package ins.webeye.project.system.cachemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.RedisUtil;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.system.cachemanager.domain.CacheManager;
import ins.webeye.project.system.cachemanager.mapper.CacheManagerMapper;
import ins.webeye.project.system.cachemanager.service.ICacheManagerService;

/**
 * 缓存管理Service业务层处理
 * 
 * @author webeye
 * @date 2020-09-08
 */
@Service
public class CacheManagerServiceImpl implements ICacheManagerService 
{
    @Autowired
    private CacheManagerMapper cacheManagerMapper;
	@Autowired
	private RedisUtil redisUtil;
    /**
     * 查询缓存管理
     * 
     * @param id 缓存管理ID
     * @return 缓存管理
     */
    @Override
    public CacheManager selectCacheManagerById(Long id)
    {
        return cacheManagerMapper.selectCacheManagerById(id);
    }

    /**
     * 查询缓存管理列表
     * 
     * @param cacheManager 缓存管理
     * @return 缓存管理
     */
    @Override
    public List<CacheManager> selectCacheManagerList(CacheManager cacheManager)
    {
        return cacheManagerMapper.selectCacheManagerList(cacheManager);
    }

    /**
     * 新增缓存管理
     * 
     * @param cacheManager 缓存管理
     * @return 结果
     */
    @Override
    public int insertCacheManager(CacheManager cacheManager)
    {
        cacheManager.setCreateTime(DateUtils.getNowDate());
        return cacheManagerMapper.insertCacheManager(cacheManager);
    }

    /**
     * 修改缓存管理
     * 
     * @param cacheManager 缓存管理
     * @return 结果
     */
    @Override
    public int updateCacheManager(CacheManager cacheManager)
    {
        cacheManager.setUpdateTime(DateUtils.getNowDate());
        return cacheManagerMapper.updateCacheManager(cacheManager);
    }

    /**
     * 删除缓存管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCacheManagerByIds(String ids)
    {
        return cacheManagerMapper.deleteCacheManagerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除缓存管理信息
     * 
     * @param id 缓存管理ID
     * @return 结果
     */
    @Override
    public int deleteCacheManagerById(Long id)
    {
        return cacheManagerMapper.deleteCacheManagerById(id);
    }

	@Override
	public int cleanCache(String cacheKey) {
		if(redisUtil.hasKey(cacheKey)==false){
			throw new IllegalArgumentException(cacheKey+"缓存不存在");
		}
		redisUtil.del(cacheKey);
		return 1;
	}
}
