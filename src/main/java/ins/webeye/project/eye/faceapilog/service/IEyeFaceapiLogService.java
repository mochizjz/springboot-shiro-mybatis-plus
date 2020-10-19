package ins.webeye.project.eye.faceapilog.service;

import java.util.List;

import ins.webeye.project.eye.faceapilog.domain.EyeFaceapiLog;

/**
 * 实名验证接口日志Service接口
 * 
 * @author XiaoQuan
 * @date 2020-08-16
 */
public interface IEyeFaceapiLogService 
{
    /**
     * 查询实名验证接口日志
     * 
     * @param id 实名验证接口日志ID
     * @return 实名验证接口日志
     */
    public EyeFaceapiLog selectEyeFaceapiLogById(Long id);

    /**
     * 查询实名验证接口日志列表
     * 
     * @param eyeFaceapiLog 实名验证接口日志
     * @return 实名验证接口日志集合
     */
    public List<EyeFaceapiLog> selectEyeFaceapiLogList(EyeFaceapiLog eyeFaceapiLog);

    /**
     * 新增实名验证接口日志
     * 
     * @param eyeFaceapiLog 实名验证接口日志
     * @return 结果
     */
    public int insertEyeFaceapiLog(EyeFaceapiLog eyeFaceapiLog);

    /**
     * 修改实名验证接口日志
     * 
     * @param eyeFaceapiLog 实名验证接口日志
     * @return 结果
     */
    public int updateEyeFaceapiLog(EyeFaceapiLog eyeFaceapiLog);

    /**
     * 批量删除实名验证接口日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeFaceapiLogByIds(String ids);

    /**
     * 删除实名验证接口日志信息
     * 
     * @param id 实名验证接口日志ID
     * @return 结果
     */
    public int deleteEyeFaceapiLogById(Long id);
}
