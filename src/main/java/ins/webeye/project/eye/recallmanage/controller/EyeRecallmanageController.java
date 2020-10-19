package ins.webeye.project.eye.recallmanage.controller;

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
import ins.webeye.project.eye.recallmanage.domain.EyeRecallmanage;
import ins.webeye.project.eye.recallmanage.service.IEyeRecallmanageService;

/**
 * 回溯管理Controller
 * 
 * @author webeye
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/eye/recallmanage")
public class EyeRecallmanageController extends BaseController
{

	private String prefix = "eye/recallmanage";

    @Autowired
    private IEyeRecallmanageService eyeRecallmanageService;

    /**
     * 回溯验真
     */
	// @RequiresPermissions("eye:recallOfTruth:view")
    @GetMapping("/recallOfTruth")
    public String recallOfTruth()
    {
        return prefix + "/recallOfTruth";
    }

    /**
     * 查询回溯管理列表
     */
	@RequiresPermissions("eye:recallmanage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeRecallmanage recallmanage)
    {
        startPage();
        List<EyeRecallmanage> list = eyeRecallmanageService.selectRecallmanageList(recallmanage);
        return getDataTable(list);
    }

    /**
     * 导出回溯管理列表
     */
	@RequiresPermissions("eye:recallmanage:export")
    @Log(title = "回溯管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeRecallmanage recallmanage)
    {
        List<EyeRecallmanage> list = eyeRecallmanageService.selectRecallmanageList(recallmanage);
        ExcelUtil<EyeRecallmanage> util = new ExcelUtil<EyeRecallmanage>(EyeRecallmanage.class);
        return util.exportExcel(list, "recallmanage");
    }



    
    
    
    

}
