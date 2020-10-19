package ins.webeye.project.eye.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ins.webeye.project.eye.order.domain.EyeOrder;

import java.util.List;

/**
 * 订单记录Mapper接口
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
public interface EyeOrderMapper extends BaseMapper<EyeOrder>
{
    /**
     * 查询订单记录
     * 
     * @param id 订单记录ID
     * @return 订单记录
     */
    public EyeOrder selectEyeOrderById(Long id);

    /**
     * 查询订单记录列表
     * 
     * @param eyeOrder 订单记录
     * @return 订单记录集合
     */
    public List<EyeOrder> selectEyeOrderList(EyeOrder eyeOrder);

    /**
     * 新增订单记录
     * 
     * @param eyeOrder 订单记录
     * @return 结果
     */
    public int insertEyeOrder(EyeOrder eyeOrder);

    /**
     * 修改订单记录
     * 
     * @param eyeOrder 订单记录
     * @return 结果
     */
    public int updateEyeOrder(EyeOrder eyeOrder);

    /**
     * 删除订单记录
     * 
     * @param id 订单记录ID
     * @return 结果
     */
    public int deleteEyeOrderById(Long id);

    /**
     * 批量删除订单记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeOrderByIds(String[] ids);

	/**
     * 修改质检记录
     * 
     * @param orderId 订单号
     * @return 结果
     */
    public EyeOrder selectEyeOrderByOrderId(String orderId);

    /**
     * 通过保单号查询订单
     * @param policyNo
     * @return
     */
	public EyeOrder selectEyeOrderByPolicyNo(String policyNo);

	public int updateByOrderId(EyeOrder eyeOrder);
	
	/**
	 * 查询当日已完成订单数量
	 * @return
	 * @modified:
	 * ☆XiaoQuan(2020年9月13日 ): <br>
	 */
	public int selectOrderDayNum();
	/**
	 * 查询当月已完成订单数量
	 */
	public int selectOrderMonthNum();
	/**
	 * 查询当日已完成订单数量质检不通过的
	 */
	public int selectOrderDayFailNum();
}
