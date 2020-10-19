package ins.webeye.project.eye.clientconfig.service;

import java.util.List;

import ins.webeye.project.eye.clientconfig.domain.EyeClientConfig;

/**
 * 系统/渠道配置Service接口
 * 
 * @author webeye
 * @date 2020-08-30
 */
public interface IEyeClientConfigService 
{
    /**
     * 查询系统/渠道配置
     * 
     * @param id 系统/渠道配置ID
     * @return 系统/渠道配置
     */
    public EyeClientConfig selectEyeClientConfigById(Long id);

    /**
     * 查询系统/渠道配置列表
     * 
     * @param eyeClientConfig 系统/渠道配置
     * @return 系统/渠道配置集合
     */
    public List<EyeClientConfig> selectEyeClientConfigList(EyeClientConfig eyeClientConfig);

    /**
     * 新增系统/渠道配置
     * 
     * @param eyeClientConfig 系统/渠道配置
     * @return 结果
     */
    public int insertEyeClientConfig(EyeClientConfig eyeClientConfig);

    /**
     * 修改系统/渠道配置
     * 
     * @param eyeClientConfig 系统/渠道配置
     * @return 结果
     */
    public int updateEyeClientConfig(EyeClientConfig eyeClientConfig);

    /**
     * 批量删除系统/渠道配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeClientConfigByIds(String ids);

    /**
     * 删除系统/渠道配置信息
     * 
     * @param id 系统/渠道配置ID
     * @return 结果
     */
    public int deleteEyeClientConfigById(Long id);

	/**
	 * 根据系统和消费方ID找到客户端配置
	 * @param systemCode
	 * @param consumerID
	 * @return
	 */
	public EyeClientConfig selectClientConfig(String systemCode,String consumerID);
}
