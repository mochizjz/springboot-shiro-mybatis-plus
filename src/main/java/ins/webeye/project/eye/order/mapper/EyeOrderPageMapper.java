package ins.webeye.project.eye.order.mapper;

import java.util.List;

import ins.webeye.project.eye.order.domain.EyeOrderPage;

/**
 * 订单录制记录Mapper接口
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
public interface EyeOrderPageMapper 
{
    /**
     * 查询订单录制记录
     * 
     * @param id 订单录制记录ID
     * @return 订单录制记录
     */
    public EyeOrderPage selectEyeOrderPageById(Long id);

    /**
     * 查询订单录制记录列表
     * 
     * @param eyeOrderPage 订单录制记录
     * @return 订单录制记录集合
     */
    public List<EyeOrderPage> selectEyeOrderPageList(EyeOrderPage eyeOrderPage);

    /**
     * 新增订单录制记录
     * 
     * @param eyeOrderPage 订单录制记录
     * @return 结果
     */
    public int insertEyeOrderPage(EyeOrderPage eyeOrderPage);

    /**
     * 修改订单录制记录
     * 
     * @param eyeOrderPage 订单录制记录
     * @return 结果
     */
    public int updateEyeOrderPage(EyeOrderPage eyeOrderPage);

    /**
     * 删除订单录制记录
     * 
     * @param id 订单录制记录ID
     * @return 结果
     */
    public int deleteEyeOrderPageById(Long id);

    /**
     * 批量删除订单录制记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeOrderPageByIds(String[] ids);
    /**
     * 当日已完成订单录制明细数量
     */
    public int selectOrderPageNum();
    
    /**
     * 质检时根据查询条件拼装质检节点数据
     */
    public List<EyeOrderPage> selectPageListForNode(EyeOrderPage eyeOrderPage);

}
