package ins.webeye.project.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hutool.core.util.RandomUtil;
import ins.webeye.common.utils.DateUtils;

/**
 * 表单相关
 * 
 * @author webeye
 */
@Controller
@RequestMapping("/demo/eyes")
public class DemoEyeSRecordController
{

	private String prefix = "demo/eyes";


    /**
     * 按钮页
     */
	@GetMapping("/productBrowse")
	public String productBrowse(ModelMap mmap,HttpServletRequest request)
    {
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
	public String paymentEnd(String orderId,ModelMap mmap) {
		mmap.addAttribute("orderId",orderId);
		return prefix+"/paymentEnd";
	}


}
