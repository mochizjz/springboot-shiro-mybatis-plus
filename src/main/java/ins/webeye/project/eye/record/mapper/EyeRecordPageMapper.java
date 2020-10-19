package ins.webeye.project.eye.record.mapper;

import java.util.List;
import java.util.Map;

import ins.webeye.project.eye.record.domain.EyeRecordPage;

/**
 * 页面录制记录Mapper接口
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
public interface EyeRecordPageMapper 
{
    /**
     * 查询页面录制记录
     * 
     * @param id 页面录制记录ID
     * @return 页面录制记录
     */
    public EyeRecordPage selectEyeRecordPageById(Long id);

    /**
     * 查询页面录制记录列表
     * 
     * @param eyeRecordPage 页面录制记录
     * @return 页面录制记录集合
     */
    public List<EyeRecordPage> selectEyeRecordPageList(EyeRecordPage eyeRecordPage);

    /**
     * 新增页面录制记录
     * 
     * @param eyeRecordPage 页面录制记录
     * @return 结果
     */
    public int insertEyeRecordPage(EyeRecordPage eyeRecordPage);

    /**
     * 修改页面录制记录
     * 
     * @param eyeRecordPage 页面录制记录
     * @return 结果
     */
    public int updateEyeRecordPage(EyeRecordPage eyeRecordPage);
    /**
     * 修改页面录制记录
     * 
     * @param eyeRecordPage 页面录制记录
     * @return 结果
     */
	public int updateEyeRecordPageByOrderId(EyeRecordPage eyeRecordPage);
	
	/**
	 * 根据token与产品编码批量更新
	 * @param eyeRecordPage
	 * @return
	 * @modified:
	 * ☆XiaoQuan(2020年7月28日 ): <br>
	 */
	public int updateEyeRecordPageByToken(EyeRecordPage eyeRecordPage);

    /**
     * 删除页面录制记录
     * 
     * @param id 页面录制记录ID
     * @return 结果
     */
    public int deleteEyeRecordPageById(Long id);

    /**
     * 批量删除页面录制记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeRecordPageByIds(String[] ids);
    
    /**
     * 通过创建时间获取订单号
     * @param map
     * @return
     * @modified:
     * ☆XiaoQuan(2020年7月20日 ): <br>
     */
    public List<String> getOrderIdsByCreateTime(Map<String, Object> map);
    
    /**
	 * 通过订单号获取token
	 * @param orderIds
	 * @return
	 * @modified: ☆XiaoQuan(2020年7月20日 ): <br>
	 */
	// public List<String> getTokensByOrderIds(String[] orderIds);

	/**
	 * 根据条件查询 出需要清理的数据
	 * @param map
	 * @return
	 * @modified: ☆XiaoQuan(2020年7月20日 ): <br>
	 */
    public List<String> getClearTokenList(Map<String, Object> map);
    
    /**
     * 查询页面录制记录列表--时间acs排序
     * @param eyeRecordPage
     * @return
     * @modified:
     * ☆XiaoQuan(2020年7月20日 ): <br>
     */
    public List<EyeRecordPage> selectClearEyeRecordPageList(EyeRecordPage eyeRecordPage);
    
    /**
	 * 查询当日订单数量(未完成)
	 * @return
	 * @modified:
	 * ☆XiaoQuan(2020年9月13日 ): <br>
	 */
	public int selectRecordNum();
}
