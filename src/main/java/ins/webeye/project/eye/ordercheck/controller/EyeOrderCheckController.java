package ins.webeye.project.eye.ordercheck.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ins.webeye.common.utils.poi.ExcelUtil;
import ins.webeye.framework.aspectj.lang.annotation.Log;
import ins.webeye.framework.aspectj.lang.enums.BusinessType;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.framework.web.page.TableDataInfo;
import ins.webeye.project.eye.ordercheck.domain.EyeOrderCheck;
import ins.webeye.project.eye.ordercheck.service.IEyeOrderCheckService;

/**
 * 回溯对账查询Controller
 * 
 * @author webeye
 * @date 2020-09-08
 */
@Controller
@RequestMapping("/eye/ordercheck")
public class EyeOrderCheckController extends BaseController
{
    private String prefix = "eye/ordercheck";

    @Autowired
    private IEyeOrderCheckService eyeOrderCheckService;

    @RequiresPermissions("eye:ordercheck:view")
    @GetMapping()
    public String ordercheck()
    {
        return prefix + "/ordercheck";
    }

    /**
     * 查询回溯对账查询列表
     */
    @RequiresPermissions("eye:ordercheck:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeOrderCheck eyeOrderCheck)
    {
        startPage();
        List<EyeOrderCheck> list = eyeOrderCheckService.selectEyeOrderCheckList(eyeOrderCheck);
        return getDataTable(list);
    }

    /**
     * 导出回溯对账查询列表
     */
    @RequiresPermissions("eye:ordercheck:export")
    @Log(title = "回溯对账查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeOrderCheck eyeOrderCheck)
    {
        List<EyeOrderCheck> list = eyeOrderCheckService.selectEyeOrderCheckList(eyeOrderCheck);
        ExcelUtil<EyeOrderCheck> util = new ExcelUtil<EyeOrderCheck>(EyeOrderCheck.class);
        return util.exportExcel(list, "ordercheck");
    }

	// /**
	// * 新增回溯对账查询
	// */
	// @GetMapping("/add")
	// public String add()
	// {
	// return prefix + "/add";
	// }
	//
	// /**
	// * 新增保存回溯对账查询
	// */
	// @RequiresPermissions("eye:ordercheck:add")
	// @Log(title = "回溯对账查询", businessType = BusinessType.INSERT)
	// @PostMapping("/add")
	// @ResponseBody
	// public AjaxResult addSave(EyeOrderCheck eyeOrderCheck)
	// {
	// return toAjax(eyeOrderCheckService.insertEyeOrderCheck(eyeOrderCheck));
	// }

	// /**
	// * 修改回溯对账查询
	// */
	// @GetMapping("/edit/{id}")
	// public String edit(@PathVariable("id") Long id, ModelMap mmap)
	// {
	// EyeOrderCheck eyeOrderCheck = eyeOrderCheckService.selectEyeOrderCheckById(id);
	// mmap.put("eyeOrderCheck", eyeOrderCheck);
	// return prefix + "/edit";
	// }

	// /**
	// * 修改保存回溯对账查询
	// */
	// @RequiresPermissions("eye:ordercheck:edit")
	// @Log(title = "回溯对账查询", businessType = BusinessType.UPDATE)
	// @PostMapping("/edit")
	// @ResponseBody
	// public AjaxResult editSave(EyeOrderCheck eyeOrderCheck)
	// {
	// return toAjax(eyeOrderCheckService.updateEyeOrderCheck(eyeOrderCheck));
	// }
	//
	// /**
	// * 删除回溯对账查询
	// */
	// @RequiresPermissions("eye:ordercheck:remove")
	// @Log(title = "回溯对账查询", businessType = BusinessType.DELETE)
	// @PostMapping( "/remove")
	// @ResponseBody
	// public AjaxResult remove(String ids)
	// {
	// return toAjax(eyeOrderCheckService.deleteEyeOrderCheckByIds(ids));
	// }
}
