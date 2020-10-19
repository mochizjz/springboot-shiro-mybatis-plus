package ins.webeye.project.eye.app.controller;



import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import ins.webeye.common.constant.RedisConstants;
import ins.webeye.common.utils.DESUtils;
import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.IpUtils;
import ins.webeye.common.utils.RedisUtil;
import ins.webeye.common.utils.ServletUtils;
import ins.webeye.common.utils.file.FileUploadUtils;
import ins.webeye.common.utils.file.FileUtils;
import ins.webeye.framework.manager.AsyncManager;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.project.eye.api.service.EyeRecordTokenService;
import ins.webeye.project.eye.app.service.EyeRecordFileService;
import ins.webeye.project.eye.app.service.IEyeAppService;
import ins.webeye.project.eye.pageconfig.domain.EyePageConfig;
import ins.webeye.project.eye.pageconfig.service.IEyePageConfigService;
import ins.webeye.project.eye.pageversion.domain.EyePageVersion;
import ins.webeye.project.eye.pageversion.service.IEyePageVersionService;
import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.vo.TokenRequestVo;
import ins.webeye.project.eye.vo.UrlHanledVo;
import ins.webeye.project.eye.vo.WebEyeSdkConfigVo;
import ins.webeye.project.storage.FileStreamService;
import lombok.extern.slf4j.Slf4j;

