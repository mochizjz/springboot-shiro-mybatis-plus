package ins.webeye.project.eye.order.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import ins.webeye.project.eye.order.domain.EyeOrder;
import ins.webeye.project.eye.vo.QualityCheckVo;

/**
 * 订单记录Service接口
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
public interface IEyeOrderService extends IService<EyeOrder>
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
     * 批量删除订单记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeOrderByIds(String ids);

    /**
     * 删除订单记录信息
     * 
     * @param id 订单记录ID
     * @return 结果
     */
    public int deleteEyeOrderById(Long id);

    /**
     * 更新质检结果及信息
     * @param vo
     * @return
     */
    public int updateQuality(QualityCheckVo vo);

    /**
     * 通过保单号查询订单
     * @param policyNo
     * @return
     */
    public EyeOrder selectEyeOrderByPolicyNo(String policyNo);

	public int updateByOrderId(EyeOrder eyeOrder);

}
