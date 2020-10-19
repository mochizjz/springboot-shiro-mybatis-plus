/******************************************************************************
* CREATETIME : 2020年8月31日 上午1:46:29
******************************************************************************/
package ins.webeye.project.eye.app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import ins.webeye.common.utils.file.GZIPUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.constant.Constants;
import ins.webeye.common.constant.RedisConstants;
import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.RedisFileUtil;
import ins.webeye.common.utils.RedisUtil;
import ins.webeye.common.utils.file.FileUtils;
import ins.webeye.framework.manager.AsyncManager;
import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.record.service.IEyeRecordPageService;
import ins.webeye.project.eye.vo.EyeRecordFileVo;
import ins.webeye.project.storage.FileStreamService;
import lombok.extern.slf4j.Slf4j;

/**
 * 录制文件处理相关的服务
 * @author ★LiuPing
 */
@Slf4j
@Service
public class EyeRecordFileService {

	private static final String FILE_RECORD_VO_CACHE = "FILE_RECORD_VO";// 缓存当前的文件保存任务，记录文件下一个写入的位置
	private static final String FILE_SAVE_TASK_CACHE = "FILE_SAVE_TASK";// 缓存标记当前有没有文件保存的任务，如果没有会新起一个
	private static final String FILE_LAST_TIME_CACHE = "FILE_LAST_TIME";// 缓存标记这个文件的最后追加时间
	@Autowired
	private FileStreamService fileStreamService;
	@Autowired
	private IEyeRecordPageService recordPageService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private RedisFileUtil redisFileUtil;
	/**
	 * 开始一个文件录制，发起10秒后写入文件的任务
	 * @modified: ☆LiuPing(2020年8月31日 上午1:58:10): <br>
	 */
	public void startRecord(EyeRecordPage recordPage) {
		EyeRecordFileVo fileVo = new EyeRecordFileVo();
		fileVo.setId(recordPage.getId());
		fileVo.setToken(recordPage.getToken());
		fileVo.setProductCode(recordPage.getProductCode());
		fileVo.setOrderId(recordPage.getOrderId());
		fileVo.setFileRealPath(recordPage.getFileRealPath());
		fileVo.setBeginTime(recordPage.getBeginTime());

		String cacheKey = FileUtils.getFileNameByPath(recordPage.getFileRealPath());
		redisUtil.hset(cacheKey,FILE_RECORD_VO_CACHE,fileVo);//
		redisUtil.hset(cacheKey,FILE_SAVE_TASK_CACHE,1);// 表示当前有异步保存的任务
		redisFileUtil.boundList(cacheKey,RedisConstants.ExpireEnum.UNSAVE_FILE);// 未保存文件一定要在1分钟内去保存
		redisUtil.expire(cacheKey,60*60);
		AsyncManager.me().execute(this.asynSaveFileTask(cacheKey),10);
	}

