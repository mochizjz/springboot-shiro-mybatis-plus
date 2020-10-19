package ins.webeye.project.eye.checklog.controller;

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
import ins.webeye.project.eye.checklog.domain.EyeCheckLog;
import ins.webeye.project.eye.checklog.service.IEyeCheckLogService;

/**
 * 质检轨迹Controller
 * 
 * @author wangtao
 * @date 2020-08-20
 */
@Controller
@RequestMapping("/eye/checklog")
public class EyeCheckLogController extends BaseController
{

	private String prefix = "eye/checklog";

    @Autowired
    private IEyeCheckLogService eyeCheckLogService;

	@RequiresPermissions("eye:checklog:view")
    @GetMapping()
    public String eye()
    {
        return prefix + "/checklog";
    }

    /**
     * 查询质检轨迹列表
     */
	@RequiresPermissions("eye:checklog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeCheckLog eyeCheckLog)
    {
        startPage();
        List<EyeCheckLog> list = eyeCheckLogService.selectEyeCheckLogList(eyeCheckLog);
        return getDataTable(list);
    }

    /**
     * 导出质检轨迹列表
     */
	@RequiresPermissions("eye:checklog:export")
    @Log(title = "质检轨迹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeCheckLog eyeCheckLog)
    {
        List<EyeCheckLog> list = eyeCheckLogService.selectEyeCheckLogList(eyeCheckLog);
        ExcelUtil<EyeCheckLog> util = new ExcelUtil<EyeCheckLog>(EyeCheckLog.class);
        return util.exportExcel(list, "eye");
    }

    /**
     * 新增质检轨迹
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存质检轨迹
     */
	@RequiresPermissions("eye:checklog:add")
    @Log(title = "质检轨迹", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EyeCheckLog eyeCheckLog)
    {
        return toAjax(eyeCheckLogService.insertEyeCheckLog(eyeCheckLog));
    }

    /**
     * 修改质检轨迹
     */
	@RequiresPermissions("eye:checklog:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyeCheckLog eyeCheckLog = eyeCheckLogService.selectEyeCheckLogById(id);
        mmap.put("eyeCheckLog", eyeCheckLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存质检轨迹
     */
	@RequiresPermissions("eye:checklog:edit")
    @Log(title = "质检轨迹", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyeCheckLog eyeCheckLog)
    {
        return toAjax(eyeCheckLogService.updateEyeCheckLog(eyeCheckLog));
    }

    /**
     * 删除质检轨迹
     */
	@RequiresPermissions("eye:checklog:remove")
    @Log(title = "质检轨迹", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eyeCheckLogService.deleteEyeCheckLogByIds(ids));
    }
}
