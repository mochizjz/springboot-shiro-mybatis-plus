package ins.webeye.project.eye.ordercheck.mapper;

import java.util.List;

import ins.webeye.project.eye.ordercheck.domain.EyeOrderCheck;

/**
 * 回溯对账查询Mapper接口
 * 
 * @author webeye
 * @date 2020-09-08
 */
public interface EyeOrderCheckMapper 
{
    /**
     * 查询回溯对账查询
     * 
     * @param id 回溯对账查询ID
     * @return 回溯对账查询
     */
    public EyeOrderCheck selectEyeOrderCheckById(Long id);

	/**
	 * 查询回溯对账查询
	 * @param id 回溯对账查询ID
	 * @return 回溯对账查询
	 */
	public EyeOrderCheck selectEyeOrderCheckByOrderId(String orderId);

    /**
     * 查询回溯对账查询列表
     * 
     * @param eyeOrderCheck 回溯对账查询
     * @return 回溯对账查询集合
     */
    public List<EyeOrderCheck> selectEyeOrderCheckList(EyeOrderCheck eyeOrderCheck);

    /**
     * 新增回溯对账查询
     * 
     * @param eyeOrderCheck 回溯对账查询
     * @return 结果
     */
    public int insertEyeOrderCheck(EyeOrderCheck eyeOrderCheck);

    /**
     * 修改回溯对账查询
     * 
     * @param eyeOrderCheck 回溯对账查询
     * @return 结果
     */
    public int updateEyeOrderCheck(EyeOrderCheck eyeOrderCheck);

    /**
     * 删除回溯对账查询
     * 
     * @param id 回溯对账查询ID
     * @return 结果
     */
    public int deleteEyeOrderCheckById(Long id);

    /**
     * 批量删除回溯对账查询
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeOrderCheckByIds(String[] ids);
}
