package ins.webeye.project.eye.nodeconfig.controller;

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

import ins.webeye.common.utils.poi.ExcelUtil;
import ins.webeye.framework.aspectj.lang.annotation.Log;
import ins.webeye.framework.aspectj.lang.enums.BusinessType;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.framework.web.page.TableDataInfo;
import ins.webeye.project.eye.nodeconfig.domain.EyeNodeConfig;
import ins.webeye.project.eye.nodeconfig.service.IEyeNodeConfigService;
import ins.webeye.project.system.dict.domain.DictData;
import ins.webeye.project.system.dict.service.IDictDataService;

/**
 * 节点配置Controller
 * 
 * @author xiaoquan
 * @date 2020-07-16
 */
@Controller
@RequestMapping("/eye/nodeconfig")
public class EyeNodeConfigController extends BaseController
{

	private String prefix = "eye/nodeconfig";

    @Autowired
    private IEyeNodeConfigService eyeNodeConfigService;
    @Autowired
    private IDictDataService dictDataService;
    
	@RequiresPermissions("eye:nodeconfig:view")
    @GetMapping()
    public String nodeconfig()
    {
        return prefix + "/nodeconfig";
    }

    /**
     * 查询节点配置列表
     */
	@RequiresPermissions("eye:nodeconfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EyeNodeConfig eyeNodeConfig)
    {
        startPage();
        List<EyeNodeConfig> list = eyeNodeConfigService.selectEyeNodeConfigList(eyeNodeConfig);
        return getDataTable(list);
    }

    /**
     * 导出节点配置列表
     */
	@RequiresPermissions("eye:nodeconfig:export")
    @Log(title = "节点配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EyeNodeConfig eyeNodeConfig)
    {
        List<EyeNodeConfig> list = eyeNodeConfigService.selectEyeNodeConfigList(eyeNodeConfig);
        ExcelUtil<EyeNodeConfig> util = new ExcelUtil<EyeNodeConfig>(EyeNodeConfig.class);
        return util.exportExcel(list, "nodeconfig");
    }

    /**
     * 新增节点配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存节点配置
     */
	@RequiresPermissions("eye:nodeconfig:add")
    @Log(title = "节点配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EyeNodeConfig eyeNodeConfig)
    {
    	//新增同时新增至字典表的  节点配置翻译  字典中
    	DictData dict = new DictData();
    	dict.setDictValue(eyeNodeConfig.getNodeCode());
    	dict.setDictLabel(eyeNodeConfig.getNodeName());
    	dict.setDictType("eye_node_config");//	节点配置:节点翻译字典
    	dict.setIsDefault("Y");//是否默认（Y是 N否）
    	dict.setStatus("0");//状态（0正常 1停用）
    	dict.setCreateTime(new Date());
    	dict.setCreateBy(eyeNodeConfig.getCreateUser());
    	//dict.setDictSort(xx);//TODO  计算最大的排序
    	dictDataService.insertDictData(dict);
    	
        return toAjax(eyeNodeConfigService.insertEyeNodeConfig(eyeNodeConfig));
    }

    /**
     * 修改节点配置
     */
	@RequiresPermissions("eye:nodeconfig:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EyeNodeConfig eyeNodeConfig = eyeNodeConfigService.selectEyeNodeConfigById(id);
        mmap.put("eyeNodeConfig", eyeNodeConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存节点配置
     */
	@RequiresPermissions("eye:nodeconfig:edit")
    @Log(title = "节点配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EyeNodeConfig eyeNodeConfig)
    {
    	//修改同时修改至字典表的  节点配置翻译  字典中
        EyeNodeConfig nodeConfig = eyeNodeConfigService.selectEyeNodeConfigById(eyeNodeConfig.getId());
    	DictData dict = new DictData();
    	dict.setDictValue(nodeConfig.getNodeCode());
    	dict.setDictLabel(nodeConfig.getNodeName());
    	dict.setDictType("eye_node_config");
        List<DictData> list = dictDataService.selectDictDataList(dict);
        if(list!=null&&list.size()>0){
        	for(DictData dictDate:list){
        		dictDate.setDictValue(eyeNodeConfig.getNodeCode());
            	dictDate.setDictLabel(eyeNodeConfig.getNodeName());
            	dictDataService.updateDictData(dictDate);
        	}
        }
        
        return toAjax(eyeNodeConfigService.updateEyeNodeConfig(eyeNodeConfig));
    }

    /**
     * 删除节点配置
     */
	@RequiresPermissions("eye:nodeconfig:remove")
    @Log(title = "节点配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
    	//删除同时删除至字典表的  节点配置翻译  字典中
    	for(String id:ids.split(",")){
    		EyeNodeConfig nodeConfig = eyeNodeConfigService.selectEyeNodeConfigById(Long.parseLong(id));
    		DictData dict = new DictData();
        	dict.setDictValue(nodeConfig.getNodeCode());
    		dict.setDictLabel(nodeConfig.getNodeName());
        	dict.setDictType("eye_node_config");
            List<DictData> list = dictDataService.selectDictDataList(dict);
            if(list!=null&&list.size()>0){
            	for(DictData dictDate:list){
            		dictDataService.deleteDictDataById(dictDate.getDictCode());
            	}
            }
    	}
        return toAjax(eyeNodeConfigService.deleteEyeNodeConfigByIds(ids));
    }
}
