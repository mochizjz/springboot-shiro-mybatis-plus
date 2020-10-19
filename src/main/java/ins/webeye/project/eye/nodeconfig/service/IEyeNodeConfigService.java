package ins.webeye.project.eye.nodeconfig.service;

import java.util.List;

import ins.webeye.project.eye.nodeconfig.domain.EyeNodeConfig;

/**
 * 节点配置Service接口
 * 
 * @author xiaoquan
 * @date 2020-07-16
 */
public interface IEyeNodeConfigService 
{
    /**
     * 查询节点配置
     * 
     * @param id 节点配置ID
     * @return 节点配置
     */
    public EyeNodeConfig selectEyeNodeConfigById(Long id);

    /**
     * 查询节点配置列表
     * 
     * @param eyeNodeConfig 节点配置
     * @return 节点配置集合
     */
    public List<EyeNodeConfig> selectEyeNodeConfigList(EyeNodeConfig eyeNodeConfig);

    /**
     * 新增节点配置
     * 
     * @param eyeNodeConfig 节点配置
     * @return 结果
     */
    public int insertEyeNodeConfig(EyeNodeConfig eyeNodeConfig);

    /**
     * 修改节点配置
     * 
     * @param eyeNodeConfig 节点配置
     * @return 结果
     */
    public int updateEyeNodeConfig(EyeNodeConfig eyeNodeConfig);

    /**
     * 批量删除节点配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeNodeConfigByIds(String ids);

    /**
     * 删除节点配置信息
     * 
     * @param id 节点配置ID
     * @return 结果
     */
    public int deleteEyeNodeConfigById(Long id);
    
    /**
     * 根据产品编码查询对应的节点配置信息
     * @param eyeNodeConfig
     * @return
     */
    public List<EyeNodeConfig> selectEyeNodeConfigByProductCodeList(EyeNodeConfig eyeNodeConfig);
}
