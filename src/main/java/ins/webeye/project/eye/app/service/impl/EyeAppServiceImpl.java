package ins.webeye.project.eye.app.service.impl;



import java.io.ByteArrayInputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.util.IdUtil;
import ins.webeye.common.constant.Constants;
import ins.webeye.common.constant.RedisConstants;
import ins.webeye.common.constant.UserConstants;
import ins.webeye.common.constant.UserConstants.OperType;
import ins.webeye.common.utils.AddressUtils;
import ins.webeye.common.utils.DESUtils;
import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.RedisUtil;
import ins.webeye.common.utils.bean.BeanUtils;
import ins.webeye.common.utils.file.FileUploadUtils;
import ins.webeye.common.utils.file.FileUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.framework.config.ServerConfig;
import ins.webeye.framework.manager.AsyncManager;
import ins.webeye.project.eye.api.service.EyeRecordTokenService;
import ins.webeye.project.eye.app.service.EyeRecordFileService;
import ins.webeye.project.eye.app.service.IEyeAppService;
import ins.webeye.project.eye.clientconfig.domain.EyeClientConfig;
import ins.webeye.project.eye.clientconfig.service.IEyeClientConfigService;
import ins.webeye.project.eye.nodeconfig.domain.EyeNodeConfig;
import ins.webeye.project.eye.nodeconfig.service.IEyeNodeConfigService;
import ins.webeye.project.eye.order.domain.EyeOrder;
import ins.webeye.project.eye.order.domain.EyeOrderPage;
import ins.webeye.project.eye.order.service.IEyeOrderPageService;
import ins.webeye.project.eye.order.service.IEyeOrderService;
import ins.webeye.project.eye.ordercheck.service.IEyeOrderCheckService;
import ins.webeye.project.eye.pageversion.domain.EyePageVersion;
import ins.webeye.project.eye.pageversion.service.IEyePageVersionService;
import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.record.service.IEyeRecordPageService;
import ins.webeye.project.eye.vo.OrderInfoVo;
import ins.webeye.project.eye.vo.UrlHanledVo;
import ins.webeye.project.eye.vo.WebEyeSdkConfigVo;
import ins.webeye.project.storage.FileStreamService;
import lombok.extern.slf4j.Slf4j;

/**
 * app Service业务层处理
 * @author xiaoquan
 * @date 2020-07-15
 */
@Service
@Slf4j
public class EyeAppServiceImpl implements IEyeAppService 
{
	@Autowired
	private RedisUtil redisUtil;
    @Autowired
    private ServerConfig serverConfig;
	@Autowired
    private IEyeOrderService eyeOrderService;
	@Autowired
    private IEyeOrderPageService eyeOrderPageService;
	@Autowired
	private IEyeRecordPageService eyeRecordPageService;
	@Autowired
	private IEyeNodeConfigService eyeNodeConfigService;
	@Autowired
	private IEyePageVersionService eyePageVersionService;
	@Autowired
	private EyeRecordTokenService recordTokenService;
	@Autowired
	private EyeRecordFileService recordFileService;
	@Autowired
	private IEyeClientConfigService clientConfigService;
	@Autowired
	private IEyeOrderCheckService orderCheckService;
	@Autowired
	private FileStreamService fileStreamService;
	
	private static final String operator = "admin";
	
	@Value("${server.servlet.context-path}")
	private String contextPath;	
	
    @Override
    public EyeRecordPage startRecord(EyeRecordPage eyeRecordPage) throws Exception{
    	String operType =eyeRecordPage.getOperType();
    	String nodeCode =eyeRecordPage.getNodeCode();
    	//操作类型:0-子节点, 录制-1，截屏-2
		if(OperType.SUB_NODE.equals(operType)){// 子节点操作:只保存操作数据
   		 	 eyeRecordPage.setBeginTime(new Date());//开始录制时间
	         eyeRecordPageService.insertEyeRecordPage(eyeRecordPage);
		}else if(OperType.CAPTURE.equals(operType)){// 截屏操作
    		eyeRecordPage.setBeginTime(new Date());
    		eyeRecordPage.setEndTime(new Date());
    		eyeRecordPage.setRecordTime(0);
			eyeRecordPage.setFilePath(getImgFilePath(eyeRecordPage));
			eyeRecordPageService.insertEyeRecordPage(eyeRecordPage);
		}else{// 1-普通录制操作

			eyeRecordPage.setBeginTime(new Date());// 开始录制时间
			String saveFilePath = this.getTsFilePath(eyeRecordPage.getNodeCode(),eyeRecordPage.getSysCode(),eyeRecordPage.getProductCode());
			eyeRecordPage.setFilePath(saveFilePath);
			eyeRecordPageService.insertEyeRecordPage(eyeRecordPage);

			eyeRecordPage.setFileRealPath(saveFilePath);// filePath被前端代码占用了，将真实文件路径放到这里
			eyeRecordPage.setFilePath(DESUtils.encrypt(eyeRecordPage.getFilePath()));// filepath 加密

			recordTokenService.updateToken(eyeRecordPage);
			recordFileService.startRecord(eyeRecordPage);

			// 用异步方法去补未完成节点的时间
			AsyncManager.me().execute(this.checkEndTimeTask(eyeRecordPage,nodeCode),1);
		}

		return eyeRecordPage;
    }
    
