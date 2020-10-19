package ins.webeye.project.eye.pageversion.service;

import java.util.List;

import ins.webeye.project.eye.pageversion.domain.EyePageVersion;

/**
 * 页面版本记录Service接口
 * 
 * @author xiaoquan
 * @date 2020-07-23
 */
public interface IEyePageVersionService 
{
    /**
     * 查询页面版本记录
     * 
     * @param id 页面版本记录ID
     * @return 页面版本记录
     */
    public EyePageVersion selectEyePageVersionById(Long id);

    /**
     * 查询页面版本记录列表
     * 
     * @param eyePageVersion 页面版本记录
     * @return 页面版本记录集合
     */
    public List<EyePageVersion> selectEyePageVersionList(EyePageVersion eyePageVersion);

    /**
     * 新增页面版本记录
     * 
     * @param eyePageVersion 页面版本记录
     * @return 结果
     */
    public int insertEyePageVersion(EyePageVersion eyePageVersion);

    /**
     * 修改页面版本记录
     * 
     * @param eyePageVersion 页面版本记录
     * @return 结果
     */
    public int updateEyePageVersion(EyePageVersion eyePageVersion);

    /**
     * 批量删除页面版本记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEyePageVersionByIds(String ids);

    /**
     * 删除页面版本记录信息
     * 
     * @param id 页面版本记录ID
     * @return 结果
     */
    public int deleteEyePageVersionById(Long id);
    
    /**
     * 检查文件MD5是否存在
     * @param md5
     * @return
     * @modified:
     * ☆XiaoQuan(2020年7月23日 ): <br>
     */
    public EyePageVersion checkFileMd5(String md5);
}
