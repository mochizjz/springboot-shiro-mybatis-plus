package ins.webeye.project.eye.api.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ins.webeye.common.constant.ApiConstants;
import ins.webeye.common.utils.IpUtils;
import ins.webeye.framework.config.ServerConfig;
import ins.webeye.framework.shiro.service.PasswordService;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.project.eye.api.service.EyeALiCloudApiService;
import ins.webeye.project.eye.api.service.EyeApiService;
import ins.webeye.project.eye.api.service.EyeTencentCloudApiService;
import ins.webeye.project.eye.api.service.IEyeSaasApiService;
import ins.webeye.project.eye.order.domain.EyeOrder;
import ins.webeye.project.eye.order.service.IEyeOrderService;
import ins.webeye.project.eye.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;


/**
 * 回溯系统对外API
 */
@Api("回溯系统对外API")
@Slf4j
@RestController
@RequestMapping("/webeye/auth/api")
public class EyeAuthApiController extends BaseController {

	@Autowired
	private EyeApiService eyeApiService;
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private IEyeSaasApiService saasAppService;
	@Autowired
	private EyeALiCloudApiService eyeALiCloudApiService;
	@Autowired
	private EyeTencentCloudApiService tencentCloudApiService;
	@Autowired
	private IEyeOrderService orderService;
	@Autowired
	private ServerConfig serverConfig;


	@ApiOperation(value="回溯系统对外API统一入口",response = AjaxResult.class)
	@PostMapping(value = "/getDetectInfo", produces = "application/json")
	@ApiImplicitParam(name = "authApiVo", value = "统一入口请求信息", dataType = "AuthApiVo")
	public AjaxResult getDetectInfo(@RequestBody @Valid AuthApiVo authApiVo, HttpServletRequest request) {
		String ip = IpUtils.getIpAddr(request);
		log.debug("call getDetectAuth api,IP={}",ip);

		String systemCode = authApiVo.getSystemCode();

		try{
			//安全key验证
			AjaxResult checkReslut = eyeApiService.checkClient(systemCode,authApiVo.getConsumerID(),authApiVo.getConsumerPWD());
			if(checkReslut!=null) {
				return checkReslut;
			}

			//接口类型、接口方法验证
			Map<String,Object> dataMap = JSON.parseObject(authApiVo.getJsonData().toString());
			if(null==dataMap.get("businessType") || null==dataMap.get("businessMethod")){
				return AjaxResult.error("接口类型或方法名不能为空");
			}
			String businessType = dataMap.get("businessType").toString();
			String businessMethod = dataMap.get("businessMethod").toString();
			log.info("第三方认证接口类型：{},方法名{}",businessType,businessMethod);

			/***********************************第三方认证接口分发开始******************************************/
			if(businessType.equals(ApiConstants.TENCENT_SAAS)){
				SaasVo saasVo = BeanUtil.mapToBean(dataMap,SaasVo.class,true);
				if(businessMethod.equals(ApiConstants.TENCENT_DETECT_AUTH)){
					DetectAuthVo info = saasAppService.getDetectAuth(saasVo);
					return AjaxResult.success("请求成功",info);
				}else if(businessMethod.equals(ApiConstants.TENCENT_DETECT_INFO)){
					TecentAuthVo info = saasAppService.getDetectInfo(saasVo);
					return AjaxResult.success("请求成功",info);
				}else{
					return AjaxResult.error("tencent_saas:businessMethod error");
				}
			}else if(businessType.equals(ApiConstants.TENCENT_VERIFY)){
				if(businessMethod.equals(ApiConstants.TENCENT_VERIFY_IDCARD)){//腾讯-身份验证
					TencentVerifyVo verifyVo = BeanUtil.mapToBean(dataMap,TencentVerifyVo.class,true);
					return AjaxResult.success("请求成功",tencentCloudApiService.idCardVerification(verifyVo));
				}else{
					return AjaxResult.error("tencent_verify:businessMethod error");
				}
			}else if(businessType.equals(ApiConstants.ALI_OCR)){
				if(businessMethod.equals(ApiConstants.ALI_OCR_INFO)){
					OcrVo ocrVo = BeanUtil.mapToBean(dataMap,OcrVo.class,true);
					JSONObject jsonObject = eyeALiCloudApiService.getOcrAuthInfo(ocrVo);
					if(jsonObject.get("stat")!=null){
						return AjaxResult.error(jsonObject.getString("errMsg"));
					}
					return AjaxResult.success("请求成功",jsonObject);
				}else{
					return AjaxResult.error("ali_ocr:businessMethod error");
				}
				/***********************************第三方认证接口分发结束******************************************/

			}else if(businessType.equals(ApiConstants.BUSSINESS_TYPE_TPKJ)){
				/***********************************第三方接口提供回放地址开始********************************************/
				if(businessMethod.equals(ApiConstants.BUSSINESS_METHOD_TPKJ_PLAYBACK)){
					String policyNo = dataMap.get("policyNo").toString();
					EyeOrder eyeOrder = orderService.selectEyeOrderByPolicyNo(policyNo);
					if(null == eyeOrder){
						return AjaxResult.error("根据："+policyNo+" 未查询到此订单");
					}else{
						String url = serverConfig.getUrl()+"/eye/recordPlay/toPlayPage?token="+eyeOrder
								.getToken()
								+"&type=orderPage&productCode="+eyeOrder.getProductCode();
						return AjaxResult.success("请求成功",url);
					}
				}else{
					return AjaxResult.error("tpkj:tpkj_view_playback error");
				}
				/***********************************第三方接口提供回放地址结束********************************************/
			}else{
				return AjaxResult.error("此业务类型接口暂未开发");
			}
		}catch(Exception e){
			log.error("getDetectInfo Error:IP={}, ",e);
			return AjaxResult.error("getDetectInfo Error:"+e.getMessage());
		}
	}

  

}

