package ins.webeye.project.eye.faceapilog.controller;

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
import ins.webeye.project.eye.faceapilog.domain.EyeFaceapiLog;
import ins.webeye.project.eye.faceapilog.service.IEyeFaceapiLogService;

/**
 * 实名验证接口日志Controller
 * 
 * @author XiaoQuan
 * @date 2020-08-16
 */
@Controller
@RequestMapping("/eye/faceapilog")
public class EyeFaceapiLogController extends BaseController
{

	private String prefix = "eye/faceapilog";

    @Autowired
    private IEyeFaceapiLogService eyeFaceapiLogService;

    @RequiresPermissions("eye:faceapilog:view")
    @GetMapping()
    public String faceapilog()
    {
        return prefix + "/faceapilog";
    }

    /**
     * 查询实名验证接口日志列表
     */
    @RequiresPermissions("eye:faceapilog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeFaceapiLog eyeFaceapiLog)
    {
        startPage();
        List<EyeFaceapiLog> list = eyeFaceapiLogService.selectEyeFaceapiLogList(eyeFaceapiLog);
        return getDataTable(list);
    }

    /**
     * 导出实名验证接口日志列表
     */
    @RequiresPermissions("eye:faceapilog:export")
    @Log(title = "实名验证接口日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeFaceapiLog eyeFaceapiLog)
    {
        List<EyeFaceapiLog> list = eyeFaceapiLogService.selectEyeFaceapiLogList(eyeFaceapiLog);
        ExcelUtil<EyeFaceapiLog> util = new ExcelUtil<EyeFaceapiLog>(EyeFaceapiLog.class);
        return util.exportExcel(list, "faceapilog");
    }

    /**
     * 新增实名验证接口日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存实名验证接口日志
     */
    @RequiresPermissions("eye:faceapilog:add")
    @Log(title = "实名验证接口日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EyeFaceapiLog eyeFaceapiLog)
    {
        return toAjax(eyeFaceapiLogService.insertEyeFaceapiLog(eyeFaceapiLog));
    }

    /**
     * 修改实名验证接口日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyeFaceapiLog eyeFaceapiLog = eyeFaceapiLogService.selectEyeFaceapiLogById(id);
        mmap.put("eyeFaceapiLog", eyeFaceapiLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存实名验证接口日志
     */
    @RequiresPermissions("eye:faceapilog:edit")
    @Log(title = "实名验证接口日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyeFaceapiLog eyeFaceapiLog)
    {
        return toAjax(eyeFaceapiLogService.updateEyeFaceapiLog(eyeFaceapiLog));
    }

    /**
     * 删除实名验证接口日志
     */
    @RequiresPermissions("eye:faceapilog:remove")
    @Log(title = "实名验证接口日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eyeFaceapiLogService.deleteEyeFaceapiLogByIds(ids));
    }
}
