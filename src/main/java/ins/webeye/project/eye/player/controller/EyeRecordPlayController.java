package ins.webeye.project.eye.player.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.date.DateUtil;
import ins.webeye.common.constant.Constants;
import ins.webeye.common.constant.UserConstants.OperType;
import ins.webeye.common.utils.DESUtils;
import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.bean.BeanUtils;
import ins.webeye.common.utils.file.FileUtils;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.project.eye.order.domain.EyeOrder;
import ins.webeye.project.eye.order.domain.EyeOrderPage;
import ins.webeye.project.eye.order.service.IEyeOrderPageService;
import ins.webeye.project.eye.order.service.IEyeOrderService;
import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.record.service.IEyeRecordPageService;
import ins.webeye.project.storage.FileStreamService;
import lombok.extern.slf4j.Slf4j;

/**
 * 录制回放操作Controller
 * @author xiaoquan
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/eye/recordPlay")
@Slf4j
public class EyeRecordPlayController extends BaseController
{
    @Autowired
    private IEyeOrderPageService eyeOrderPageService;
    @Autowired
	private IEyeRecordPageService eyeRecordPageService;
    @Autowired
    private IEyeOrderService eyeOrderService;
	@Autowired
	private FileStreamService fileStreamService;
    /**
     * 跳转到录屏播放页面
     * @throws Exception 
     */
    @GetMapping("/toPlayPage")
    public String toPlaybackPage(String token,String productCode,String type,String playId, ModelMap mmap) throws Exception
    {
    	log.debug("EyeRecordPlayController.toPlayPage : token={},productCode={},type={}",token,productCode,type);
    	List<EyeOrderPage> list = new ArrayList<EyeOrderPage>();

    	if("recordPage".equals(type)){
    		EyeRecordPage eyeRecordPage = new EyeRecordPage();
    		eyeRecordPage.setToken(token);
    		eyeRecordPage.setProductCode(productCode);
			List<EyeRecordPage> recordPageList = eyeRecordPageService.selectEyeRecordPageList(eyeRecordPage);// order by create_time desc
			EyeRecordPage firstNode = recordPageList.get(recordPageList.size()-1);
    		for(EyeRecordPage recordPage:recordPageList){
    			EyeOrderPage eyeOrderPage = new EyeOrderPage();
    			BeanUtils.copyBeanProp(eyeOrderPage,recordPage);
    			list.add(eyeOrderPage);
				if(playId.equals(recordPage.getId().toString())&&StringUtils.isBlank(recordPage.getFilePath())){
					playId = firstNode.getId().toString();// 如果某个节点没有文件，播放节点要取第一个
				}
    		}
    		if(list!=null && list.size()>0) mmap.put("eyePlayerInfoForRecord", list.get(0));
    	}else if("orderPage".equals(type)){
    		EyeOrderPage eyeOrderPage = new EyeOrderPage();
        	eyeOrderPage.setToken(token);
			list = eyeOrderPageService.selectEyeOrderPageList(eyeOrderPage);// order by create_time desc
        	//查询订单信息
        	EyeOrder order = new EyeOrder();
        	order.setToken(token);
        	order.setProductCode(StringUtils.isBlank(productCode)?list.get(0).getProductCode():productCode);
            List<EyeOrder> orderList = eyeOrderService.selectEyeOrderList(order);
			if(orderList!=null&&orderList.size()>0){
				mmap.put("eyePlayerInfoForOrder",orderList.get(0));
			}
			playId = list.get(list.size()-1).getId()+"";// 合并后的订单播放，只有第一个节点有文件
    	}
        if(list!=null && list.size()>0){
        	for(EyeOrderPage orderPage:list){
        		if(StringUtils.isNotBlank(orderPage.getFilePath()))orderPage.setFilePath(DESUtils.encrypt(orderPage.getFilePath()));//加密
        	}
        }
        
		// 处理数据，将图片放到对应的事件节点里面
        List<EyeOrderPage> nodeList = new ArrayList<EyeOrderPage>();
		for(int i = list.size()-1; i>=0; i--){
			if( !OperType.CAPTURE.equals(list.get(i).getOperType())){// 非截屏的
				nodeList.add(list.get(i));
				list.remove(i);
			}
		}
		for(EyeOrderPage orderPage:nodeList){
			String nodeCode = orderPage.getNodeCode();
			// 判断一下回放按钮是否要显示
			if("recordPage".equals(type)&&StringUtils.isBlank(orderPage.getFilePath())){
				orderPage.setShowPayBtn(false);// 前端不显示回放按钮
			}
			Date beginTime = orderPage.getBeginTime()==null?new Date():orderPage.getBeginTime();
			Date endTime = orderPage.getEndTime()==null?new Date():orderPage.getEndTime();
			endTime = DateUtils.addSeconds(new Date(),2);// 结束时间加上两秒，因为截图是他结束时间后面1到2秒保存
			Map<String,String> imgMap = new HashMap<String,String>();
			for(int i = list.size()-1; i>=0; i--){
				EyeOrderPage orderPage1=list.get(i);
				if(OperType.CAPTURE.equals(orderPage1.getOperType())&&orderPage1.getNodeCode().equals(nodeCode)&&DateUtil
						.isIn(orderPage1.getBeginTime(),beginTime,endTime)){// 截屏；节点相等；并且属于该节点时间范围内
					imgMap.put(orderPage1.getId().toString(),orderPage1.getFilePath());
					list.remove(i);
				}
			}
			orderPage.setImgMap(imgMap);
		}
		for(EyeOrderPage orderPage:list){//最后剩下单独的截屏
			if(OperType.CAPTURE.equals(orderPage.getOperType())){
				Map<String,String> imgMap = new HashMap<String,String>();
				imgMap.put(orderPage.getId().toString(),orderPage.getFilePath());
				orderPage.setImgMap(imgMap);
				orderPage.setShowPayBtn(false);// 前端不显示回放按钮
				nodeList.add(orderPage);
			}
		}
		
		// Collections.reverse(nodeList);//排序
        mmap.put("eyePlayerInfoList", nodeList);
		mmap.put("playType",type);
        mmap.put("playId", playId);
		return "eye/player/webPlayer";
    }
    
    /**
     * 点击回放 -返回对应文件信息
     * @param path
     * @param mmap
     * @return
     * @throws Exception 
     */
    @GetMapping("/getFile")
    @ResponseBody
    public String getFile(String path) throws Exception
    {
    	if(path.indexOf("?")>0){
    		path=path.substring(0,path.lastIndexOf("?"));//去除后面携带时间戳等参数
    	}
    	path = DESUtils.decrypt(path);//解密
    	log.debug("EyeRecordPlayController.getFile : path={}",path);
		return fileStreamService.readFileContent(path);
    }
    
    /**
     * 获取图片
     * @param path
     * @param response
     * @throws Exception 
     */
    @GetMapping("/getImage")
	public void getImage(String path,HttpServletResponse response,HttpServletRequest request) {
		try{
			if(path.indexOf("?")>0){
				path = path.substring(0,path.lastIndexOf("?"));// 去除后面携带时间戳等参数
			}
			path = DESUtils.decrypt(path);// 解密
			log.debug("EyeRecordPlayController.getImage : path={}",path);
			String fileName = FileUtils.getFileNameByPath(path);
			String strContentType = FileUtils.getContentType(fileName);
			response.setCharacterEncoding(Constants.UTF8);
			response.setContentType(strContentType);
			response.setHeader("Content-Disposition","attachment;fileName="+FileUtils.setFileDownloadHeader(request,fileName));
			byte[] fileData = fileStreamService.readFileBytes(path);
			FileUtils.writeBytes(fileData,response.getOutputStream());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
    /**
     * 跳转至查看版本html页面
     * @param path
     * @param mmap
     * @return
     * @throws Exception
     * @modified:
     * ☆XiaoQuan(2020年7月24日 ): <br>
     */
    @GetMapping("/toViewPage")
    public String toViewPage(String path, ModelMap mmap) throws Exception
    {
    	String htmlContent = getFile(path);
        mmap.put("htmlContent", htmlContent);
		return "eye/pageversion/pageVersionShow";
    }
    
	/**
	 * 跳转到录屏播放页面
	 * @throws Exception
	 */
	@GetMapping("/toVideo")
	public String toVideo(String token,String productCode,String type,String playId,ModelMap mmap) throws Exception {
		log.debug("EyeRecordPlayController.toPlayPage : token={},productCode={},type={}",token,productCode,type);
		List<EyeOrderPage> list = new ArrayList<EyeOrderPage>();

		if("recordPage".equals(type)){
			EyeRecordPage eyeRecordPage = new EyeRecordPage();
			eyeRecordPage.setToken(token);
			eyeRecordPage.setProductCode(productCode);
			List<EyeRecordPage> recordPageList = eyeRecordPageService.selectEyeRecordPageList(eyeRecordPage);
			for(EyeRecordPage recordPage:recordPageList){
				EyeOrderPage eyeOrderPage = new EyeOrderPage();
				BeanUtils.copyBeanProp(eyeOrderPage,recordPage);
				list.add(eyeOrderPage);
			}
		}else if("orderPage".equals(type)){
			EyeOrderPage eyeOrderPage = new EyeOrderPage();
			eyeOrderPage.setToken(token);
			list = eyeOrderPageService.selectEyeOrderPageList(eyeOrderPage);
		}

		if(list!=null&&list.size()>0){
			for(EyeOrderPage orderPage:list){
				if(StringUtils.isNotBlank(orderPage.getFilePath())&&playId.equals(orderPage.getId().toString())){
					mmap.put("playerFilePath",DESUtils.encrypt(orderPage.getFilePath()));
					break;
				}
			}
		}

		mmap.put("playId",playId);
		return "eye/player/webToVideo";
	}
}
