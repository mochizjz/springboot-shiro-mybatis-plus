package ins.webeye.project.eye.pageconfig.controller;

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
import ins.webeye.project.eye.pageconfig.domain.EyePageConfig;
import ins.webeye.project.eye.pageconfig.service.IEyePageConfigService;

/**
 * 页面管理Controller
 * 
 * @author xiaoquan
 * @date 2020-08-02
 */
@Controller
@RequestMapping("/eye/pageconfig")
public class EyePageConfigController extends BaseController
{

	private String prefix = "eye/pageconfig";

    @Autowired
    private IEyePageConfigService eyePageConfigService;

    @RequiresPermissions("eye:pageconfig:view")
    @GetMapping()
    public String pageconfig()
    {
        return prefix + "/pageconfig";
    }

    /**
     * 查询页面管理列表
     */
    @RequiresPermissions("eye:pageconfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyePageConfig eyePageConfig)
    {
        startPage();
        List<EyePageConfig> list = eyePageConfigService.selectEyePageConfigList(eyePageConfig);
        return getDataTable(list);
    }

    /**
     * 导出页面管理列表
     */
    @RequiresPermissions("eye:pageconfig:export")
    @Log(title = "页面管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyePageConfig eyePageConfig)
    {
        List<EyePageConfig> list = eyePageConfigService.selectEyePageConfigList(eyePageConfig);
        ExcelUtil<EyePageConfig> util = new ExcelUtil<EyePageConfig>(EyePageConfig.class);
        return util.exportExcel(list, "pageconfig");
    }

    /**
     * 新增页面管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存页面管理
     */
    @RequiresPermissions("eye:pageconfig:add")
    @Log(title = "页面管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EyePageConfig eyePageConfig)
    {
        return toAjax(eyePageConfigService.insertEyePageConfig(eyePageConfig));
    }

    /**
     * 修改页面管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyePageConfig eyePageConfig = eyePageConfigService.selectEyePageConfigById(id);
        mmap.put("eyePageConfig", eyePageConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存页面管理
     */
    @RequiresPermissions("eye:pageconfig:edit")
    @Log(title = "页面管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyePageConfig eyePageConfig)
    {
        return toAjax(eyePageConfigService.updateEyePageConfig(eyePageConfig));
    }

    /**
     * 删除页面管理
     */
    @RequiresPermissions("eye:pageconfig:remove")
    @Log(title = "页面管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eyePageConfigService.deleteEyePageConfigByIds(ids));
    }
}
