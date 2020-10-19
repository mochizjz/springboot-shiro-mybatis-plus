package ins.webeye.project.eye.checklog.mapper;

import java.util.Date;
import java.util.List;

import ins.webeye.project.eye.checklog.domain.EyeCheckLog;

/**
 * 质检轨迹Mapper接口
 * 
 * @author wangtao
 * @date 2020-08-20
 */
public interface EyeCheckLogMapper 
{
    /**
     * 查询质检轨迹
     * 
     * @param id 质检轨迹ID
     * @return 质检轨迹
     */
    public EyeCheckLog selectEyeCheckLogById(Long id);

    /**
     * 查询质检轨迹列表
     * 
     * @param eyeCheckLog 质检轨迹
     * @return 质检轨迹集合
     */
    public List<EyeCheckLog> selectEyeCheckLogList(EyeCheckLog eyeCheckLog);

    /**
     * 新增质检轨迹
     * 
     * @param eyeCheckLog 质检轨迹
     * @return 结果
     */
    public int insertEyeCheckLog(EyeCheckLog eyeCheckLog);

    /**
     * 修改质检轨迹
     * 
     * @param eyeCheckLog 质检轨迹
     * @return 结果
     */
    public int updateEyeCheckLog(EyeCheckLog eyeCheckLog);

    /**
     * 删除质检轨迹
     * 
     * @param id 质检轨迹ID
     * @return 结果
     */
    public int deleteEyeCheckLogById(Long id);

    /**
     * 批量删除质检轨迹
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeCheckLogByIds(String[] ids);

    /**
     * 通过状态更新质检轨迹
     * @param orderId
     * @param status
     * @return
     */
    public int updateEyeCheckLogByOrderId(String orderId, String status, String updateUser, Date updateTime);
}
