package ins.webeye.project.system.cachemanager.controller;

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
import ins.webeye.project.system.cachemanager.domain.CacheManager;
import ins.webeye.project.system.cachemanager.service.ICacheManagerService;

/**
 * 缓存管理Controller
 * 
 * @author webeye
 * @date 2020-09-08
 */
@Controller
@RequestMapping("/system/cachemanager")
public class CacheManagerController extends BaseController
{
    private String prefix = "system/cachemanager";

    @Autowired
    private ICacheManagerService cacheManagerService;

    @RequiresPermissions("system:cachemanager:view")
    @GetMapping()
    public String cachemanager()
    {
        return prefix + "/cachemanager";
    }

    /**
     * 查询缓存管理列表
     */
    @RequiresPermissions("system:cachemanager:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CacheManager cacheManager)
    {
        startPage();
        List<CacheManager> list = cacheManagerService.selectCacheManagerList(cacheManager);
        return getDataTable(list);
    }

    /**
     * 导出缓存管理列表
     */
    @RequiresPermissions("system:cachemanager:export")
    @Log(title = "缓存管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CacheManager cacheManager)
    {
        List<CacheManager> list = cacheManagerService.selectCacheManagerList(cacheManager);
        ExcelUtil<CacheManager> util = new ExcelUtil<CacheManager>(CacheManager.class);
        return util.exportExcel(list, "cachemanager");
    }

    /**
     * 新增缓存管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存缓存管理
     */
    @RequiresPermissions("system:cachemanager:add")
    @Log(title = "缓存管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CacheManager cacheManager)
    {
        return toAjax(cacheManagerService.insertCacheManager(cacheManager));
    }

    /**
     * 修改缓存管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CacheManager cacheManager = cacheManagerService.selectCacheManagerById(id);
        mmap.put("cacheManager", cacheManager);
        return prefix + "/edit";
    }

    /**
     * 修改保存缓存管理
     */
    @RequiresPermissions("system:cachemanager:edit")
    @Log(title = "缓存管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CacheManager cacheManager)
    {
        return toAjax(cacheManagerService.updateCacheManager(cacheManager));
    }

    /**
     * 删除缓存管理
     */
    @RequiresPermissions("system:cachemanager:remove")
    @Log(title = "缓存管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cacheManagerService.deleteCacheManagerByIds(ids));
    }

	/**
	 * 清空redis值
	 */
	@RequiresPermissions("system:cachemanager:remove")
	@Log(title = "清空redis值", businessType = BusinessType.DELETE)
	@GetMapping("/clean/{cacheKey}")
	@ResponseBody
	public AjaxResult clean(@PathVariable("cacheKey") String cacheKey) {
		return toAjax(cacheManagerService.cleanCache(cacheKey));
	}
}
