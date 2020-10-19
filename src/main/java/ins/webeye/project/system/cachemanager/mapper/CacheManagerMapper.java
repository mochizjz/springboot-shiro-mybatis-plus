package ins.webeye.project.system.cachemanager.mapper;

import java.util.List;
import ins.webeye.project.system.cachemanager.domain.CacheManager;

/**
 * 缓存管理Mapper接口
 * 
 * @author webeye
 * @date 2020-09-08
 */
public interface CacheManagerMapper 
{
    /**
     * 查询缓存管理
     * 
     * @param id 缓存管理ID
     * @return 缓存管理
     */
    public CacheManager selectCacheManagerById(Long id);

    /**
     * 查询缓存管理列表
     * 
     * @param cacheManager 缓存管理
     * @return 缓存管理集合
     */
    public List<CacheManager> selectCacheManagerList(CacheManager cacheManager);

    /**
     * 新增缓存管理
     * 
     * @param cacheManager 缓存管理
     * @return 结果
     */
    public int insertCacheManager(CacheManager cacheManager);

    /**
     * 修改缓存管理
     * 
     * @param cacheManager 缓存管理
     * @return 结果
     */
    public int updateCacheManager(CacheManager cacheManager);

    /**
     * 删除缓存管理
     * 
     * @param id 缓存管理ID
     * @return 结果
     */
    public int deleteCacheManagerById(Long id);

    /**
     * 批量删除缓存管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCacheManagerByIds(String[] ids);
}
