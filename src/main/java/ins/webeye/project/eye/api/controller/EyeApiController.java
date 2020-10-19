package ins.webeye.project.eye.api.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ins.webeye.project.eye.order.domain.EyeOrder;
import ins.webeye.project.eye.order.service.IEyeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import ins.webeye.framework.aspectj.lang.annotation.Log;
import ins.webeye.framework.aspectj.lang.enums.BusinessType;
import ins.webeye.framework.config.ServerConfig;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.project.eye.api.service.EyeApiService;
import ins.webeye.project.eye.api.service.EyeRecordTokenService;
import ins.webeye.project.eye.app.service.IEyeAppService;
import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.vo.OrderInfoVo;
import ins.webeye.project.eye.vo.TokenRequestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/**
 * 回溯系统对外接口控制
 */
@Api("回溯平台内网API")
@Slf4j
@RestController
@RequestMapping("/webeye/api")
public class EyeApiController extends BaseController
{
	@Autowired
    private IEyeAppService eyeAppService;
	@Autowired
	private EyeApiService eyeApiService;
	@Autowired
	private EyeRecordTokenService eyeRecordTokenService;
	@Autowired
	private ServerConfig serverConfig;
	@Autowired
	private IEyeOrderService iEyeOrderService;


	@ApiOperation(value="获取录制凭证token",response =AjaxResult.class)
	@ApiImplicitParam(name = "requestVo", value = "获取Token时的请求信息", dataType = "TokenRequestVo")
	@Log(title = "获取Token", businessType = BusinessType.GRANT)
	@PostMapping(value = "/getToken", produces = "application/json")
	public AjaxResult getToken(@RequestBody @Valid TokenRequestVo requestVo, HttpServletRequest request) throws Exception {

		log.debug("EyeApiController.getToken : {}",JSONObject.toJSONString(requestVo));


		String systemCode = requestVo.getSystemCode();

		try{
			AjaxResult checkReslut = eyeApiService.checkClient(systemCode,requestVo.getConsumerID(),requestVo.getConsumerPWD());
			if(checkReslut!=null) return checkReslut;

			EyeRecordPage recordPage = eyeRecordTokenService.createToken(requestVo);
			String token = recordPage.getToken();
			boolean cacheSuccess = eyeRecordTokenService.saveToken(recordPage);
			if(cacheSuccess==false){
				return AjaxResult.error("回溯平台缓存服务异常，请联系运维人员。");
			}
			String baseUrl = serverConfig.getApiUrl();
			String sdkURL = baseUrl+"/webeye/sdk/webrecord.js?token="+token+"&sysCode="+systemCode;
			Map<String,String> responeMap = new HashMap<String,String>();
			responeMap.put("token",token);
			responeMap.put("sdkURL",sdkURL);
			log.debug("EyeApiController.getToken return: {}",JSONObject.toJSONString(responeMap));
			return AjaxResult.success("成功",responeMap);
		}catch(Exception e){
			log.error("EyeApiController.getToken error: "+e.getMessage(),e);
			return AjaxResult.error("getToken Error:"+e.getMessage());
		}

    }


	@ApiOperation(value="订单信息回写",response =AjaxResult.class)
	@ApiImplicitParam(name = "requestVo", value = "订单信息回写接口的请求信息", dataType = "OrderInfoVo")
	@Log(title = "订单回写", businessType = BusinessType.UPDATE)
	@PostMapping(value = "/syncOrderInfo", produces = "application/json")
	public AjaxResult syncOrderInfo(@RequestBody @Valid OrderInfoVo requestVo, HttpServletRequest request) throws Exception {
		// String ip = IpUtils.getIpAddr(request);
		// log.debug("call getToken api,IP={}",ip);
		log.info("EyeApiController.syncOrderInfo : {}",JSONObject.toJSONString(requestVo));
		try{
			AjaxResult checkReslut = eyeApiService.checkClient(requestVo.getSystemCode(),requestVo.getConsumerID(),requestVo.getConsumerPWD());
			if(checkReslut!=null) return checkReslut;
			int updateCount = eyeAppService.syncOrderInfo(requestVo);
			if(updateCount<1){
				String errorMsg = "同步失败:未找到相关录制记录，请确认参数:orderId="+requestVo.getOrderId();
				log.error("\"EyeApiController.syncOrderInfo Error:"+errorMsg);
				return AjaxResult.error(errorMsg);
			}

			return AjaxResult.success();
		}catch(Exception e){
			log.error("syncOrderInfo Error:"+e.getMessage(),e);
			return AjaxResult.error("syncOrderInfo Error:"+e.getMessage());
		}
    }

	@ApiOperation(value="测试",response =AjaxResult.class)
	@Log(title = "测试", businessType = BusinessType.UPDATE)
	@PostMapping(value = "/syncOrderInfo2", produces = "application/json")
	public AjaxResult syncOrderInfo2(HttpServletRequest request) throws Exception {
		// String ip = IpUtils.getIpAddr(request);
		// log.debug("call getToken api,IP={}",ip);
		try{
//			List<EyeOrder> list = iEyeOrderService.list();
			EyeOrder list = iEyeOrderService.selectEyeOrderById(1L);
			list = iEyeOrderService.getById(1L);
			list = iEyeOrderService.getOne(new QueryWrapper<EyeOrder>()
					.eq("id",1L)
			);
			return AjaxResult.success(list);
		}catch(Exception e){
			log.error("syncOrderInfo Error:"+e.getMessage(),e);
			return AjaxResult.error("syncOrderInfo Error:"+e.getMessage());
		}
	}


}