	/**
	 * 收到前端请求，将文件追加到缓存中
	 * @param path
	 * @param request
	 * @param fileType
	 * @throws IOException
	 */
	public void appendFileData(String path, HttpServletRequest request, String fileType) throws IOException {
		String cacheKey = FileUtils.getFileNameByPath(path);
		if(redisUtil.hHasKey(cacheKey,FILE_RECORD_VO_CACHE)==false){
			log.warn("**appendFileData error 文件{}缓存已被清理,无法继续追加文件 ",cacheKey);
			return;
		}
		String oneContent;
		if("gzip".equals(fileType)){
			oneContent = GZIPUtil.getZipStream(request);
		}else {
			// 读取文件内容
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),Constants.UTF8));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while(( line = br.readLine() )!=null){
				sb.append(line);
			}
			if(sb.toString().length()>2){// 空数组不需要追加
				oneContent = sb.toString().substring(1,sb.toString().length()-1)+",";
			}else{
				return;// 文字是空的，不用追截
			}
		}
		Integer saveTask = (Integer)redisUtil.hget(cacheKey,FILE_SAVE_TASK_CACHE);
		if(saveTask==null||saveTask==0){
			// 异步保存任务已被执行了，有新加的文件，需要追加一个异步保存任务
			redisFileUtil.boundList(cacheKey,RedisConstants.ExpireEnum.UNSAVE_FILE);// 未保存文件一定要在1分钟内去保存
			redisUtil.hset(cacheKey,FILE_SAVE_TASK_CACHE,1);// 表示当前有异步保存的任务
			AsyncManager.me().execute(this.asynSaveFileTask(cacheKey),10);// 10秒后执行文件保存
			log.debug("** appendFileData start new asynSaveFileTask ** fileName:{} ",cacheKey);
		}

		redisUtil.hset(cacheKey,FILE_LAST_TIME_CACHE,new Date());// 结束时间放到缓存
		if(oneContent.length()>50000){
			// 这个是个大文件，直接开始保存吧
			log.info("**appendFileData save file={} ,length={} ",cacheKey,oneContent.length());
			saveFileData(cacheKey,oneContent);
		}else{
			// 将文件追加到list 缓存后面,等会保存文件
			redisFileUtil.addToListRight(cacheKey,oneContent);
		}

	}

	public void saveFileData(String cacheKey,String lastContent) throws IOException {

		List<String> fileContentList = redisFileUtil.proListFromLeft(cacheKey);
		if(lastContent==null){
			if(fileContentList==null||fileContentList.size()==0){
				EyeRecordFileVo fileVo = (EyeRecordFileVo)redisUtil.hget(cacheKey,FILE_RECORD_VO_CACHE);
				if(fileVo!=null&&fileVo.getSaveCount()==0){// 文件内容是0，又从来没保存过文件，这行数据应该是要删除的
					log.warn("**saveFileData but fileContent is null ** fileName:{} ,token={}",cacheKey,fileVo.getToken());
				}
				return;// 没有需要保存的文件
			}
		}
		EyeRecordFileVo fileVo = (EyeRecordFileVo)redisUtil.hget(cacheKey,FILE_RECORD_VO_CACHE);
		if(fileVo==null){
			log.warn("**saveFileData error 文件{}缓存已被清理,无法继续追加文件,可能是已完成了文件转移 ",cacheKey);
			return;
		}

		StringBuffer fileContent = new StringBuffer();
		for(String content:fileContentList){
			fileContent.append(content);
		}
		if(lastContent!=null){
			fileContent.append(lastContent);
		}
		fileVo.setSaveCount(fileVo.getSaveCount()+1);
		Long position = fileStreamService.appendData(fileVo.getFileRealPath(),fileVo.getPosition(),fileContent.toString());
		fileVo.setPosition(position);

		redisUtil.hset(cacheKey,FILE_RECORD_VO_CACHE,fileVo);// 执行一次保存数据就要更新缓存

		// 更新数据库的endTime
		Date endTime = (Date)redisUtil.hget(cacheKey,FILE_LAST_TIME_CACHE);
		if(endTime==null) endTime = new Date();
		EyeRecordPage recordPage = new EyeRecordPage();
		recordPage.setId(fileVo.getId());
		recordPage.setEndTime(endTime);
		recordPage.setUpdateTime(new Date());
		recordPage.setRecordTime(DateUtils.getIntervalTime(fileVo.getBeginTime(),endTime));
		recordPageService.updateEyeRecordPage(recordPage);

		log.debug("**asynSaveFileTask END ** fileName:{} ,newPosition={}",cacheKey,fileVo.getPosition());
	}

	public TimerTask asynSaveFileTask(String cacheKey) {
		// 异步执行保存文件，是为了补救剩余未保存的
		return new TimerTask() {

			@Override
			public void run() {
				try{
					redisUtil.hdel(cacheKey,FILE_SAVE_TASK_CACHE);// 0-表示已经没有异步任务了，如果后面用户又操作了，需要再加上异步任务
					saveFileData(cacheKey,null);

				}catch(Exception e){
					log.error("异步保存文件失败："+e.getMessage(),e);
				}
			}
		};
	}

	/**
	 * 执行了文件内容替换动作，需要重新设置Position
	 * @param filePath
	 * @throws IOException
	 * @modified:
	 */
	/*public void replaceFileDataX(String filePath,String content) throws IOException {
		String cacheKey = FileUtils.getFileNameByPath(filePath);
		long newPosition = fileStreamService.replaceFileData(filePath,content);
		EyeRecordFileVo fileVo = (EyeRecordFileVo)redisUtil.hget(cacheKey,FILE_RECORD_VO_CACHE);
		if(fileVo!=null){// 当前有文件正在保存,文件替换后，要重新设置Position
			fileVo.setPosition(newPosition);
			redisUtil.hset(cacheKey,FILE_RECORD_VO_CACHE,fileVo);// 执行一次保存数据就要更新缓存
		}
	
	}*/

	public void endFileWrite(String filePath,boolean clearCache) throws IOException {
		if(StringUtils.isBlank(filePath)) return;
		// 文件结束写入，清理缓存
		String cacheKey = FileUtils.getFileNameByPath(filePath);
		log.debug("**endFileWrite:{}",cacheKey);

		if(redisUtil.hHasKey(cacheKey,FILE_RECORD_VO_CACHE)){
			saveFileData(cacheKey,null);// 清理前执行一次保存
			if(clearCache){
				redisUtil.hdel(cacheKey,FILE_RECORD_VO_CACHE,FILE_SAVE_TASK_CACHE,FILE_LAST_TIME_CACHE);
				redisUtil.del(cacheKey);
			}
		}
	}

}
