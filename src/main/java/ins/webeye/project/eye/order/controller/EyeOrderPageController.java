package ins.webeye.project.eye.order.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ins.webeye.common.utils.poi.ExcelUtil;
import ins.webeye.framework.aspectj.lang.annotation.Log;
import ins.webeye.framework.aspectj.lang.enums.BusinessType;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.framework.web.page.TableDataInfo;
import ins.webeye.project.eye.order.domain.EyeOrderPage;
import ins.webeye.project.eye.order.service.IEyeOrderPageService;

/**
 * 订单录制记录Controller
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/eye/orderpage")
public class EyeOrderPageController extends BaseController
{

	private String prefix = "eye/orderpage";

    @Autowired
    private IEyeOrderPageService eyeOrderPageService;

	@RequiresPermissions("eye:order:view")
    @GetMapping()
    public String orderpage()
    {
        return prefix + "/orderpage";
    }

    /**
     * 查询订单录制记录列表
     */
	@RequiresPermissions("eye:order:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeOrderPage eyeOrderPage)
    {
        startPage();
        List<EyeOrderPage> list = eyeOrderPageService.selectEyeOrderPageList(eyeOrderPage);
        return getDataTable(list);
    }

    /**
     * 导出订单录制记录列表
     */
	@RequiresPermissions("eye:order:export")
    @Log(title = "订单录制记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeOrderPage eyeOrderPage)
    {
        List<EyeOrderPage> list = eyeOrderPageService.selectEyeOrderPageList(eyeOrderPage);
        ExcelUtil<EyeOrderPage> util = new ExcelUtil<EyeOrderPage>(EyeOrderPage.class);
        return util.exportExcel(list, "orderpage");
    }


    /**
     * 修改订单录制记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyeOrderPage eyeOrderPage = eyeOrderPageService.selectEyeOrderPageById(id);
        mmap.put("eyeOrderPage", eyeOrderPage);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单录制记录
     */
	@RequiresPermissions("eye:order:edit")
    @Log(title = "订单录制记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyeOrderPage eyeOrderPage)
    {
        return toAjax(eyeOrderPageService.updateEyeOrderPage(eyeOrderPage));
    }

    /**
     * 删除订单录制记录
     */
	@RequiresPermissions("eye:order:remove")
    @Log(title = "订单录制记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eyeOrderPageService.deleteEyeOrderPageByIds(ids));
    }
}
