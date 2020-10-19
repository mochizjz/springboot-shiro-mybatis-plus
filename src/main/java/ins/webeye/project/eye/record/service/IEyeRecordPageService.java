package ins.webeye.project.eye.record.service;

import java.util.List;

import ins.webeye.project.eye.record.domain.EyeRecordPage;

/**
 * 页面录制记录Service接口
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
public interface IEyeRecordPageService 
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
	 * 根据token与产品编码批量更新
	 * @param eyeRecordPage
	 * @return
	 * @modified:
	 * ☆XiaoQuan(2020年7月28日 ): <br>
	 */
    public int updateEyeRecordPageByToken(EyeRecordPage eyeRecordPage);
    
    /**
     * 批量删除页面录制记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyeRecordPageByIds(String ids);

    /**
     * 删除页面录制记录信息
     * 
     * @param id 页面录制记录ID
     * @return 结果
     */
    public int deleteEyeRecordPageById(Long id);
    
   /**
    * 批量清理录制信息：清理 clearAll以前的所有数据；清理clearNoOrder~clearAll的没有订单的数据
    * @param clearAll 清理所有的  ：7天前，则设置7
    * @param clearNoOrder 清理除去订单的 :1天前 ，则设置1
    * @modified:
    * ☆XiaoQuan(2020年7月20日 ): <br>
    */
    public void clearRecordPages(Integer clearAll,Integer clearNoOrder);

    
}