	public TimerTask checkEndTimeTask(EyeRecordPage eyeRecordPage,String nodeCode) {
		return new TimerTask() {

			@Override
			public void run() {
				try{

					List<EyeRecordPage> list = new ArrayList<EyeRecordPage>();
					// 检查数据库是否已经录制过这些数据
					EyeRecordPage eyeRecordSelect = new EyeRecordPage();
					eyeRecordSelect.setToken(eyeRecordPage.getToken());
					eyeRecordSelect.setProductCode(eyeRecordPage.getProductCode());
					list = eyeRecordPageService.selectEyeRecordPageList(eyeRecordSelect);// 查询所有录制信息数据
					// 检查同订单其他数据
					if(list!=null&&list.size()>1){
						// 检查其他信息是否已经录制完
						for(EyeRecordPage record:list){
							if(record.getEndTime()==null&& !record.getNodeCode().equals(nodeCode)){// 结束时间为空并且不是当前环节，则设置为当前时间
								record.setEndTime(new Date());
								record.setRecordTime(DateUtils.getIntervalTime(record.getBeginTime(),record.getEndTime()));
								eyeRecordPageService.updateEyeRecordPage(record);
							}
						}
					}

					log.debug("checkEndTimeTask node:{} END",nodeCode);
				}catch(Exception e){
					log.error("异步方法checkEndTimeTask执行失败"+e.getMessage(),e);
				}
			}
		};
	}

    @Override
    public EyeRecordPage endRecord(EyeRecordPage eyeRecordPage) throws Exception{

    	String nodeCode =eyeRecordPage.getNodeCode();
    	String operType =eyeRecordPage.getOperType();

    	List<EyeRecordPage> list = new ArrayList<EyeRecordPage>();
    	//查询条件，将最后一个节点更新
    	EyeRecordPage eyeRecordSelect =new EyeRecordPage();
    	eyeRecordSelect.setToken(eyeRecordPage.getToken());
    	eyeRecordSelect.setProductCode(eyeRecordPage.getProductCode());
    	if("0".equals(operType)){//子节点操作:只保存当前操作节点数据
    		eyeRecordSelect.setNodeCode(nodeCode);
    		list = eyeRecordPageService.selectEyeRecordPageList(eyeRecordSelect);
    		if(list!=null&&list.size()>0){
    			for(EyeRecordPage record:list){//更新
    				if(record.getEndTime()==null){//结束时间为空
		   				record.setEndTime(new Date());
		   				record.setRecordTime(DateUtils.getIntervalTime(record.getBeginTime(), record.getEndTime()));
		   				eyeRecordPageService.updateEyeRecordPage(record);
		   			}
            	}
    		}
    	}else{
    		list = eyeRecordPageService.selectEyeRecordPageList(eyeRecordSelect);
	    	if(list!=null&&list.size()>0){
	    		for(EyeRecordPage record:list){//结束操作将所有结束时间为空的赋值//可以判断最后一个节点
	    			if(record.getEndTime()==null) {
	    				record.setEndTime(new Date());
	    				record.setRecordTime(DateUtils.getIntervalTime(record.getBeginTime(), record.getEndTime()));
	    				eyeRecordPageService.updateEyeRecordPage(record);
	    			}
	    		}
	    	}else{
	    		throw new Exception("失败:未找到相关录制信息，请确认");
	    	}

    	}
		recordTokenService.updateToken(eyeRecordPage);
    	return eyeRecordPage;
    }
    
	/**
	 * 获得图片的保存路径
	 * @param eyeRecord
	 * @param suffix
	 * @return
	 */
	private String getImgFilePath(EyeRecordPage eyeRecord) {
		String fileName = eyeRecord.getNodeCode()+"_"+ObjectId.next()+".jpg";
		String filePath = FileUploadUtils.getRecordFilePath(eyeRecord.getSysCode(),eyeRecord.getProductCode());
		return filePath+fileName;
	}
	
