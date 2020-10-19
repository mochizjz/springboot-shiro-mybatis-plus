package ins.webeye.project.eye.recallmanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.recallmanage.domain.EyeRecallmanage;
import ins.webeye.project.eye.recallmanage.mapper.EyeRecallmanageMapper;
import ins.webeye.project.eye.recallmanage.service.IEyeRecallmanageService;

/**
 * 回溯管理Service业务层处理
 * 
 * @author webeye
 * @date 2020-07-08
 */
@Service
public class EyeRecallmanageServiceImpl implements IEyeRecallmanageService 
{
    @Autowired
    private EyeRecallmanageMapper eyeRecallmanageMapper;

    /**
     * 查询回溯管理
     * 
     * @param id 回溯管理ID
     * @return 回溯管理
     */
    @Override
    public EyeRecallmanage selectRecallmanageById(Integer id)
    {
        return eyeRecallmanageMapper.selectRecallmanageById(id);
    }

    /**
     * 查询回溯管理列表
     * 
     * @param recallmanage 回溯管理
     * @return 回溯管理
     */
    @Override
    public List<EyeRecallmanage> selectRecallmanageList(EyeRecallmanage recallmanage)
    {
        return eyeRecallmanageMapper.selectRecallmanageList(recallmanage);
    }

    /**
     * 新增回溯管理
     * 
     * @param recallmanage 回溯管理
     * @return 结果
     */
    @Override
    public int insertRecallmanage(EyeRecallmanage recallmanage)
    {
        return eyeRecallmanageMapper.insertRecallmanage(recallmanage);
    }

    /**
     * 修改回溯管理
     * 
     * @param recallmanage 回溯管理
     * @return 结果
     */
    @Override
    public int updateRecallmanage(EyeRecallmanage recallmanage)
    {
        return eyeRecallmanageMapper.updateRecallmanage(recallmanage);
    }

    /**
     * 删除回溯管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRecallmanageByIds(String ids)
    {
        return eyeRecallmanageMapper.deleteRecallmanageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除回溯管理信息
     * 
     * @param id 回溯管理ID
     * @return 结果
     */
    @Override
    public int deleteRecallmanageById(Integer id)
    {
        return eyeRecallmanageMapper.deleteRecallmanageById(id);
    }
}
