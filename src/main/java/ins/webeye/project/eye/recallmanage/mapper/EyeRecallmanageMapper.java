package ins.webeye.project.eye.recallmanage.mapper;

import ins.webeye.project.eye.recallmanage.domain.EyeRecallmanage;

import java.util.List;


/**
 * 回溯管理Mapper接口
 * 
 * @author webeye
 * @date 2020-07-08
 */
public interface EyeRecallmanageMapper 
{
    /**
     * 查询回溯管理
     * 
     * @param id 回溯管理ID
     * @return 回溯管理
     */
    public EyeRecallmanage selectRecallmanageById(Integer id);

    /**
     * 查询回溯管理列表
     * 
     * @param recallmanage 回溯管理
     * @return 回溯管理集合
     */
    public List<EyeRecallmanage> selectRecallmanageList(EyeRecallmanage recallmanage);

    /**
     * 新增回溯管理
     * 
     * @param recallmanage 回溯管理
     * @return 结果
     */
    public int insertRecallmanage(EyeRecallmanage recallmanage);

    /**
     * 修改回溯管理
     * 
     * @param recallmanage 回溯管理
     * @return 结果
     */
    public int updateRecallmanage(EyeRecallmanage recallmanage);

    /**
     * 删除回溯管理
     * 
     * @param id 回溯管理ID
     * @return 结果
     */
    public int deleteRecallmanageById(Integer id);

    /**
     * 批量删除回溯管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRecallmanageByIds(String[] ids);
}
