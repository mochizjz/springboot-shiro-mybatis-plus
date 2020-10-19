package ins.webeye.project.eye.order.service.impl;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.security.ShiroUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.checklog.domain.EyeCheckLog;
import ins.webeye.project.eye.checklog.mapper.EyeCheckLogMapper;
import ins.webeye.project.eye.order.domain.EyeOrder;
import ins.webeye.project.eye.order.mapper.EyeOrderMapper;
import ins.webeye.project.eye.order.service.IEyeOrderService;
import ins.webeye.project.eye.vo.QualityCheckVo;

/**
 * 订单记录Service业务层处理
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
@Service
public class EyeOrderServiceImpl extends ServiceImpl<EyeOrderMapper, EyeOrder> implements IEyeOrderService
{
    @Autowired
    private EyeOrderMapper eyeOrderMapper;
    @Autowired
    private EyeCheckLogMapper eyeCheckLogMapper;

    /**
     * 查询订单记录
     *
     * @param id 订单记录ID
     * @return 订单记录
     */
    @Override
    public EyeOrder selectEyeOrderById(Long id)
    {
        return eyeOrderMapper.selectEyeOrderById(id);
    }

    /**
     * 查询订单记录列表
     *
     * @param eyeOrder 订单记录
     * @return 订单记录
     */
    @Override
    public List<EyeOrder> selectEyeOrderList(EyeOrder eyeOrder)
    {
        return eyeOrderMapper.selectEyeOrderList(eyeOrder);
    }

    /**
     * 新增订单记录
     *
     * @param eyeOrder 订单记录
     * @return 结果
     */
    @Override
    public int insertEyeOrder(EyeOrder eyeOrder)
    {
        return eyeOrderMapper.insertEyeOrder(eyeOrder);
    }

    /**
     * 修改订单记录
     *
     * @param eyeOrder 订单记录
     * @return 结果
     */
    @Override
    public int updateEyeOrder(EyeOrder eyeOrder)
    {
        eyeOrder.setUpdateTime(DateUtils.getNowDate());
		if(eyeOrder.getUpdateUser()==null){
			//eyeOrder.setUpdateUser(ShiroUtils.getLoginName());
		}
        return eyeOrderMapper.updateEyeOrder(eyeOrder);
    }

    /**
     * 删除订单记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyeOrderByIds(String ids)
    {
        return eyeOrderMapper.deleteEyeOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单记录信息
     *
     * @param id 订单记录ID
     * @return 结果
     */
    @Override
    public int deleteEyeOrderById(Long id)
    {
        return eyeOrderMapper.deleteEyeOrderById(id);
    }

    /**
     * 更新质检结果
     * @param vo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateQuality(QualityCheckVo vo) {

        String userName = ShiroUtils.getLoginName();
        Date nowDate = DateUtils.getNowDate();

        EyeOrder eyeOrder = eyeOrderMapper.selectEyeOrderByOrderId(vo.getOrderId());
        eyeOrder.setCheckStatus(vo.getCheckStatus());
        eyeOrder.setCheckMessage(vo.getCheckMessage());
        eyeOrder.setCheckTime(nowDate);
        eyeOrder.setUpdateTime(nowDate);
        eyeOrder.setUpdateUser(userName);
        int num = eyeOrderMapper.updateEyeOrder(eyeOrder);

        eyeCheckLogMapper.updateEyeCheckLogByOrderId(eyeOrder.getOrderId(),"1",userName,nowDate);

        EyeCheckLog eyeCheckLog = new EyeCheckLog();
        eyeCheckLog.setEyeOrderId(eyeOrder.getId());
        eyeCheckLog.setOrderId(eyeOrder.getOrderId());
        eyeCheckLog.setPolicyNo(eyeOrder.getPolicyNo());
        eyeCheckLog.setCheckStatus(eyeOrder.getCheckStatus());
        eyeCheckLog.setCheckMessage(eyeOrder.getCheckMessage());
        eyeCheckLog.setCheckTime(nowDate);
        eyeCheckLog.setCreateTime(nowDate);
        eyeCheckLog.setCreateUser(userName);
        eyeCheckLog.setUpdateUser(userName);
        eyeCheckLog.setUpdateTime(nowDate);
        eyeCheckLog.setSysCode(eyeOrder.getSysCode());
        eyeCheckLogMapper.insertEyeCheckLog(eyeCheckLog);
        return num;
    }

	/**
     * 通过保单号查询订单
     * @param policyNo
     * @return
     */
    @Override
    public EyeOrder selectEyeOrderByPolicyNo(String policyNo) {
        EyeOrder eyeOrder = eyeOrderMapper.selectEyeOrderByPolicyNo(policyNo);
        return eyeOrder;
    }

	@Override
	public int updateByOrderId(EyeOrder eyeOrder) {
		return eyeOrderMapper.updateByOrderId(eyeOrder);
	}
}