	/**
	 * 获取录制文件保存的了路径，同时要触发1个图片URL替换的延后任务
	 * @param eyeRecord
	 * @return
	 */
	private String getTsFilePath(String nodeCode,String sysCode,String productCode) {

		String fileName = nodeCode+"_"+IdUtil.fastSimpleUUID()+UserConstants.FILE_TS;
		String filePath = FileUploadUtils.getRecordFilePath(sysCode,productCode)+fileName;

		// 文件替换改为在文件转移时执行 redisUtil.set(RedisConstants.FILE_PATH_CACHE+filePath,1,12*60);
		// 文件替换改为在文件转移时执行 AsyncManager.me().execute(this.asynHandleTask(null,null,filePath,"fileHandle"),60*10);// 暂时设置为10分钟后触发替换图片操作

		return filePath;
	}

	@Override
	public int syncOrderInfo(OrderInfoVo orderVo) throws Exception {

		EyeRecordPage eyeRecordPage = new EyeRecordPage();
		eyeRecordPage.setOrderId(orderVo.getOrderId());
		eyeRecordPage.setPolicyNo(orderVo.getPolicyNo());
		eyeRecordPage.setPolicyIdcard(orderVo.getPolicyIdcard());
		eyeRecordPage.setPolicyName(orderVo.getPolicyName());
		eyeRecordPage.setFlag(orderVo.getPayStatus());
		eyeRecordPage.setUpdateUser(orderVo.getConsumerID());

		int result = 0;
		if(StringUtils.isNotBlank(orderVo.getOrderId())){
			result = eyeRecordPageService.updateEyeRecordPage(eyeRecordPage);// 根据订单号update
		}
		if(result<1&&StringUtils.isNotBlank(orderVo.getToken())){// 如果根据订单号未更新到 说明未产生订单，根据token再update一次
			eyeRecordPage.setToken(orderVo.getToken());
			eyeRecordPage.setProductCode(orderVo.getProductCode());
			result = eyeRecordPageService.updateEyeRecordPageByToken(eyeRecordPage);
		}

		if("3".equals(orderVo.getPayStatus())){
			// 3-支付前token关联订单号,未实际支付,只是update上订单号，不需要触发数据合并
			return 1;
		}

		if(result<1&&StringUtils.isNotBlank(orderVo.getOrderId())&&StringUtils.isNotBlank(orderVo.getPolicyNo())){
			// 还是没有更新到的话，可能是已经在的转移到订单表了，需要去更新orader表
			EyeOrder eyeOrder = new EyeOrder();
			eyeOrder.setOrderId(orderVo.getOrderId());
			eyeOrder.setPolicyNo(orderVo.getPolicyNo());
			eyeOrder.setPolicyIdcard(orderVo.getPolicyIdcard());
			eyeOrder.setPolicyName(orderVo.getPolicyName());
			eyeOrder.setUpdateUser(orderVo.getConsumerID());
			result = eyeOrderService.updateByOrderId(eyeOrder);
			if(result>1){// 如果在order表已经更新了数据，说明已经转移了，不用二次转移
				orderCheckService.saveEyeOrderCheck(orderVo,result);
				return result;// 根据订单号已经update到了，说明数据已经转移，不用再进行后面的转移了
			}
		}

		// 0和1 保存对账数据
		orderCheckService.saveEyeOrderCheck(orderVo,result);
		if(result<1){
			return result;// 订单不存在，不需要触发数据合并
		}
		if("0".equals(orderVo.getPayStatus())){
			// 未支付,不需要触发数据合并
			return result;
		}
		eyeRecordPage.setToken(orderVo.getToken());
		eyeRecordPage.setProductCode(orderVo.getProductCode());
		// 20秒后 触发录制记录转移的逻辑,这20秒还会等待前端的一些请求
		// Threads.sleep(RandomUtil.randomLong(30,100)*10);// 随机等等500毫秒到1500毫秒，
		if(redisUtil.lock(eyeRecordPage.getOrderId(),60)){// 锁定30秒执行这个异步方法，如果第二个请求再进来不会再执行
			AsyncManager.me().execute(this.asynHandleTask(eyeRecordPage,null,null,"endPaySuccess"),20);
		}
		return result;
	}
	
