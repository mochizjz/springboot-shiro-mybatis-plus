package ins.webeye.project.eye.api.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import ins.webeye.common.utils.IpUtils;
import ins.webeye.framework.shiro.service.PasswordService;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.project.eye.api.service.EyeTencentCloudApiService;
import ins.webeye.project.eye.vo.TencentCloudApiVo;
import ins.webeye.project.system.user.domain.User;
import ins.webeye.project.system.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 回溯系统腾讯云接口控制
 */
@Api("回溯平台腾讯云API")
@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/webeye/tencentCloudApi")
public class EyeTencentCloudApiController extends BaseController
{				
	@Autowired
	private IUserService userService;
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private EyeTencentCloudApiService tencentCloudApiService;
	
	/**
	 * 人脸核验-获取动作顺序(暂未使用，使用需要调整规范)
	 * @return
	 * @modified:
	 * ☆XiaoQuan(2020年8月13日 ): <br>
	 */
	@GetMapping("/getActionSequence")
	@ResponseBody
	public AjaxResult getActionSequence(){
		 try{
			String result = tencentCloudApiService.getActionSequence();
			log.debug("EyeTencentCloudApiController.getActionSequence :result={}",result);
            return AjaxResult.success("成功",result);
	     } catch (TencentCloudSDKException e) {
	    	log.error("EyeTencentCloudApiController.getActionSequence Error:{}",e);
            return AjaxResult.error("EyeTencentCloudApiController.getActionSequence Error :",e.toString());
	     }
	}
	/**
	 * 腾讯人脸核验接口(暂未使用，使用需要调整规范)
	 * @param jsonData
	 * @return
	 * @modified:
	 * ☆XiaoQuan(2020年8月13日 ): <br>
	 */
	@PostMapping("/livenessRecognition")
    @ResponseBody
	public AjaxResult livenessRecognition(@RequestParam("jsonData") String jsonData){
		 try{
			log.debug("EyeTencentCloudApiController.livenessRecognition...");
			String result = tencentCloudApiService.livenessRecognition(jsonData);
	        return AjaxResult.success("成功",result);
	     } catch (TencentCloudSDKException e) {
	    	log.error("EyeTencentCloudApiController.getActionSequence Error:{}",e);
	    	return AjaxResult.error("EyeTencentCloudApiController.getActionSequence Error :",e.toString());
	     }
	}
	
	
	
	@ApiOperation(value="请求实名核身鉴权",response = AjaxResult.class)
	@PostMapping(value = "/getDetectAuth", produces = "application/json")
	@ApiImplicitParam(name = "tencentCloudApiVo", value = "活体认证接口的请求信息", dataType = "TencentCloudApiVo")
	public AjaxResult getDetectAuth(@RequestBody @Valid TencentCloudApiVo tencentCloudApiVo, HttpServletRequest request) {
		String ip = IpUtils.getIpAddr(request);
		log.debug("EyeTencentCloudApiController getDetectAuth api,IP={}",ip);
		String consumerID = tencentCloudApiVo.getConsumerID();
		String consumerPWD = tencentCloudApiVo.getConsumerPWD();
		try{
			User user = userService.selectUserByLoginName(consumerID);
			if(user==null){
				return AjaxResult.error("消费方ID不存在："+consumerID);
			}
			passwordService.validate(user,consumerPWD);
			String detectStr = tencentCloudApiService.getDetectAuth(tencentCloudApiVo);
			JSONObject jsonJObj = JSON.parseObject(detectStr);
			return AjaxResult.success(jsonJObj);
		}catch(Exception e){
			log.error("getDetectAuth Error:IP={}, ",e);
			return AjaxResult.error("EyeTencentCloudApiController.getDetectAuth Error:"+e.getMessage());
		}
	}
	
	
	@ApiOperation(value="获取实名核身结果信息",response =AjaxResult.class)
	@PostMapping(value = "/getDetectInfo", produces = "application/json")
	@ApiImplicitParam(name = "tencentCloudApiVo", value = "获取实名核身结果信息", dataType = "TencentCloudApiVo")
	public AjaxResult getDetectInfo(@RequestBody @Valid TencentCloudApiVo tencentCloudApiVo, HttpServletRequest request) {
		String ip = IpUtils.getIpAddr(request);
		log.debug("EyeTencentCloudApiController getDetectInfo api,IP={}",ip);
		String consumerID = tencentCloudApiVo.getConsumerID();
		String consumerPWD = tencentCloudApiVo.getConsumerPWD();
		try{
			User user = userService.selectUserByLoginName(consumerID);
			if(user==null){
				return AjaxResult.error("消费方ID不存在："+consumerID);
			}
			passwordService.validate(user,consumerPWD);
			String detectInfo = tencentCloudApiService.getDetectInfo(tencentCloudApiVo);
			JSONObject jsonJObj = JSON.parseObject(detectInfo);
			return AjaxResult.success(jsonJObj);
		}catch(Exception e){
			log.error("getDetectInfo Error:IP={}, ",e);
			return AjaxResult.error("EyeTencentCloudApiController.getDetectInfo Error:"+e.getMessage());
		}
	}
	
}

