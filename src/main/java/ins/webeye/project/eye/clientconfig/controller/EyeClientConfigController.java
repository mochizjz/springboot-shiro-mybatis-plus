package ins.webeye.project.eye.clientconfig.controller;

import java.util.Date;
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

import cn.hutool.core.util.RandomUtil;
import ins.webeye.common.utils.poi.ExcelUtil;
import ins.webeye.framework.aspectj.lang.annotation.Log;
import ins.webeye.framework.aspectj.lang.enums.BusinessType;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.framework.web.page.TableDataInfo;
import ins.webeye.project.eye.clientconfig.domain.EyeClientConfig;
import ins.webeye.project.eye.clientconfig.service.IEyeClientConfigService;
import ins.webeye.project.eye.nodeconfig.domain.EyeNodeConfig;
import ins.webeye.project.system.dict.domain.DictData;
import ins.webeye.project.system.dict.service.IDictDataService;

/**
 * 系统/渠道配置Controller
 * 
 * @author webeye
 * @date 2020-08-30
 */
@Controller
@RequestMapping("/eye/clientconfig")
public class EyeClientConfigController extends BaseController
{
    private String prefix = "eye/clientconfig";

    @Autowired
    private IEyeClientConfigService eyeClientConfigService;
    @Autowired
    private IDictDataService dictDataService;

    @RequiresPermissions("eye:clientconfig:view")
    @GetMapping()
    public String clientconfig()
    {
        return prefix + "/clientconfig";
    }

    /**
     * 查询系统/渠道配置列表
     */
    @RequiresPermissions("eye:clientconfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeClientConfig eyeClientConfig)
    {
        startPage();
        List<EyeClientConfig> list = eyeClientConfigService.selectEyeClientConfigList(eyeClientConfig);
        return getDataTable(list);
    }

    /**
     * 导出系统/渠道配置列表
     */
    @RequiresPermissions("eye:clientconfig:export")
    @Log(title = "系统/渠道配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeClientConfig eyeClientConfig)
    {
        List<EyeClientConfig> list = eyeClientConfigService.selectEyeClientConfigList(eyeClientConfig);
        ExcelUtil<EyeClientConfig> util = new ExcelUtil<EyeClientConfig>(EyeClientConfig.class);
        return util.exportExcel(list, "clientconfig");
    }

    /**
     * 新增系统/渠道配置
     */
    @GetMapping("/add")
	public String add(ModelMap mmap)
    {
		String authorizeKey = RandomUtil.randomString(20);
		mmap.put("authorizeKey",authorizeKey);
        return prefix + "/add";
    }

    /**
     * 新增保存系统/渠道配置
     */
    @RequiresPermissions("eye:clientconfig:add")
    @Log(title = "系统/渠道配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EyeClientConfig eyeClientConfig)
    {
    	//新增同时新增至字典表的  节点配置翻译  字典中
    	DictData dict = new DictData();
    	dict.setDictValue(eyeClientConfig.getClientCode());
    	dict.setDictLabel(eyeClientConfig.getClientName());
    	dict.setDictType("client_code");//	节点配置:节点翻译字典
    	dict.setIsDefault("Y");//是否默认（Y是 N否）
    	dict.setStatus("0");//状态（0正常 1停用）
    	dict.setCreateTime(new Date());
    	dict.setCreateBy(eyeClientConfig.getCreateUser());
    	//dict.setDictSort(xx);//TODO  计算最大的排序
    	dictDataService.insertDictData(dict);
    	
        return toAjax(eyeClientConfigService.insertEyeClientConfig(eyeClientConfig));
    }

    /**
     * 修改系统/渠道配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyeClientConfig eyeClientConfig = eyeClientConfigService.selectEyeClientConfigById(id);
        mmap.put("eyeClientConfig", eyeClientConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存系统/渠道配置
     */
    @RequiresPermissions("eye:clientconfig:edit")
    @Log(title = "系统/渠道配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyeClientConfig eyeClientConfig)
    {
    	//修改同时修改至字典表的  节点配置翻译  字典中
    	EyeClientConfig clientConfig = eyeClientConfigService.selectEyeClientConfigById(eyeClientConfig.getId());
    	DictData dict = new DictData();
    	dict.setDictValue(clientConfig.getClientCode());
    	dict.setDictLabel(clientConfig.getClientName());
    	dict.setDictType("client_code");
        List<DictData> list = dictDataService.selectDictDataList(dict);
        if(list!=null&&list.size()>0){
        	for(DictData dictDate:list){
        		dictDate.setDictValue(eyeClientConfig.getClientCode());
            	dictDate.setDictLabel(eyeClientConfig.getClientName());
            	dictDataService.updateDictData(dictDate);
        	}
        }
        return toAjax(eyeClientConfigService.updateEyeClientConfig(eyeClientConfig));
    }

    /**
     * 删除系统/渠道配置
     */
    @RequiresPermissions("eye:clientconfig:remove")
    @Log(title = "系统/渠道配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
    	//删除同时删除至字典表的  节点配置翻译  字典中
    	for(String id:ids.split(",")){
        	EyeClientConfig clientConfig = eyeClientConfigService.selectEyeClientConfigById(Long.parseLong(id));
    		DictData dict = new DictData();
        	dict.setDictValue(clientConfig.getClientCode());
    		dict.setDictLabel(clientConfig.getClientName());
        	dict.setDictType("client_code");
            List<DictData> list = dictDataService.selectDictDataList(dict);
            if(list!=null&&list.size()>0){
            	for(DictData dictDate:list){
            		dictDataService.deleteDictDataById(dictDate.getDictCode());
            	}
            }
    	}
        return toAjax(eyeClientConfigService.deleteEyeClientConfigByIds(ids));
    }

	/**
	 * 修改系统/渠道配置
	 */
	@RequiresPermissions("eye:clientconfig:viewKey")
	@GetMapping("/viewKey/{id}")
	public String viewKey(@PathVariable("id") Long id,ModelMap mmap) {
		EyeClientConfig eyeClientConfig = eyeClientConfigService.selectEyeClientConfigById(id);
		mmap.put("eyeClientConfig",eyeClientConfig);
		return prefix+"/viewKey";
	}
}
