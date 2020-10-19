package ins.webeye.project.eye.clientconfig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.security.ShiroUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.clientconfig.domain.EyeClientConfig;
import ins.webeye.project.eye.clientconfig.mapper.EyeClientConfigMapper;
import ins.webeye.project.eye.clientconfig.service.IEyeClientConfigService;

/**
 * 系统/渠道配置Service业务层处理
 * 
 * @author webeye
 * @date 2020-08-30
 */
@Service
public class EyeClientConfigServiceImpl implements IEyeClientConfigService 
{
    @Autowired
    private EyeClientConfigMapper eyeClientConfigMapper;

    /**
     * 查询系统/渠道配置
     * 
     * @param id 系统/渠道配置ID
     * @return 系统/渠道配置
     */
    @Override
    public EyeClientConfig selectEyeClientConfigById(Long id)
    {
        return eyeClientConfigMapper.selectEyeClientConfigById(id);
    }

    /**
     * 查询系统/渠道配置列表
     * 
     * @param eyeClientConfig 系统/渠道配置
     * @return 系统/渠道配置
     */
    @Override
    public List<EyeClientConfig> selectEyeClientConfigList(EyeClientConfig eyeClientConfig)
    {
        return eyeClientConfigMapper.selectEyeClientConfigList(eyeClientConfig);
    }

    /**
     * 新增系统/渠道配置
     * 
     * @param eyeClientConfig 系统/渠道配置
     * @return 结果
     */
    @Override
    public int insertEyeClientConfig(EyeClientConfig eyeClientConfig)
    {
        eyeClientConfig.setCreateTime(DateUtils.getNowDate());
		eyeClientConfig.setCreateUser(ShiroUtils.getLoginName());
        return eyeClientConfigMapper.insertEyeClientConfig(eyeClientConfig);
    }

    /**
     * 修改系统/渠道配置
     * 
     * @param eyeClientConfig 系统/渠道配置
     * @return 结果
     */
    @Override
    public int updateEyeClientConfig(EyeClientConfig eyeClientConfig)
    {
        eyeClientConfig.setUpdateTime(DateUtils.getNowDate());
		eyeClientConfig.setUpdateUser(ShiroUtils.getLoginName());
        return eyeClientConfigMapper.updateEyeClientConfig(eyeClientConfig);
    }

    /**
     * 删除系统/渠道配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyeClientConfigByIds(String ids)
    {
        return eyeClientConfigMapper.deleteEyeClientConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除系统/渠道配置信息
     * 
     * @param id 系统/渠道配置ID
     * @return 结果
     */
    @Override
    public int deleteEyeClientConfigById(Long id)
    {
        return eyeClientConfigMapper.deleteEyeClientConfigById(id);
    }

	@Override
	public EyeClientConfig selectClientConfig(String systemCode,String consumerID) {
		EyeClientConfig queryConfig = new EyeClientConfig();
		queryConfig.setClientCode(systemCode);
		queryConfig.setAuthorizeId(consumerID);
		List<EyeClientConfig> clientList = eyeClientConfigMapper.selectEyeClientConfigList(queryConfig);
		if(clientList!=null&&clientList.size()>0) return clientList.get(0);// 取第一个
		return null;
	}
}
