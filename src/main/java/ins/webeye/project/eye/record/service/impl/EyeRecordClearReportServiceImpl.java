package ins.webeye.project.eye.record.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.security.ShiroUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.record.domain.EyeRecordClearReport;
import ins.webeye.project.eye.record.mapper.EyeRecordClearReportMapper;
import ins.webeye.project.eye.record.service.IEyeRecordClearReportService;

/**
 * 清理操作记录Service业务层处理
 * 
 * @author xiaoquan
 * @date 2020-07-20
 */
@Service
public class EyeRecordClearReportServiceImpl implements IEyeRecordClearReportService 
{
    @Autowired
    private EyeRecordClearReportMapper eyeRecordClearReportMapper;

    /**
     * 查询清理操作记录
     * 
     * @param id 清理操作记录ID
     * @return 清理操作记录
     */
    @Override
    public EyeRecordClearReport selectEyeRecordClearReportById(Long id)
    {
        return eyeRecordClearReportMapper.selectEyeRecordClearReportById(id);
    }

    /**
     * 查询清理操作记录列表
     * 
     * @param eyeRecordClearReport 清理操作记录
     * @return 清理操作记录
     */
    @Override
    public List<EyeRecordClearReport> selectEyeRecordClearReportList(EyeRecordClearReport eyeRecordClearReport)
    {
        return eyeRecordClearReportMapper.selectEyeRecordClearReportList(eyeRecordClearReport);
    }

    /**
     * 新增清理操作记录
     * 
     * @param eyeRecordClearReport 清理操作记录
     * @return 结果
     */
    @Override
    public int insertEyeRecordClearReport(EyeRecordClearReport eyeRecordClearReport)
    {
        eyeRecordClearReport.setCreateTime(DateUtils.getNowDate());
        return eyeRecordClearReportMapper.insertEyeRecordClearReport(eyeRecordClearReport);
    }

    /**
     * 修改清理操作记录
     * 
     * @param eyeRecordClearReport 清理操作记录
     * @return 结果
     */
    @Override
    public int updateEyeRecordClearReport(EyeRecordClearReport eyeRecordClearReport)
    {
        eyeRecordClearReport.setUpdateTime(DateUtils.getNowDate());
        return eyeRecordClearReportMapper.updateEyeRecordClearReport(eyeRecordClearReport);
    }

    /**
     * 删除清理操作记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyeRecordClearReportByIds(String ids)
    {
        return eyeRecordClearReportMapper.deleteEyeRecordClearReportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除清理操作记录信息
     * 
     * @param id 清理操作记录ID
     * @return 结果
     */
    @Override
    public int deleteEyeRecordClearReportById(Long id)
    {
        return eyeRecordClearReportMapper.deleteEyeRecordClearReportById(id);
    }
}
