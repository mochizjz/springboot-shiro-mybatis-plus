package ins.webeye.project.eye.pageconfig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.RedisUtil;
import ins.webeye.common.utils.security.ShiroUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.pageconfig.domain.EyePageConfig;
import ins.webeye.project.eye.pageconfig.mapper.EyePageConfigMapper;
import ins.webeye.project.eye.pageconfig.service.IEyePageConfigService;

/**
 * 页面管理Service业务层处理
 * 
 * @author xiaoquan
 * @date 2020-08-02
 */
@Service
public class EyePageConfigServiceImpl implements IEyePageConfigService 
{
	@Autowired
	private RedisUtil redisUtil;
    @Autowired
    private EyePageConfigMapper eyePageConfigMapper;

    /**
     * 查询页面管理
     * 
     * @param id 页面管理ID
     * @return 页面管理
     */
    @Override
    public EyePageConfig selectEyePageConfigById(Long id)
    {
        return eyePageConfigMapper.selectEyePageConfigById(id);
    }

    /**
     * 查询页面管理列表
     * 
     * @param eyePageConfig 页面管理
     * @return 页面管理
     */
    @Override
    public List<EyePageConfig> selectEyePageConfigList(EyePageConfig eyePageConfig)
    {
        return eyePageConfigMapper.selectEyePageConfigList(eyePageConfig);
    }

    /**
     * 新增页面管理
     * 
     * @param eyePageConfig 页面管理
     * @return 结果
     */
    @Override
    public int insertEyePageConfig(EyePageConfig eyePageConfig)
    {
    	eyePageConfig.setCreateUser(ShiroUtils.getLoginName());
        eyePageConfig.setCreateTime(DateUtils.getNowDate());
        return eyePageConfigMapper.insertEyePageConfig(eyePageConfig);
    }

    /**
     * 修改页面管理
     * 
     * @param eyePageConfig 页面管理
     * @return 结果
     */
    @Override
    public int updateEyePageConfig(EyePageConfig eyePageConfig)
    {
    	eyePageConfig.setUpdateUser(ShiroUtils.getLoginName());
        eyePageConfig.setUpdateTime(DateUtils.getNowDate());
        //编辑时将缓存进行更新
        String key = eyePageConfig.getSysCode() + "_" + eyePageConfig.getPageUrl();
        redisUtil.set(key,eyePageConfig);
		redisUtil.expire(key,60*60*6);
        return eyePageConfigMapper.updateEyePageConfig(eyePageConfig);
    }

    /**
     * 删除页面管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyePageConfigByIds(String ids)
    {
        return eyePageConfigMapper.deleteEyePageConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除页面管理信息
     * 
     * @param id 页面管理ID
     * @return 结果
     */
    @Override
    public int deleteEyePageConfigById(Long id)
    {
        return eyePageConfigMapper.deleteEyePageConfigById(id);
    }
    
    @Override
    public EyePageConfig findEyePageConfig(String sysCode,String pageUrl)
    {
    	String key = sysCode + "_" + pageUrl;
    	EyePageConfig pageManage = (EyePageConfig)redisUtil.get(key);
    	if(pageManage==null){
    		pageManage = new EyePageConfig();
    		pageManage.setSysCode(sysCode);
        	pageManage.setPageUrl(pageUrl);
        	List<EyePageConfig> pageManageList = eyePageConfigMapper.selectEyePageConfigList(pageManage);
        	if(pageManageList != null && pageManageList.size() > 0 ){
        		pageManage = pageManageList.get(0);
				redisUtil.set(key,pageManage);
				redisUtil.expire(key,60*60*1);
        		return pageManage;
        	}else{
        		return null;
        	}
    	}else{
    		return pageManage;	
    	}
    }
}
