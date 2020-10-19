package ins.webeye.project.eye.record.service;

import java.util.List;

import ins.webeye.project.eye.record.domain.EyeRecordClearReport;

/**
 * 清理操作记录Service接口
 * 
 * @author xiaoquan
 * @date 2020-07-20
 */
public interface IEyeRecordClearReportService 
{
    /**
     * 查询清理操作记录
     * 
     * @param id 清理操作记录ID
     * @return 清理操作记录
     */
    public EyeRecordClearReport selectEyeRecordClearReportById(Long id);

    /**
     * 查询清理操作记录列表
     * 
     * @param eyeRecordClearReport 清理操作记录
     * @return 清理操作记录集合
     */
    public List<EyeRecordClearReport> selectEyeRecordClearReportList(EyeRecordClearReport eyeRecordClearReport);

    /**
     * 新增清理操作记录
     * 
     * @param eyeRecordClearReport 清理操作记录
     * @return 结果
     */
    public int insertEyeRecordClearReport(EyeRecordClearReport eyeRecordClearReport);

    /**
     * 修改清理操作记录
     * 
     * @param eyeRecordClearReport 清理操作记录
     * @return 结果
     */
    public int updateEyeRecordClearReport(EyeRecordClearReport eyeRecordClearReport);

    /**
     * 批量删除清理操作记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeRecordClearReportByIds(String ids);

    /**
     * 删除清理操作记录信息
     * 
     * @param id 清理操作记录ID
     * @return 结果
     */
    public int deleteEyeRecordClearReportById(Long id);
}
