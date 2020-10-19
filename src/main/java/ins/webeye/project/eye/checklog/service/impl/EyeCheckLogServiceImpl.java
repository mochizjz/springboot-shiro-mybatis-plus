package ins.webeye.project.eye.checklog.service.impl;

import java.util.List;
import ins.webeye.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.checklog.domain.EyeCheckLog;
import ins.webeye.project.eye.checklog.mapper.EyeCheckLogMapper;
import ins.webeye.project.eye.checklog.service.IEyeCheckLogService;

/**
 * 质检轨迹Service业务层处理
 * 
 * @author wangtao
 * @date 2020-08-20
 */
@Service
public class EyeCheckLogServiceImpl implements IEyeCheckLogService 
{
    @Autowired
    private EyeCheckLogMapper eyeCheckLogMapper;

    /**
     * 查询质检轨迹
     * 
     * @param id 质检轨迹ID
     * @return 质检轨迹
     */
    @Override
    public EyeCheckLog selectEyeCheckLogById(Long id)
    {
        return eyeCheckLogMapper.selectEyeCheckLogById(id);
    }

    /**
     * 查询质检轨迹列表
     * 
     * @param eyeCheckLog 质检轨迹
     * @return 质检轨迹
     */
    @Override
    public List<EyeCheckLog> selectEyeCheckLogList(EyeCheckLog eyeCheckLog)
    {
        return eyeCheckLogMapper.selectEyeCheckLogList(eyeCheckLog);
    }

    /**
     * 新增质检轨迹
     * 
     * @param eyeCheckLog 质检轨迹
     * @return 结果
     */
    @Override
    public int insertEyeCheckLog(EyeCheckLog eyeCheckLog)
    {
        eyeCheckLog.setCreateTime(DateUtils.getNowDate());
        return eyeCheckLogMapper.insertEyeCheckLog(eyeCheckLog);
    }

    /**
     * 修改质检轨迹
     * 
     * @param eyeCheckLog 质检轨迹
     * @return 结果
     */
    @Override
    public int updateEyeCheckLog(EyeCheckLog eyeCheckLog)
    {
        return eyeCheckLogMapper.updateEyeCheckLog(eyeCheckLog);
    }

    /**
     * 删除质检轨迹对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyeCheckLogByIds(String ids)
    {
        return eyeCheckLogMapper.deleteEyeCheckLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除质检轨迹信息
     * 
     * @param id 质检轨迹ID
     * @return 结果
     */
    @Override
    public int deleteEyeCheckLogById(Long id)
    {
        return eyeCheckLogMapper.deleteEyeCheckLogById(id);
    }
}
