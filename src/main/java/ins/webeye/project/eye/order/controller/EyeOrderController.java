package ins.webeye.project.eye.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ins.webeye.common.utils.poi.ExcelUtil;
import ins.webeye.framework.aspectj.lang.annotation.Log;
import ins.webeye.framework.aspectj.lang.enums.BusinessType;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.framework.web.page.TableDataInfo;
import ins.webeye.project.eye.order.domain.EyeOrder;
import ins.webeye.project.eye.order.service.IEyeOrderPageService;
import ins.webeye.project.eye.order.service.IEyeOrderService;
import ins.webeye.project.eye.vo.QualityCheckVo;

/**
 * 订单记录Controller
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/eye/order")
public class EyeOrderController extends BaseController
{

	private String prefix = "eye/order";

    @Autowired
    private IEyeOrderService eyeOrderService;
    @Autowired
    private IEyeOrderPageService eyeOrderPageService;
    
    private static final Logger log = LoggerFactory.getLogger(EyeOrderController.class);
    
	@RequiresPermissions("eye:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询订单记录列表
     */
	@RequiresPermissions("eye:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeOrder eyeOrder)
    {
        startPage();
        List<EyeOrder> list = eyeOrderService.selectEyeOrderList(eyeOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单记录列表
     */
	@RequiresPermissions("eye:order:export")
    @Log(title = "订单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeOrder eyeOrder)
    {
        List<EyeOrder> list = eyeOrderService.selectEyeOrderList(eyeOrder);
        ExcelUtil<EyeOrder> util = new ExcelUtil<EyeOrder>(EyeOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 修改订单记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyeOrder eyeOrder = eyeOrderService.selectEyeOrderById(id);
        mmap.put("eyeOrder", eyeOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单记录
     */
	@RequiresPermissions("eye:order:edit")
    @Log(title = "订单记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyeOrder eyeOrder)
    {
        return toAjax(eyeOrderService.updateEyeOrder(eyeOrder));
    }

    /**
     * 删除订单记录
     */
	@RequiresPermissions("eye:order:remove")
    @Log(title = "订单记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eyeOrderService.deleteEyeOrderByIds(ids));
    }
    
    /**
     * 订单页面跳转至录制详情页面
     * @param token
     * @param mmap
     * @return
     */
	@GetMapping("/toOrderPage")
	public String detail(String token,String productCode,ModelMap mmap)
    {
		mmap.put("productCode",productCode);
        mmap.put("token", token);
		return "eye/orderpage/orderpage";
    }
    
    /**
     * 质检预警报告页面跳转
     * @return
     */
    @RequiresPermissions("eye:order:list")
    @GetMapping("/orderQualitylist")
    public String orderQualitylist()
    {
        return prefix + "/orderquality";
    }

    /**
     * 更新质检结果
     */
    @RequiresPermissions("eye:order:edit")
    @Log(title = "更新质检结果", businessType = BusinessType.UPDATE)
    @PostMapping("/updateQuality")
    @ResponseBody
    public AjaxResult editQuality(@RequestBody @Valid QualityCheckVo vo) {
        return toAjax(eyeOrderService.updateQuality(vo));
    }
}
