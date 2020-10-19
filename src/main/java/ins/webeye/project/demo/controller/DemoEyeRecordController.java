package ins.webeye.project.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.util.RandomUtil;
import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.http.HttpUtils;
import ins.webeye.framework.config.ServerConfig;
import ins.webeye.project.eye.vo.TokenRequestVo;

/**
 * 表单相关
 * 
 * @author webeye
 */
@Controller
@RequestMapping("/demo/eye")
public class DemoEyeRecordController
{

	private String prefix = "demo/eye";

	@Autowired
	private ServerConfig serverConfig;
    /**
     * 按钮页
     */
	@GetMapping("/productBrowse")
	public String productBrowse(ModelMap mmap,HttpServletRequest request)
    {
		String webeyeUrl = serverConfig.getApiUrl()+"/webeye/api/getToken";
		TokenRequestVo requestVo = new TokenRequestVo();
		requestVo.setSystemCode("xiandai-h5");
		requestVo.setConsumerID("xiandai-test");
		requestVo.setConsumerPWD("32ea8a19bc56abd927ee37dc19d4081b");
		requestVo.setProductCode("6102_v3");
		requestVo.setProductName("安康福医疗险");

		JSONObject result = HttpUtils.sendPostJson(webeyeUrl,requestVo);
		// mmap.addAttribute("eyeToken",result.getJSONObject("data").getString("token"));
		// mmap.addAttribute("eyeSdkURL",result.getJSONObject("data").getString("sdkURL"));
		request.getSession().setAttribute("eyeSdkURL",result.getJSONObject("data").getString("sdkURL"));
		return prefix+"/productBrowse";
    }

    /**
     * 下拉框
     */
	@GetMapping("/healthInform")
	public String healthInform()
    {
		return prefix+"/healthInform";
    }

	@GetMapping("/insureInput")
	public String insureInput() {
		return prefix+"/insureInput";
	}

	@GetMapping("/insureInputSave")
	public String insureInputSave(ModelMap mmap) {
		// 订单提交时产生一个订单和和投保单好 RandomUtil.randomInt
		String policyNo = "1101500"+DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS)+RandomUtil.randomInt(1000,9999);
		String orderId = "24012020"+DateUtils.dateTimeNow("ddHHmmss")+RandomUtil.randomInt(10000,99999);
		mmap.addAttribute("policyNo",policyNo);
		mmap.addAttribute("orderId",orderId);
		return prefix+"/insureInputSave";
	}

	@GetMapping("/paymentSit")
	public String paymentSit() {
		return prefix+"/paymentSit";
	}

	@GetMapping("/paymentEnd")
	public String paymentEnd() {
		return prefix+"/paymentEnd";
	}

	@GetMapping("/faceId")
	public String faceId(ModelMap mmap,HttpServletRequest request)
    {
		return prefix+"/faceId";
    }
	
	@GetMapping("/faceIdResult")
	public String faceIdResult(ModelMap mmap,HttpServletRequest request)
    {
		return prefix+"/faceIdResult";
    }
}