	/**
	 * 完成一个支付流程
	 * @param eyeRecord
	 * @throws Exception 
	 * @modified: ☆LiuPing(2020年7月19日 下午1:47:52): <br>
	 */
	public void endPaySuccess(EyeRecordPage eyeRecord) {
		String policyNo = eyeRecord.getPolicyNo();// 保单号
		String policyName = eyeRecord.getPolicyName();// 投保人姓名
		String policyIdcard = eyeRecord.getPolicyIdcard();// 投保人证件
		String orderId = eyeRecord.getOrderId();// 订单号
		String productCode = eyeRecord.getProductCode();
		String productName = eyeRecord.getProductName();// 订单号
		String sysCode = eyeRecord.getSysCode();// 订单号
		Date createTime = null;
		long firstStartTime = 0L;
		String token = eyeRecord.getToken();

		log.info(">>>endPaySuccess Start: token={},orderid={}",token,orderId);
		try{

			if(StringUtils.isBlank(token)){
				EyeRecordPage eyeRecordSelect = new EyeRecordPage();
				eyeRecordSelect.setOrderId(orderId);
				List<EyeRecordPage> list = eyeRecordPageService.selectEyeRecordPageList(eyeRecordSelect);

				if(list!=null&&list.size()>0){
					for(EyeRecordPage record:list){
						if(StringUtils.isNotBlank(record.getToken())){
							token = record.getToken();
							productCode = record.getProductCode();// 防止productCode传错的情况
							break;
						}
					}
				}
			}
			if(StringUtils.isBlank(token)){
				log.warn("orderId:{},没找到对应token，可能数据已经转移",orderId);
				return;
			}
			// 查询条件，将最后一个节点更新
			List<EyeRecordPage> list = new ArrayList<EyeRecordPage>();
			EyeRecordPage eyeRecordSelect = new EyeRecordPage();
			eyeRecordSelect.setToken(token);
			eyeRecordSelect.setProductCode(productCode);
			list = eyeRecordPageService.selectEyeRecordPageList(eyeRecordSelect);// 根据token和产品编码得到全部的数据，order by create_time desc
			if(list!=null&&list.size()>0){// 没有数据说明被转移了，有则说明还未转移
				for(EyeRecordPage record:list){
					// 如果为空则从时间最近的取，得到一个不为空的值
					orderId = StringUtils.isBlank(orderId) ? record.getOrderId() : orderId;
					policyIdcard = StringUtils.isBlank(policyIdcard) ? record.getPolicyIdcard() : policyIdcard;
					policyName = StringUtils.isBlank(policyName) ? record.getPolicyName() : policyName;
					policyNo = StringUtils.isBlank(policyNo) ? record.getPolicyNo() : policyNo;
					productCode = StringUtils.isBlank(productCode) ? record.getProductCode() : productCode;
					productName = StringUtils.isBlank(productName) ? record.getProductName() : productName;
					sysCode = StringUtils.isBlank(sysCode) ? record.getSysCode() : sysCode;
					token = StringUtils.isBlank(token) ? record.getToken() : token;
					createTime = record.getCreateTime();
				}
				if(StringUtils.isBlank(orderId)) throw new Exception("录制记录中没找到订单号");
				firstStartTime = createTime.getTime();

				// 转存至 order 和 order_page 记录表
				EyeOrder order = new EyeOrder();
				order.setToken(token);
				order.setProductCode(productCode);
				List<EyeOrder> orderList = eyeOrderService.selectEyeOrderList(order);// 这里得到的应该是一条或0数据
				if(orderList!=null&&orderList.size()>0){
					for(EyeOrder eyeOrder:orderList){// 更新
						eyeOrder.setPolicyName(policyName);
						eyeOrder.setPolicyIdcard(policyIdcard);
						eyeOrder.setPolicyNo(policyNo);
						eyeOrder.setOrderId(orderId);
						BeanUtils.copyBeanProp(order,eyeOrder);
					}
				}else{
					eyeRecord.setProductCode(productCode);
					eyeRecord.setProductName(productName);
					eyeRecord.setToken(token);
					eyeRecord.setPolicyName(policyName);
					eyeRecord.setPolicyIdcard(policyIdcard);
					eyeRecord.setPolicyNo(policyNo);
					eyeRecord.setOrderId(orderId);
					eyeRecord.setCreateTime(createTime);
					eyeRecord.setSysCode(sysCode);
					BeanUtils.copyBeanProp(order,eyeRecord);
					order.setId(null);
				}

				StringBuffer md5FileContent = new StringBuffer();
				// StringBuffer saveFileContent = new StringBuffer();
				int allFileSize = 0;
				List<String> deleteFileList = new ArrayList<>();

				String orderFilePath = FileUploadUtils.getOrderFilePath(sysCode,productCode,token);// 订单合并后文件存放路径

				for(EyeRecordPage record:list){// 结束操作将所有结束时间为空的赋值
					String recordFilePath = record.getFilePath();
					record.setPolicyName(policyName);
					record.setPolicyIdcard(policyIdcard);
					record.setPolicyNo(policyNo);
					record.setOrderId(orderId);
					if(record.getEndTime()==null){
						record.setEndTime(new Date());
					}

					EyeOrderPage orderPage = new EyeOrderPage();
					BeanUtil.copyProperties(record,orderPage,"childEyeOrderPageList","id");
					orderPage.setRecordTime(DateUtils.getIntervalTime(record.getBeginTime(),record.getEndTime()));

					// liuping ：根据当前 orderPage 的创建时间，计算与第一个节点时间的偏移量，用于控制播放按钮的偏移时间
					long playSetTime = orderPage.getCreateTime().getTime()-firstStartTime;
					orderPage.setPlaySetTime(playSetTime);
					if(StringUtils.isNotBlank(recordFilePath)){// 有文件的节点
						if(recordFilePath.endsWith(UserConstants.FILE_TS)){
							// 如果支付比较快，可能延后保存的文件都还没保存，先检查一下保存，同时清空文件保存时用的缓存
							recordFileService.endFileWrite(recordFilePath,true);
						}
						try{
							String fileContent = replaceContent(recordFilePath,record.getPageId(),false);// 检查是否有未替换的已经进行替换
							byte[] bytes = null;
							if(fileContent!=null){
								bytes = fileContent.getBytes(Constants.UTF8);
							}else{
								try{
									bytes = fileStreamService.readFileBytes(recordFilePath);
								}catch(Exception e){
									// 2020年10月2日 liuping 有个时候录制的文件会没有数据，这里不要抛错，先继续转移订单
									log.error("OSS getObject Error:file="+recordFilePath,e);
								}
							}

							if(bytes==null||bytes.length==0){
								log.error("No such file:{}",recordFilePath);
								orderPage.setFileSize(0d);// kb
							}else{

								allFileSize += bytes.length;

								boolean isExcludeMerge = false;
								if(recordFilePath.endsWith(UserConstants.FILE_TS)){// 只有录制文件才参与md5计算
									isExcludeMerge = checkExcludeMerge(sysCode,record.getNodeCode());
									if(isExcludeMerge==false){
										md5FileContent.insert(0,new String(bytes,Constants.UTF8));// 新的文件放到前面
									}
								}

								String newFilePath = null;
								if(isExcludeMerge){// 排除合并的，文件进行转移
									newFilePath = orderFilePath+FileUtils.getFileNameByPath(recordFilePath);
									fileStreamService.uploadFileData(newFilePath,bytes);
									deleteFileList.add(recordFilePath);// TS 文件将会被删除
									orderPage.setPlaySetTime(0L);
								}else if(playSetTime==0){// 偏移时间为0，表示是第一个节点，需要将文件内容全部放到第一个节点的文件里面，其他节点文件进行删
									deleteFileList.add(recordFilePath);// TS 文件将会被删除
									newFilePath = orderFilePath+FileUtils.getFileNameByPath(recordFilePath);
									fileStreamService.saveFileContent(newFilePath,md5FileContent.toString());
								}else if(recordFilePath.endsWith(UserConstants.FILE_TS)){
									deleteFileList.add(recordFilePath);// TS 文件将会被删除
								}else{
									// 图片文件转移到标准存储或者影像存储
									newFilePath = orderFilePath+FileUtils.getFileNameByPath(recordFilePath);
									fileStreamService.moveFile(recordFilePath,newFilePath);
								}

								orderPage.setFilePath(newFilePath);
								orderPage.setFileSize(bytes.length/1024.00d);// kb
							}
						}catch(Exception e){
							log.error("文件转移异常:"+e.getMessage(),e);
							orderPage.setFileSize(0d);// kb
						}
					}else{
						orderPage.setFileSize(0d);// kb
					}

					eyeOrderPageService.insertEyeOrderPage(orderPage); // 转移录制信息
					eyeRecordPageService.deleteEyeRecordPageById(record.getId());// 删除源录制数据
				}

				// 获取总内容大小，计算MD5作为验真码使用
				byte[] bytes = md5FileContent.toString().getBytes(Constants.UTF8);
				String verifyCode = DigestUtils.md5Hex(new ByteArrayInputStream(bytes));// MD5
				order.setVerifyCode(verifyCode);
				order.setFileSize(allFileSize/1024.00d);
				order.setSysCode(sysCode);
				if(order.getId()==null){
					eyeOrderService.insertEyeOrder(order);
				}else{
					eyeOrderService.updateEyeOrder(order);
				}
				// 将高频存储的TS文件删除
				if(deleteFileList.size()>0){
					fileStreamService.deleteFiles(deleteFileList.toArray(new String[deleteFileList.size()]));
				}

				// 肖权 启动一个异步任务线程对该订单数据进行行为质检 //质检 ： 查询节点是否关联与该产品
				qualityTesting(token,productCode);
			}

			log.info(">>>endPaySuccess END: token={},orderid={}",token,orderId);
		}catch(Exception e){
			log.error(">>>endPaySuccess ERROR "+e.getMessage(),e);
		}
		finally{
			// 清空token缓存
			recordTokenService.clearTokenCache(token,productCode,orderId);
			redisUtil.unlock(orderId);
		}
	}
	

