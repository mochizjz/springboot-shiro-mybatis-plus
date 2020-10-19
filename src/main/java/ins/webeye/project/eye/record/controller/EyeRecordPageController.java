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
import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.record.service.IEyeRecordPageService;

/**
 * 页面录制记录Controller
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/eye/recordpage")
public class EyeRecordPageController extends BaseController
{

	private String prefix = "eye/recordpage";

    @Autowired
    private IEyeRecordPageService eyeRecordPageService;

	@RequiresPermissions("eye:recordpage:view")
    @GetMapping()
    public String recordpage()
    {
        return prefix + "/recordpage";
    }

    /**
     * 查询页面录制记录列表
     */
	@RequiresPermissions("eye:recordpage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeRecordPage eyeRecordPage)
    {
        startPage();
        List<EyeRecordPage> list = eyeRecordPageService.selectEyeRecordPageList(eyeRecordPage);
        return getDataTable(list);
    }

    /**
     * 导出页面录制记录列表
     */
	@RequiresPermissions("eye:recordpage:export")
    @Log(title = "页面录制记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeRecordPage eyeRecordPage)
    {
        List<EyeRecordPage> list = eyeRecordPageService.selectEyeRecordPageList(eyeRecordPage);
        ExcelUtil<EyeRecordPage> util = new ExcelUtil<EyeRecordPage>(EyeRecordPage.class);
        return util.exportExcel(list, "recordpage");
    }


    /**
     * 修改页面录制记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyeRecordPage eyeRecordPage = eyeRecordPageService.selectEyeRecordPageById(id);
        mmap.put("eyeRecordPage", eyeRecordPage);
        return prefix + "/edit";
    }

    /**
     * 修改保存页面录制记录
     */
	@RequiresPermissions("eye:recordpage:edit")
    @Log(title = "页面录制记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyeRecordPage eyeRecordPage)
    {
        return toAjax(eyeRecordPageService.updateEyeRecordPage(eyeRecordPage));
    }

    /**
     * 删除页面录制记录
     */
	@RequiresPermissions("eye:recordpage:remove")
    @Log(title = "页面录制记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eyeRecordPageService.deleteEyeRecordPageByIds(ids));
    }
}
