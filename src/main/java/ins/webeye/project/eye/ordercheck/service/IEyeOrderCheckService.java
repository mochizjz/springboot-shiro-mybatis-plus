package ins.webeye.project.eye.ordercheck.service;

import java.util.List;

import ins.webeye.project.eye.ordercheck.domain.EyeOrderCheck;
import ins.webeye.project.eye.vo.OrderInfoVo;

/**
 * 回溯对账查询Service接口
 * 
 * @author webeye
 * @date 2020-09-08
 */
public interface IEyeOrderCheckService 
{
    /**
     * 查询回溯对账查询
     * 
     * @param id 回溯对账查询ID
     * @return 回溯对账查询
     */
    public EyeOrderCheck selectEyeOrderCheckById(Long id);

    /**
     * 查询回溯对账查询列表
     * 
     * @param eyeOrderCheck 回溯对账查询
     * @return 回溯对账查询集合
     */
    public List<EyeOrderCheck> selectEyeOrderCheckList(EyeOrderCheck eyeOrderCheck);

    /**
	 * 新增或修改对账数据
	 * @param eyeOrderCheck 回溯对账查询
	 * @return 结果
	 */
	public int saveEyeOrderCheck(OrderInfoVo orderVo,int updateCount);

    /**
     * 修改回溯对账查询
     * 
     * @param eyeOrderCheck 回溯对账查询
     * @return 结果
     */
    public int updateEyeOrderCheck(EyeOrderCheck eyeOrderCheck);

    /**
     * 批量删除回溯对账查询
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeOrderCheckByIds(String ids);

    /**
     * 删除回溯对账查询信息
     * 
     * @param id 回溯对账查询ID
     * @return 结果
     */
    public int deleteEyeOrderCheckById(Long id);
}