/**
 * 回溯系统内部接口控制
 * @author xiaoquan
 * @date 2020-07-14
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@Slf4j
@RequestMapping("/webeye")
public class EyeAppController extends BaseController
{
	@Autowired
	private RedisUtil redisUtil;

	@Autowired
    private IEyeAppService eyeAppService;
	@Autowired
	private IEyePageVersionService eyePageVersionService;
	@Autowired
	private IEyePageConfigService eyePageConfigService;
	@Autowired
	private EyeRecordTokenService eyeRecordTokenService;
	@Autowired
	private FileStreamService fileStreamService;
	@Autowired
	private EyeRecordFileService recordFileService;
	
	private static final String DEFAULT_CHARSET_NAME = "UTF-8";
	
	private static final String operator = "admin";

    /**
	 * 获取SDK js文件,在第一行写入WebEyeConfig配置信息
	 * @param token
	 * @param sysCode
	 * @return sdk的JS内容
	 * @throws Exception
	 */
    @GetMapping("/sdk/webrecord.js")
    @ResponseBody
	public String getWebRecordJS(HttpServletRequest request,String token,String sysCode) throws Exception {
		String webrecordStr = "";
		String sdkPath = "sdk/webrecord.js";
		WebEyeSdkConfigVo sdkConfigVo = eyeAppService.getEyeSdkConfigVo(sysCode);

		if(StringUtils.isNotBlank(token)){// js带了token
			// if(token.startsWith("test_")){// 用来 临时 测试
			// EyeRecordPage eyeRecordPage = new EyeRecordPage();
			// eyeRecordPage.setSysCode(sysCode);
			// eyeRecordPage.setProductCode("test001");
			// eyeRecordPage.setProductName("测试产品1");
			// eyeRecordPage.setUserId("test");
			// eyeRecordPage.setToken(token);
			// eyeRecordPage.setCreateTime(new Date());
			// eyeRecordTokenService.saveToken(eyeRecordPage);
			// }
			sdkConfigVo.setToken(token);
		}
		//sdkConfigVo.setGzip(true);
		String sdkConfigJson = JSONObject.toJSONString(sdkConfigVo);
		log.debug("EyeAppController.getWebrecord.sdkConfigJson= {}",sdkConfigJson);

		webrecordStr = "window.WebEyeConfig="+sdkConfigJson+";";

		Resource resource = new ClassPathResource(sdkPath);
		StringBuffer buffer = FileUtils.readStreamAsBuffer(resource.getInputStream());
		buffer.append(webrecordStr);// 在开始插入

		return buffer.toString();
	}
    
    /**
     * 保存录制信息
     * @param request
     * @param eyeRecordPage
     * @return
     * @throws Exception 
     */
    @PostMapping("/app/startRecord")
    @ResponseBody
	public AjaxResult startRecord(HttpServletRequest request,HttpServletResponse response,EyeRecordPage eyeRecordPage) throws Exception
    {
		log.info("EyeAppController.startRecord : {}",JSONObject.toJSONString(eyeRecordPage));

    	String token = eyeRecordPage.getToken();
    	String orderId =eyeRecordPage.getOrderId();
    	String productCode =eyeRecordPage.getProductCode();
    	String productName =eyeRecordPage.getProductName();
    	String userId =eyeRecordPage.getUserId();
    	String nodeCode =eyeRecordPage.getNodeCode();
    	String operType =eyeRecordPage.getOperType();
    	String nodeUrl =eyeRecordPage.getNodeUrl();
    	String sysCode =eyeRecordPage.getSysCode();
    	String sdkType =eyeRecordPage.getSdkType();
		Boolean newToken = eyeRecordPage.getNewToken();

		if(StringUtils.isNotBlank(nodeUrl)&&"S".equals(sdkType)&&StringUtils.isBlank(nodeCode)){
			// 简版，没有传nodeCode，根据nodeUrl 去页面配置中找nodeCode
			EyePageConfig pageManage = eyePageConfigService.findEyePageConfig(sysCode,nodeUrl);
			if(pageManage!=null){
				nodeCode = pageManage.getNodeCode();
    		}else{
				return AjaxResult.error("参数错误:sysCode="+sysCode+",nodeUrl="+nodeUrl+",未找到对应页面配置");
    		}
    	}
		if(StringUtils.isNotBlank(nodeUrl)&&nodeUrl.length()>180){
			nodeUrl = nodeUrl.substring(0,180);// 最长只保留500
			eyeRecordPage.setNodeUrl(nodeUrl);
		}

		if(StringUtils.isBlank(nodeCode)) return AjaxResult.error("参数错误:节点编码nodeCode不能为空");
		if(StringUtils.isBlank(operType)) return AjaxResult.error("内置参数错误:操作类型operType不能为空");

		EyeRecordPage recordPageCache = new EyeRecordPage();
		if(newToken==null) newToken = true;// 默认都是产生新的token
		if("ProductBrowse".equals(nodeCode)&&"S".equals(sdkType)&&newToken){
			// S简单模式，在产品浏览的时候，每次创建个新的token，并写入cookie；
			if(StringUtils.isBlank(productCode)) return AjaxResult.error("参数错误:产品编码productCode不能为空@ProductBrowse");
			TokenRequestVo tokenVo = new TokenRequestVo();
			tokenVo.setSystemCode(sysCode);
			tokenVo.setProductCode(productCode);
			tokenVo.setProductName(productName);
			tokenVo.setOrderId(orderId);// 这里应该是空的
			tokenVo.setUserId(userId);
			recordPageCache = eyeRecordTokenService.createToken(tokenVo);
			token = recordPageCache.getToken();
			eyeRecordTokenService.saveToken(recordPageCache);
			response.addCookie(new Cookie("eye_token",token));
		}
		if(StringUtils.isBlank(token)&&StringUtils.isBlank(orderId)){
			return AjaxResult.error("参数错误:sysCode="+sysCode+",nodeCode="+nodeCode+",sdkType="+sdkType+",未找到token或orderId参数");
    	}else{
			/*if(token.startsWith("test_")&& "ProductBrowse".equals(nodeCode) && !eyeRecordTokenService.checkToken(token,productCode)){//用来测试
				eyeRecordPage.setProductCode(productCode);
				eyeRecordPage.setProductName(productName);
				eyeRecordTokenService.saveToken(eyeRecordPage);
			}*/
    		//if(StringUtils.isBlank(token))return AjaxResult.error("失败:token不能为空");
    		recordPageCache = eyeRecordTokenService.getTokenCache(token,productCode,orderId);
			if(recordPageCache==null) return AjaxResult.error("录制启动失败:token+productCode或orderId不存在");
    	}


    	//如果为空则从缓存里面获取
		//orderId = StringUtils.isBlank(orderId) ? recordPageCache.getOrderId() : orderId;
		productCode = StringUtils.isBlank(productCode) ? recordPageCache.getProductCode() : productCode;
		productName = StringUtils.isBlank(productName) ? recordPageCache.getProductName() : productName;
		userId = StringUtils.isBlank(userId) ? recordPageCache.getUserId() : userId;
		sysCode = StringUtils.isBlank(sysCode) ? recordPageCache.getSysCode() : sysCode;
		token = ( StringUtils.isBlank(token)|| !token.equals(recordPageCache.getToken()) ) ? recordPageCache.getToken() : token;// 根据订单号得到token
		if(StringUtils.isBlank(productCode)) return AjaxResult.error("参数错误:产品编码productCode不能为空");
		Long startTime = System.currentTimeMillis();
		if(recordPageCache.getCreateTime()!=null){
			startTime = recordPageCache.getCreateTime().getTime();
		}

		// 获取重新设值 IP 市级地址 和浏览器
    	String ip = IpUtils.getIpAddr(request);
		String cityName = eyeAppService.getCityName(ip);
		String browser = ServletUtils.getBrowserName(request);

    	eyeRecordPage.setClientIp(ip);
		eyeRecordPage.setClientCityName(cityName);
		eyeRecordPage.setClientBrowser(browser);
		eyeRecordPage.setSysCode(sysCode);
		// eyeRecordPage.setOrderId(orderId); 不用重新赋值，前端给的什么就是什么
    	eyeRecordPage.setProductCode(productCode);
    	eyeRecordPage.setProductName(productName);
    	eyeRecordPage.setUserId(userId);
    	eyeRecordPage.setRecordTime(0);//默认为0
    	eyeRecordPage.setNodeCode(nodeCode);
    	eyeRecordPage.setToken(token);
    	eyeRecordPage.setCreateUser(operator);
		eyeRecordPage.setPlaySetTime(System.currentTimeMillis()-startTime);

    	try{
    		eyeRecordPage = eyeAppService.startRecord(eyeRecordPage);
			if("0".equals(operType)){// 子节点操作:只保存操作数据,为了前端不报错，将路径返回回去
    			eyeRecordPage.setFilePath(recordPageCache.getFilePath());
    		}
        	return AjaxResult.success(eyeRecordPage);
    	}catch(Exception e){
			log.error("EyeAppController.startRecord Error:",e);
			return AjaxResult.error("录制启动失败:"+e.getMessage());
		}
    	
    }
    
    /**
	 * 结束订单，可能会传投保人信息
	 * @param eyeRecordPage
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/app/endRecord")
    @ResponseBody
    public AjaxResult endRecord(EyeRecordPage eyeRecordPage) throws Exception 
    {
		log.debug("EyeAppController.endRecord : {}",JSONObject.toJSONString(eyeRecordPage));
    	String token = eyeRecordPage.getToken();
    	String orderId =eyeRecordPage.getOrderId();
    	String productCode =eyeRecordPage.getProductCode();
    	String productName =eyeRecordPage.getProductName();
    	String userId =eyeRecordPage.getUserId();
    	String nodeCode =eyeRecordPage.getNodeCode();
		// String operType =eyeRecordPage.getOperType();
		// String policyNo=eyeRecordPage.getPolicyNo();//保单号
		// String policyName=eyeRecordPage.getPolicyName();//投保人姓名
		// String policyIdcard=eyeRecordPage.getPolicyIdcard();//投保人证件

    	if(StringUtils.isBlank(nodeCode))return AjaxResult.error("失败:节点不能为空");
		EyeRecordPage recordPageCache = new EyeRecordPage();
		recordPageCache = eyeRecordTokenService.getTokenCache(token,productCode,orderId);
		if(recordPageCache==null) return AjaxResult.error("失败:token不存在或已失效");

    	//如果为空则从缓存里面获取
		orderId = StringUtils.isBlank(orderId) ? recordPageCache.getOrderId() : orderId;
		productCode = StringUtils.isBlank(productCode) ? recordPageCache.getProductCode() : productCode;
		productName = StringUtils.isBlank(productName) ? recordPageCache.getProductName() : productName;
		userId = StringUtils.isBlank(userId) ? recordPageCache.getUserId() : userId;
		token = StringUtils.isBlank(token) ? recordPageCache.getToken() : token;// 根据订单号得到token

		if(StringUtils.isBlank(productCode)) return AjaxResult.error("失败:产品编码不能为空");
    	
		// policyNo = StringUtils.isBlank(policyNo) ? recordPageCache.getPolicyNo() : policyNo;
		// policyName = StringUtils.isBlank(policyName) ? recordPageCache.getPolicyName() : policyName;
		// policyIdcard = StringUtils.isBlank(policyIdcard) ? recordPageCache.getPolicyIdcard() : policyIdcard;
		// operType = StringUtils.isBlank(operType) ? recordPageCache.getOperType() : operType;
    	
    	//重新设值
		eyeRecordPage.setOrderId(orderId);
    	eyeRecordPage.setProductCode(productCode);
    	eyeRecordPage.setProductName(productName);
    	eyeRecordPage.setUserId(userId);
		// eyeRecordPage.setOperType(operType);
		eyeRecordPage.setToken(token);
    	eyeRecordPage.setUpdateUser(operator);
    	try{
    		eyeRecordPage = eyeAppService.endRecord(eyeRecordPage);
        	return AjaxResult.success();
    	}catch(Exception e){
			log.error("EyeAppController.startRecord Error:",e);
			return AjaxResult.error("startRecord Error:"+e.getMessage());
		}
    }
   
    
    
    /**
     * 保存文件信息(追加文件信息)
     * @param request
     * @param token
     * @param path
     * @return
     * @throws Exception 
     */
    @PostMapping("/app/saveFile")
    @ResponseBody
	public AjaxResult saveFile(HttpServletRequest request,String token,String productCode,String path,String orderId,String f) throws Exception
    {	
		if(StringUtils.isBlank(token)&&StringUtils.isBlank(orderId)) return AjaxResult.error("录制失败:token和orderId不能同时为空");
		if(StringUtils.isBlank(path)) return AjaxResult.error("录制失败:path不能为空");
    	path = DESUtils.decrypt(path);//解密
		// log.debug("EyeAppController.saveFile : token={},path={},fileType={}",token,path,f);
        try {
			recordFileService.appendFileData(path,request,f);
        } catch (Exception e) {
			log.error(token+" EyeAppController.saveFile error ",e);
    		return AjaxResult.error(e.getMessage());
    	}
    	return AjaxResult.success();
    }
    
    
    
    /**
     * 保存版本html
     * @param request
     * @param token
     * @param nodeCode
     * @param productCode
     * @param pageId
     * @param userId
     * @return
     * @throws Exception
     * @modified:
     * ☆XiaoQuan(2020年7月23日 ): <br>
     */
    @PostMapping("/app/saveHtml")
    @ResponseBody
	public AjaxResult saveHtml(	HttpServletRequest request,String token,String nodeCode,String productCode,String productName,String pageId,
								String nodeUrl,String userId,String orderId,String sysCode) throws Exception
    {	
    	log.debug("EyeAppController.saveHtml : token={},nodeCode={},sysCode={}",token,nodeCode,sysCode);
		// if(StringUtils.isBlank(token))return AjaxResult.error("失败:token不能为空");
    	if(StringUtils.isBlank(nodeCode))return AjaxResult.error("失败:nodeCode不能为空");
    	if(StringUtils.isBlank(productCode))return AjaxResult.error("失败:productCode不能为空");

    	
    	//流重复使用，将流装换成字节
        ServletInputStream stream = request.getInputStream();
        String requestBody = StreamUtils.copyToString(stream, Charset.forName(DEFAULT_CHARSET_NAME));
        byte[] requestBodyIniBytes = requestBody.getBytes(DEFAULT_CHARSET_NAME);
    	
		Map<String,String> htmlMap = (Map<String,String>)redisUtil.get(RedisConstants.FILE_HTML_CACHE);
    	if(htmlMap==null)htmlMap=new HashMap<String,String>();
    	
    	String md5Hex = "";
    	if(StringUtils.isNotBlank(pageId)){
			md5Hex = DigestUtils.md5Hex(productCode+"_"+nodeCode+"_"+pageId);
    	}else if(StringUtils.isNotBlank(nodeUrl)){//url版本，一天一个版本
    		nodeUrl= nodeUrl + DateUtils.getDate();
    		md5Hex = DigestUtils.md5Hex(nodeUrl);
    	}else{
    		md5Hex = DigestUtils.md5Hex(new ByteArrayInputStream(requestBodyIniBytes));//MD5
    	}
    	
    	if(htmlMap.containsKey(md5Hex))return AjaxResult.success();//缓存存在不需要存储
    	
    	EyePageVersion eyePageVersion = eyePageVersionService.checkFileMd5(md5Hex);
    	if(eyePageVersion!=null){//放入缓存
    		htmlMap.put(eyePageVersion.getFileMd5(),eyePageVersion.getFilePath());
    	}else{
    		String path = FileUploadUtils.getPathForRecordVersion(productCode,nodeCode,pageId,md5Hex + ".txt");
    		htmlMap.put(md5Hex,path);//放入缓存，一天清理一遍
    		
			// 将文件路径放入缓存，后面做url替换工作，替换完成后，会从缓存中删除
			redisUtil.set(RedisConstants.FILE_PATH_CACHE+path,1,10*60);
			AsyncManager.me().execute(eyeAppService.asynHandleTask(null,null,pageId+"#"+path,"fileHandle"),5*60);// TODO 5*60 暂时设置为5分钟后触发替换图片操作
    		
    		 //将文件记录至数据库MD5  filePath
            EyePageVersion pageVersion = new EyePageVersion();
            pageVersion.setFileMd5(md5Hex);
            pageVersion.setFilePath(path);
            pageVersion.setFileType("orgHtml");
            pageVersion.setCreateUser(operator);
            pageVersion.setProductCode(productCode);
            pageVersion.setProductName(productName);
            pageVersion.setPageId(pageId);
            pageVersion.setNodeCode(nodeCode);
            pageVersion.setSysCode(sysCode);
            eyePageVersionService.insertEyePageVersion(pageVersion);
            try {
				fileStreamService.uploadFileData(path,requestBodyIniBytes);
            } catch (Exception e) {
    			log.error(token+" EyeAppController.saveHtml error ",e);
        		return AjaxResult.error(e.getMessage());
        	}
    	}
		redisUtil.set(RedisConstants.FILE_HTML_CACHE,htmlMap,60*60*6);// 更新缓存
    	return AjaxResult.success();
    }
    
    
   /**
    * 保存截图
    * @param file
    * @param token
    * @param nodeCode
    * @return
    * @throws Exception
    */
    @PostMapping("/app/saveImage")
    @ResponseBody
	public AjaxResult saveImage(HttpServletRequest request,@RequestParam("imageFile") MultipartFile file,@RequestParam("token") String token,
								@RequestParam("nodeCode") String nodeCode,@RequestParam("productCode") String productCode,
								@RequestParam("orderId") String orderId) throws Exception
    {	
    	log.debug("EyeAppController.saveImage : token={},nodeCode={}",token,nodeCode);
        try {
        	//校验
			EyeRecordPage eyeRecordCache = saveImage(request,token,nodeCode,productCode,orderId);

			fileStreamService.uploadFileData(eyeRecordCache.getFilePath(),file.getBytes());

        } catch (Exception e) {
			log.error(token+" EyeAppController.saveImage error "+e.getMessage(),e);
    		return AjaxResult.error(e.getMessage());
        }
    	return AjaxResult.success();
    }
    
    /**
     * 处理截图数据
     * */
	private EyeRecordPage saveImage(HttpServletRequest request,String token,String nodeCode,String productCode,String orderId) throws Exception {
    	//校验
    	EyeRecordPage eyeRecordCache = new EyeRecordPage();
    	if(StringUtils.isBlank(nodeCode))throw new Exception("失败:节点不能为空");
    	if(StringUtils.isBlank(productCode))throw new Exception("失败:产品代码不能为空");
		eyeRecordCache = eyeRecordTokenService.getTokenCache(token,productCode,orderId);;
    	if(eyeRecordCache==null)throw new Exception("失败:token不存在或已失效");
    	eyeRecordCache.setProductCode(productCode);
    	eyeRecordCache.setNodeCode(nodeCode);
    	eyeRecordCache.setOperType("2");//操作类型:0-子节点  录制-1，截屏-2
    	eyeRecordCache.setCreateUser(operator);
    	// 获取重新设值 IP 市级地址 和浏览器
    	String ip = IpUtils.getIpAddr(request);
		String cityName = eyeAppService.getCityName(ip);
		String browser = ServletUtils.getBrowserName(request);
		eyeRecordCache.setClientIp(ip);
		eyeRecordCache.setClientCityName(cityName);
		eyeRecordCache.setClientBrowser(browser);
		eyeRecordCache = eyeAppService.startRecord(eyeRecordCache);
		return eyeRecordCache;
    }

    /**
     * base64方式保存文件
     * @param request
     * @param eyeRecordPage
     * @return
     */
    @PostMapping("/app/saveBase64")
    @ResponseBody
    public AjaxResult saveBase64(HttpServletRequest request,EyeRecordPage eyeRecordPage)
    {	
    	String base64Content = eyeRecordPage.getBase64Content();
    	String token = eyeRecordPage.getToken();
    	String nodeCode = eyeRecordPage.getNodeCode();
    	String productCode = eyeRecordPage.getProductCode();
		log.debug("EyeAppController.saveBase64 : {}",JSONObject.toJSONString(eyeRecordPage));

    	//校验
    	if(StringUtils.isBlank(base64Content))return AjaxResult.error("失败:base64Content不能为空");
        try {
			EyeRecordPage eyeRecordCache = saveImage(request,token,nodeCode,productCode,eyeRecordPage.getOrderId());

			Base64 base64 = new Base64();
			byte[] fileData = base64.decode(base64Content);

			fileStreamService.uploadFileData(eyeRecordCache.getFilePath(),fileData);

        } catch (Exception e) {
			log.error(token+" EyeAppController.saveBase64 error "+e.getMessage(),e);
    		return AjaxResult.error(e.getMessage());
        }
    	return AjaxResult.success();
    }
    
    
    
    /**
     * 接收图片url;处理图片
     * @param urlHanldVo
     * @return
     * @modified:
     * ☆XiaoQuan(2020年7月22日 ): <br>
     */
    @PostMapping("/app/receiveUrl")
    @ResponseBody
    public AjaxResult receiveUrl(@Valid UrlHanledVo urlHanldVo) 
    {
    	log.info("EyeAppController.receiveUrl ：token={}",urlHanldVo.getToken());
		if( !eyeRecordTokenService.checkToken(urlHanldVo.getToken(),urlHanldVo.getProductCode(),null)) return AjaxResult.error("失败:token不存在或已失效");
    	//异步执行获取文件的方法
    	AsyncManager.me().execute(eyeAppService.asynHandleTask(null,urlHanldVo,null,"urlHandle"));
    	return AjaxResult.success();
    }
    
    /**
     * 健康检查接口
     */
    @GetMapping("/health/check")
    @ResponseBody
    public String healthCheck()
    {
        return "200";
    }
}
