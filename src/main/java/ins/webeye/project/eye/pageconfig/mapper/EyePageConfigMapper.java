package ins.webeye.project.eye.pageconfig.mapper;

import java.util.List;

import ins.webeye.project.eye.pageconfig.domain.EyePageConfig;

/**
 * 页面管理Mapper接口
 * 
 * @author xiaoquan
 * @date 2020-08-02
 */
public interface EyePageConfigMapper 
{
    /**
     * 查询页面管理
     * 
     * @param id 页面管理ID
     * @return 页面管理
     */
    public EyePageConfig selectEyePageConfigById(Long id);

    /**
     * 查询页面管理列表
     * 
     * @param eyePageConfig 页面管理
     * @return 页面管理集合
     */
    public List<EyePageConfig> selectEyePageConfigList(EyePageConfig eyePageConfig);

    /**
     * 新增页面管理
     * 
     * @param eyePageConfig 页面管理
     * @return 结果
     */
    public int insertEyePageConfig(EyePageConfig eyePageConfig);

    /**
     * 修改页面管理
     * 
     * @param eyePageConfig 页面管理
     * @return 结果
     */
    public int updateEyePageConfig(EyePageConfig eyePageConfig);

    /**
     * 删除页面管理
     * 
     * @param id 页面管理ID
     * @return 结果
     */
    public int deleteEyePageConfigById(Long id);

    /**
     * 批量删除页面管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyePageConfigByIds(String[] ids);
}
