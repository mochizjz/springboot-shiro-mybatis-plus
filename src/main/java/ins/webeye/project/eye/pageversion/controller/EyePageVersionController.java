package ins.webeye.project.eye.pageversion.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ins.webeye.common.utils.DESUtils;
import ins.webeye.common.utils.poi.ExcelUtil;
import ins.webeye.framework.aspectj.lang.annotation.Log;
import ins.webeye.framework.aspectj.lang.enums.BusinessType;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.framework.web.page.TableDataInfo;
import ins.webeye.project.eye.pageversion.domain.EyePageVersion;
import ins.webeye.project.eye.pageversion.service.IEyePageVersionService;

/**
 * 页面版本记录Controller
 * 
 * @author xiaoquan
 * @date 2020-07-23
 */
@Controller
@RequestMapping("/eye/pageversion")
public class EyePageVersionController extends BaseController
{

	private String prefix = "eye/pageversion";

    @Autowired
    private IEyePageVersionService eyePageVersionService;

	@RequiresPermissions("eye:pageversion:view")
    @GetMapping()
    public String pageversion()
    {
        return prefix + "/pageversion";
    }

    /**
     * 查询页面版本记录列表
     * @throws Exception 
     */
	@RequiresPermissions("eye:pageversion:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyePageVersion eyePageVersion) throws Exception
    {
        startPage();
        List<EyePageVersion> list = eyePageVersionService.selectEyePageVersionList(eyePageVersion);
        if(list!=null && list.size()>0){
        	for(EyePageVersion pageVersion:list){
        		if(StringUtils.isNotBlank(pageVersion.getFilePath())){
        			pageVersion.setFilePath( DESUtils.encrypt(pageVersion.getFilePath()));
        		}
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出页面版本记录列表
     */
	@RequiresPermissions("eye:pageversion:export")
    @Log(title = "页面版本记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyePageVersion eyePageVersion)
    {
        List<EyePageVersion> list = eyePageVersionService.selectEyePageVersionList(eyePageVersion);
        ExcelUtil<EyePageVersion> util = new ExcelUtil<EyePageVersion>(EyePageVersion.class);
        return util.exportExcel(list, "pageversion");
    }

    /**
     * 新增页面版本记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存页面版本记录
     */
	@RequiresPermissions("eye:pageversion:add")
    @Log(title = "页面版本记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EyePageVersion eyePageVersion)
    {
        return toAjax(eyePageVersionService.insertEyePageVersion(eyePageVersion));
    }

    /**
     * 修改页面版本记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyePageVersion eyePageVersion = eyePageVersionService.selectEyePageVersionById(id);
        mmap.put("eyePageVersion", eyePageVersion);
        return prefix + "/edit";
    }

    /**
     * 修改保存页面版本记录
     */
	@RequiresPermissions("eye:pageversion:edit")
    @Log(title = "页面版本记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyePageVersion eyePageVersion)
    {
        return toAjax(eyePageVersionService.updateEyePageVersion(eyePageVersion));
    }

    /**
     * 删除页面版本记录
     */
	@RequiresPermissions("eye:pageversion:remove")
    @Log(title = "页面版本记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eyePageVersionService.deleteEyePageVersionByIds(ids));
    }
    
}
