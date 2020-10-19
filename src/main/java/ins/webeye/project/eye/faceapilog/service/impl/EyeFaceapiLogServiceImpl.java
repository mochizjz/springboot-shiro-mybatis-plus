package ins.webeye.project.eye.faceapilog.service.impl;

import java.util.List;
import ins.webeye.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.faceapilog.domain.EyeFaceapiLog;
import ins.webeye.project.eye.faceapilog.mapper.EyeFaceapiLogMapper;
import ins.webeye.project.eye.faceapilog.service.IEyeFaceapiLogService;

/**
 * 实名验证接口日志Service业务层处理
 * 
 * @author XiaoQuan
 * @date 2020-08-16
 */
@Service
public class EyeFaceapiLogServiceImpl implements IEyeFaceapiLogService 
{
    @Autowired
    private EyeFaceapiLogMapper eyeFaceapiLogMapper;

    /**
     * 查询实名验证接口日志
     * 
     * @param id 实名验证接口日志ID
     * @return 实名验证接口日志
     */
    @Override
    public EyeFaceapiLog selectEyeFaceapiLogById(Long id)
    {
        return eyeFaceapiLogMapper.selectEyeFaceapiLogById(id);
    }

    /**
     * 查询实名验证接口日志列表
     * 
     * @param eyeFaceapiLog 实名验证接口日志
     * @return 实名验证接口日志
     */
    @Override
    public List<EyeFaceapiLog> selectEyeFaceapiLogList(EyeFaceapiLog eyeFaceapiLog)
    {
        return eyeFaceapiLogMapper.selectEyeFaceapiLogList(eyeFaceapiLog);
    }

    /**
     * 新增实名验证接口日志
     * 
     * @param eyeFaceapiLog 实名验证接口日志
     * @return 结果
     */
    @Override
    public int insertEyeFaceapiLog(EyeFaceapiLog eyeFaceapiLog)
    {
        eyeFaceapiLog.setCreateTime(DateUtils.getNowDate());
        return eyeFaceapiLogMapper.insertEyeFaceapiLog(eyeFaceapiLog);
    }

    /**
     * 修改实名验证接口日志
     * 
     * @param eyeFaceapiLog 实名验证接口日志
     * @return 结果
     */
    @Override
    public int updateEyeFaceapiLog(EyeFaceapiLog eyeFaceapiLog)
    {
        eyeFaceapiLog.setUpdateTime(DateUtils.getNowDate());
        return eyeFaceapiLogMapper.updateEyeFaceapiLog(eyeFaceapiLog);
    }

    /**
     * 删除实名验证接口日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyeFaceapiLogByIds(String ids)
    {
        return eyeFaceapiLogMapper.deleteEyeFaceapiLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除实名验证接口日志信息
     * 
     * @param id 实名验证接口日志ID
     * @return 结果
     */
    @Override
    public int deleteEyeFaceapiLogById(Long id)
    {
        return eyeFaceapiLogMapper.deleteEyeFaceapiLogById(id);
    }
}
