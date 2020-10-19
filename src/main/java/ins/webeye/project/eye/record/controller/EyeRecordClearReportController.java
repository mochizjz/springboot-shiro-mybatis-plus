package ins.webeye.project.eye.record.controller;

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
import ins.webeye.project.eye.record.domain.EyeRecordClearReport;
import ins.webeye.project.eye.record.service.IEyeRecordClearReportService;

/**
 * 清理操作记录Controller
 * 
 * @author xiaoquan
 * @date 2020-07-20
 */
@Controller
@RequestMapping("/eye/recordclearreport")
public class EyeRecordClearReportController extends BaseController
{

	private String prefix = "eye/recordclearreport";

    @Autowired
    private IEyeRecordClearReportService eyeRecordClearReportService;

	@RequiresPermissions("eye:recordclearreport:view")
    @GetMapping()
    public String recordclearreport()
    {
        return prefix + "/recordclearreport";
    }

    /**
     * 查询清理操作记录列表
     */
	@RequiresPermissions("eye:recordclearreport:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeRecordClearReport eyeRecordClearReport)
    {
        startPage();
        List<EyeRecordClearReport> list = eyeRecordClearReportService.selectEyeRecordClearReportList(eyeRecordClearReport);
        return getDataTable(list);
    }

    /**
     * 导出清理操作记录列表
     */
	@RequiresPermissions("eye:recordclearreport:export")
    @Log(title = "清理操作记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeRecordClearReport eyeRecordClearReport)
    {
        List<EyeRecordClearReport> list = eyeRecordClearReportService.selectEyeRecordClearReportList(eyeRecordClearReport);
        ExcelUtil<EyeRecordClearReport> util = new ExcelUtil<EyeRecordClearReport>(EyeRecordClearReport.class);
        return util.exportExcel(list, "recordclearreport");
    }

    /**
     * 新增清理操作记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存清理操作记录
     */
	@RequiresPermissions("eye:recordclearreport:add")
    @Log(title = "清理操作记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EyeRecordClearReport eyeRecordClearReport)
    {
        return toAjax(eyeRecordClearReportService.insertEyeRecordClearReport(eyeRecordClearReport));
    }

    /**
     * 修改清理操作记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyeRecordClearReport eyeRecordClearReport = eyeRecordClearReportService.selectEyeRecordClearReportById(id);
        mmap.put("eyeRecordClearReport", eyeRecordClearReport);
        return prefix + "/edit";
    }

    /**
     * 修改保存清理操作记录
     */
	@RequiresPermissions("eye:recordclearreport:edit")
    @Log(title = "清理操作记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyeRecordClearReport eyeRecordClearReport)
    {
        return toAjax(eyeRecordClearReportService.updateEyeRecordClearReport(eyeRecordClearReport));
    }

    /**
     * 删除清理操作记录
     */
	@RequiresPermissions("eye:recordclearreport:remove")
    @Log(title = "清理操作记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eyeRecordClearReportService.deleteEyeRecordClearReportByIds(ids));
    }
}
