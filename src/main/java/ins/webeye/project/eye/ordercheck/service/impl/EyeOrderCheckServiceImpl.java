package ins.webeye.project.eye.ordercheck.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.ordercheck.domain.EyeOrderCheck;
import ins.webeye.project.eye.ordercheck.mapper.EyeOrderCheckMapper;
import ins.webeye.project.eye.ordercheck.service.IEyeOrderCheckService;
import ins.webeye.project.eye.vo.OrderInfoVo;

/**
 * 回溯对账查询Service业务层处理
 * 
 * @author webeye
 * @date 2020-09-08
 */
@Service
public class EyeOrderCheckServiceImpl implements IEyeOrderCheckService 
{
    @Autowired
    private EyeOrderCheckMapper eyeOrderCheckMapper;

    /**
     * 查询回溯对账查询
     * 
     * @param id 回溯对账查询ID
     * @return 回溯对账查询
     */
    @Override
    public EyeOrderCheck selectEyeOrderCheckById(Long id)
    {
        return eyeOrderCheckMapper.selectEyeOrderCheckById(id);
    }

    /**
     * 查询回溯对账查询列表
     * 
     * @param eyeOrderCheck 回溯对账查询
     * @return 回溯对账查询
     */
    @Override
    public List<EyeOrderCheck> selectEyeOrderCheckList(EyeOrderCheck eyeOrderCheck)
    {
        return eyeOrderCheckMapper.selectEyeOrderCheckList(eyeOrderCheck);
    }

    /**
     * 新增回溯对账查询
     * 
     * @param eyeOrderCheck 回溯对账查询
     * @return 结果
     */
    @Override
	public int saveEyeOrderCheck(OrderInfoVo orderVo,int updateCount)
    {
		String orderId = orderVo.getOrderId();
		if(StringUtils.isBlank(orderId)) return 0;
		EyeOrderCheck eyeOrderCheck = new EyeOrderCheck();
		eyeOrderCheck.setSysCode(orderVo.getSystemCode());
		eyeOrderCheck.setOrderId(orderVo.getOrderId());
		eyeOrderCheck.setProductCode(orderVo.getProductCode());
		eyeOrderCheck.setPolicyNo(orderVo.getPolicyNo());
		eyeOrderCheck.setPolicyIdcard(orderVo.getPolicyIdcard());
		eyeOrderCheck.setPolicyName(orderVo.getPolicyName());
		eyeOrderCheck.setPayStatus(orderVo.getPayStatus());
		eyeOrderCheck.setToken(orderVo.getToken());
		eyeOrderCheck.setCheckStatus("0");// 0-通过，1-未通过
		eyeOrderCheck.setCheckMessage("同步成功");
		eyeOrderCheck.setUpdateTime(DateUtils.getNowDate());
		eyeOrderCheck.setUpdateBy(orderVo.getConsumerID());
		if(updateCount<1){// 更新数量为0 ，同步失败了
			eyeOrderCheck.setCheckStatus("1");// 0-通过，1-未通过
			eyeOrderCheck.setCheckMessage("同步失败:未找到相关录制记录");
		}

		EyeOrderCheck dbOrderCheck = eyeOrderCheckMapper.selectEyeOrderCheckByOrderId(orderVo.getOrderId());
		if(dbOrderCheck==null){// 新增操作
			eyeOrderCheck.setCreateTime(DateUtils.getNowDate());
			eyeOrderCheck.setCreateBy(orderVo.getConsumerID());
			return eyeOrderCheckMapper.insertEyeOrderCheck(eyeOrderCheck);
		}else{// 更新操作
			eyeOrderCheck.setId(dbOrderCheck.getId());
			return eyeOrderCheckMapper.updateEyeOrderCheck(eyeOrderCheck);
		}

    }

    /**
     * 修改回溯对账查询
     * 
     * @param eyeOrderCheck 回溯对账查询
     * @return 结果
     */
    @Override
    public int updateEyeOrderCheck(EyeOrderCheck eyeOrderCheck)
    {
        eyeOrderCheck.setUpdateTime(DateUtils.getNowDate());
        return eyeOrderCheckMapper.updateEyeOrderCheck(eyeOrderCheck);
    }

    /**
     * 删除回溯对账查询对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyeOrderCheckByIds(String ids)
    {
        return eyeOrderCheckMapper.deleteEyeOrderCheckByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除回溯对账查询信息
     * 
     * @param id 回溯对账查询ID
     * @return 结果
     */
    @Override
    public int deleteEyeOrderCheckById(Long id)
    {
        return eyeOrderCheckMapper.deleteEyeOrderCheckById(id);
    }
}