	/**
	 * 检查是不是需要排除合并的节点
	 * @param sysCode
	 * @param nodeCode
	 * @return
	 */
	private boolean checkExcludeMerge(String sysCode,String nodeCode) {
		if("QSCQB".equals(sysCode)){// 现代保险PC端这两个节点不要进行合并，否则播放会异常
			return true;
		}
		if("mms-pc".equals(sysCode)){// 现代保险PC端这两个节点不要进行合并，否则播放会异常
			if("InsureNotes".equals(nodeCode)){
				return true;
			}else if("ClauseBrowse".equals(nodeCode)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 行为质检 ： 查询节点是否关联与该产品
	 * @param recordList
	 */
	private void qualityTesting(String token,String productCode) {
		EyeOrder order = new EyeOrder();
		order.setToken(token);
		List<EyeOrder> orderList = eyeOrderService.selectEyeOrderList(order);
		if(orderList!=null&&orderList.size()>0) order = orderList.get(0);
		// 查询订单明细
		EyeOrderPage orderPage = new EyeOrderPage();
		orderPage.setToken(token);
		List<EyeOrderPage> orderPageList = eyeOrderPageService.selectPageListForNode(orderPage);
		if(orderPageList!=null&&orderPageList.size()>0){
			// 查询节点配置相关信息
			EyeNodeConfig eyeNodeConfig = new EyeNodeConfig();
			eyeNodeConfig.setRelationProductCode(productCode);
			eyeNodeConfig.setNodeType("regulatory");// 只查询监管节点的
			List<EyeNodeConfig> nodeConfigList = eyeNodeConfigService.selectEyeNodeConfigByProductCodeList(eyeNodeConfig);
			String checkMessage = "";
			order.setCheckStatus("0");//检查前设置为通过状态
			for(EyeNodeConfig nodeConfig:nodeConfigList){
				String nodeCode = nodeConfig.getNodeCode();// 节点配置节点代码
				String nodeName = nodeConfig.getNodeName();
				int operMinTime = nodeConfig.getOperMinTime();// 该节点操作最小时间
				Boolean status = false;
				for(EyeOrderPage orderPageNew:orderPageList){
					if(nodeCode.equals(orderPageNew.getNodeCode())){// 验证是否匹配节点
						status = true;
						if(operMinTime>orderPageNew.getRecordTime()){// 验证操作时间是否大于设置时间
							checkMessage = checkMessage+nodeName+":操作少于 "+operMinTime+" 秒;\n";
							order.setCheckStatus("1");
						}
					}
				}
				if( !status){
					checkMessage = checkMessage+nodeName+":流程缺失;\n";
					order.setCheckStatus("1");
				}
			}
			order.setCheckTime(new Date());// 检查时间
			order.setCheckMessage(checkMessage);
			eyeOrderService.updateEyeOrder(order);// 更新订单状态
		}
	}
	
	@Override
	public TimerTask asynHandleTask(EyeRecordPage eyeRecord,UrlHanledVo urlHanledVo,String filePath,String type) {
		return new TimerTask() {
			@Override
			public void run() {
				try{
					log.debug("asynHandleTask type:{} START",type);
					if("endPaySuccess".equals(type)){
						endPaySuccess(eyeRecord);
					}else if("urlHandle".equals(type)){
						urlHandle(urlHanledVo);
					}else if("fileHandle".equals(type)){
						String pageId = filePath.substring(0,filePath.indexOf('#'));
						String htmlFilePath = filePath.substring(filePath.indexOf('#')+1);
						replaceContent(htmlFilePath,pageId,true);
					}
					log.debug("asynHandleTask type:{} END",type);
				}catch(Exception e){
					log.error("异步方法asynHandleTask执行"+type+"失败"+e.getMessage(),e);
				}
			}
		};
	}
	private  Map<String,String> findPageVersions(String pageId) throws Exception{
		Map<String,String> urlMap = new HashMap<String,String>();
		String url ="";
		String newUrl ="";
		String fileExt = "";
		String filePath = "";
		EyePageVersion pageVersion = new EyePageVersion();
        pageVersion.setFileType("urlFile");
        pageVersion.setPageId(pageId);
        List<EyePageVersion> eyePageVersions = eyePageVersionService.selectEyePageVersionList(pageVersion);
        if(eyePageVersions!=null && eyePageVersions.size()>0){
        	for(EyePageVersion version:eyePageVersions){
        		url = version.getNodeUrl();
        		filePath = version.getFilePath();
        		//获取后缀
				if(url.indexOf("?")>0){
					fileExt = FileUploadUtils.getFileExt(url.substring(0,url.lastIndexOf("?")));
				}else{
					fileExt = FileUploadUtils.getFileExt(url);
				}
        		if("/".equals(contextPath))contextPath="";//如果默认/则为空
				if(FileUploadUtils.IMAGE_EXTS.indexOf(fileExt.toLowerCase())>-1){//图片
					newUrl = contextPath+"/eye/recordPlay/getImage?path="+DESUtils.encrypt(filePath);// 拼接系统url，加密处理
				}else{//css  
					newUrl = contextPath+"/eye/recordPlay/getFile?path="+DESUtils.encrypt(filePath);// 拼接系统url，加密处理
				}
        		urlMap.put(url,newUrl);
        	}
        }else{//压根没有，理论上不会有这种情况
			//if(fromCache) redisUtil.del(RedisConstants.FILE_PATH_CACHE+path);// 删掉缓存，下次不用再执行替换了
			return null;
        }
		return urlMap;
	}


	/**
	 * 文件内容替换并重新写入文件
	 * @throws Exception 
	 */
	private String replaceContent(String path,String pageId,Boolean fromCache) throws Exception {

		if(fromCache&&redisUtil.hasKey(RedisConstants.FILE_PATH_CACHE+path)==false) return null;// 说明文件已经替换过了
		if(StringUtils.isBlank(pageId)){
			pageId = "NoPageId";
		}
		Map<String,String> urlMap = (Map<String,String>)redisUtil.get(RedisConstants.FILE_URL_CACHE+pageId);
		if(urlMap==null||urlMap.isEmpty()){
			// 肖权 这里不能返回，应该是根据pageid再去数据库查询一次，放到缓存，防止有些是6个时候后才触发替换的，或者版本id已经变了再替换的
			urlMap = findPageVersions(pageId);
			if(urlMap!=null&& !urlMap.isEmpty()){
				redisUtil.set(RedisConstants.FILE_URL_CACHE+pageId,urlMap);// 缓存6个小时
			}
		}

		if(urlMap==null||urlMap.isEmpty()){
			if(fromCache) redisUtil.del(RedisConstants.FILE_PATH_CACHE+path);// 删掉缓存，下次不用再执行替换了
			return null;// 数据库也没找到，返回吧
		}
		String content = null;
		try{
			content = fileStreamService.readFileContent(path);
			if(StringUtils.isNotBlank(content)){
				log.debug("文件内容替换：path={}",path);
				for(String url:urlMap.keySet()){
					content=content.replaceAll(url,urlMap.get(url));
				}
			}
			if(fromCache) redisUtil.del(RedisConstants.FILE_PATH_CACHE+path);// 删掉缓存，下次不用再执行替换了
		}catch(Exception e){
			log.error("文件替换失败："+e.getMessage(),e);
		}
		return content;
	}
	
	/**
	 * URL->文件处理方法
	 * @param urlHanledVo
	 * @modified:
	 * ☆XiaoQuan(2020年7月22日 ): <br>
	 */
	private void urlHandle(UrlHanledVo urlHanledVo) {
		String userId = urlHanledVo.getUserId();
		String pageId = urlHanledVo.getPageId();
		String nodeCode = urlHanledVo.getNodeCode();
		String productCode = urlHanledVo.getProductCode();
		String productName = urlHanledVo.getProductName();
		String[] urls = Convert.toStrArray(urlHanledVo.getUrlStrings());
		String fileExt = "";
		String filePath = "";
		String newUrl ="";
		log.debug("url处理：节点:{},pageId:{},urls SIZE:{}",nodeCode,pageId,urls.length);
		if(StringUtils.isBlank(pageId)){
			pageId = "NoPageId";
		}
		//存在的图片缓存
		Map<String,String> urlMap = (Map<String,String>)redisUtil.get(RedisConstants.FILE_URL_CACHE+pageId);
		if(urlMap==null)urlMap = new HashMap<String,String>();
		for(String url:urls){
			try{
				if(urlMap.containsKey(url)){
					// 这个URL还在缓存中，不要在获取内容
					continue;
				}
				if( !url.startsWith("http")){
					log.info("not http url:{}",url);
					continue;
				}
				//获取后缀
				if(url.indexOf("?")>0){
					fileExt = FileUploadUtils.getFileExt(url.substring(0,url.lastIndexOf("?")));
				}else{
					fileExt = FileUploadUtils.getFileExt(url);
				}

				URL URL = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) URL.openConnection();
				int responseCode = conn.getResponseCode();
				if(responseCode!=200){
					log.error("##### {} URL:{} ",responseCode,url);// 无效文件，不抓取
					continue;
				}
				// 从URL中得到文件字节
				byte[] urlFileData = FileUtils.readStreamAsByteArray(conn.getInputStream());

				String md5Hex = DigestUtils.md5Hex(urlFileData);// MD5
				// 没有缓存时通过MD5查询数据库，如果数据库查询到了放入缓存，如果数据库没有，新建一个版本数据
				EyePageVersion eyePageVersion = eyePageVersionService.checkFileMd5(md5Hex);
				if(eyePageVersion==null){
					//拼接path
					filePath = FileUploadUtils.getPathForRecordVersion(productCode,nodeCode,pageId,md5Hex + fileExt);
					log.debug("url={},MD5={},fileExt{}",url,md5Hex,fileExt);

					fileStreamService.uploadFileData(filePath,urlFileData);

		            //将文件记录至数据库MD5  filePath
		            EyePageVersion pageVersion = new EyePageVersion();
		            pageVersion.setFileMd5(md5Hex);
		            pageVersion.setFilePath(filePath);
		            pageVersion.setFileType("urlFile");
		            pageVersion.setCreateUser(operator);
		            pageVersion.setProductCode(productCode);
		            pageVersion.setProductName(productName);
		            pageVersion.setPageId(pageId);
					pageVersion.setNodeUrl(url);
		            pageVersion.setNodeCode(nodeCode);
		            eyePageVersionService.insertEyePageVersion(pageVersion);
				}else{//放入缓存
					filePath = eyePageVersion.getFilePath();
				}
				if("/".equals(contextPath))contextPath="";//如果默认/则为空
				if(FileUploadUtils.IMAGE_EXTS.indexOf(fileExt.toLowerCase())>-1){//图片
					newUrl = contextPath+"/eye/recordPlay/getImage?path="+DESUtils.encrypt(filePath);// 拼接系统url，加密处理
				}else{//css  
					newUrl = contextPath+"/eye/recordPlay/getFile?path="+DESUtils.encrypt(filePath);// 拼接系统url，加密处理
				}
	            urlMap.put(url,newUrl);
			
	        }catch(MalformedURLException e){
				log.error(url+" MalformedURLException：{}",e.getMessage());
			}catch(ConnectException e){
				log.error(url+"连接超时，请检查网络",e);
			}catch(Exception e){
				log.error("url转换错误："+e.getMessage(),e);
			}
        }
		//放入缓存
		redisUtil.set(RedisConstants.FILE_URL_CACHE+pageId,urlMap);// 缓存6个小时
		redisUtil.expire(RedisConstants.FILE_URL_CACHE+pageId,60*60*6);
	}

	@Override
	public WebEyeSdkConfigVo getEyeSdkConfigVo(String sysCode) {
		if(StringUtils.isBlank(sysCode)){
			throw new IllegalArgumentException("sysCode参数不能为空。（该参数在后台的“对接系统/渠道配置”中配置）");
		}
		EyeClientConfig clientConfig = clientConfigService.selectClientConfig(sysCode,null);
		if(clientConfig==null){
			throw new IllegalArgumentException("sysCode:"+sysCode+"不存在。（请到后台的“对接系统/渠道配置”中配置）");
		}
		String baseUrl = serverConfig.getApiUrl(); // url
		if(UserConstants.YES.equals(clientConfig.getHttpsOnly())&&baseUrl.indexOf("https")<0){
			baseUrl = baseUrl.replace("http","https");// 签字替换为HTTPS请求
		}
		WebEyeSdkConfigVo sdkConfigVo = new WebEyeSdkConfigVo();
		sdkConfigVo.setServiceURL(baseUrl);
		sdkConfigVo.setSdkType(clientConfig.getSdkType());
		sdkConfigVo.setSysCode(sysCode);
		if(UserConstants.YES.equals(clientConfig.getAutoStart())&&UserConstants.SDK_TYPE_S.equals(clientConfig.getSdkType())){
			sdkConfigVo.setAutoStart(true);
		}else{
			sdkConfigVo.setAutoStart(false);
		}

		return sdkConfigVo;
	}

	@Override
	public String getCityName(String ip) {
		if(ip!=null&&ip.indexOf(',')>0){
			ip = ip.substring(0,ip.indexOf(','));// 得到了多个IP，取第一个
		}
		String cityName = (String)redisUtil.hget(RedisConstants.IP_NAME_CACHE,ip);
		if(StringUtils.isBlank(cityName)){
			cityName = AddressUtils.getRealAddressByIP(ip);
			redisUtil.hset(RedisConstants.IP_NAME_CACHE,ip,cityName,60*60*4);
		}
		return cityName;
	}
	
	
}
