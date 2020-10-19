package ins.webeye.project.eye.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.security.ShiroUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.order.domain.EyeOrderPage;
import ins.webeye.project.eye.order.mapper.EyeOrderPageMapper;
import ins.webeye.project.eye.order.service.IEyeOrderPageService;

/**
 * 订单录制记录Service业务层处理
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
@Service
public class EyeOrderPageServiceImpl implements IEyeOrderPageService 
{
    @Autowired
    private EyeOrderPageMapper eyeOrderPageMapper;

    /**
     * 查询订单录制记录
     * 
     * @param id 订单录制记录ID
     * @return 订单录制记录
     */
    @Override
    public EyeOrderPage selectEyeOrderPageById(Long id)
    {
        return eyeOrderPageMapper.selectEyeOrderPageById(id);
    }

    /**
     * 查询订单录制记录列表
     * 
     * @param eyeOrderPage 订单录制记录
     * @return 订单录制记录
     */
    @Override
    public List<EyeOrderPage> selectEyeOrderPageList(EyeOrderPage eyeOrderPage)
    {
        return eyeOrderPageMapper.selectEyeOrderPageList(eyeOrderPage);
    }

    /**
     * 新增订单录制记录
     * 
     * @param eyeOrderPage 订单录制记录
     * @return 结果
     */
    @Override
    public int insertEyeOrderPage(EyeOrderPage eyeOrderPage)
    {
        return eyeOrderPageMapper.insertEyeOrderPage(eyeOrderPage);
    }

    /**
     * 修改订单录制记录
     * 
     * @param eyeOrderPage 订单录制记录
     * @return 结果
     */
    @Override
    public int updateEyeOrderPage(EyeOrderPage eyeOrderPage)
    {
        eyeOrderPage.setUpdateTime(DateUtils.getNowDate());
		if(eyeOrderPage.getUpdateUser()==null){
			//eyeOrderPage.setUpdateUser(ShiroUtils.getLoginName());
		}
        return eyeOrderPageMapper.updateEyeOrderPage(eyeOrderPage);
    }

    /**
     * 删除订单录制记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyeOrderPageByIds(String ids)
    {
        return eyeOrderPageMapper.deleteEyeOrderPageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单录制记录信息
     * 
     * @param id 订单录制记录ID
     * @return 结果
     */
    @Override
    public int deleteEyeOrderPageById(Long id)
    {
        return eyeOrderPageMapper.deleteEyeOrderPageById(id);
    }
    
    @Override
    public List<EyeOrderPage> selectPageListForNode(EyeOrderPage eyeOrderPage)
    {
        return eyeOrderPageMapper.selectPageListForNode(eyeOrderPage);
    }
}
