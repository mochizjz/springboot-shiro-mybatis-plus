package ins.webeye.project.eye.pageversion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.pageversion.domain.EyePageVersion;
import ins.webeye.project.eye.pageversion.mapper.EyePageVersionMapper;
import ins.webeye.project.eye.pageversion.service.IEyePageVersionService;

/**
 * 页面版本记录Service业务层处理
 * 
 * @author xiaoquan
 * @date 2020-07-23
 */
@Service
public class EyePageVersionServiceImpl implements IEyePageVersionService 
{
    @Autowired
    private EyePageVersionMapper eyePageVersionMapper;

    /**
     * 查询页面版本记录
     * 
     * @param id 页面版本记录ID
     * @return 页面版本记录
     */
    @Override
    public EyePageVersion selectEyePageVersionById(Long id)
    {
        return eyePageVersionMapper.selectEyePageVersionById(id);
    }

    /**
     * 查询页面版本记录列表
     * 
     * @param eyePageVersion 页面版本记录
     * @return 页面版本记录
     */
    @Override
    public List<EyePageVersion> selectEyePageVersionList(EyePageVersion eyePageVersion)
    {
        return eyePageVersionMapper.selectEyePageVersionList(eyePageVersion);
    }

    /**
     * 新增页面版本记录
     * 
     * @param eyePageVersion 页面版本记录
     * @return 结果
     */
    @Override
    public int insertEyePageVersion(EyePageVersion eyePageVersion)
    {
        eyePageVersion.setCreateTime(DateUtils.getNowDate());
        return eyePageVersionMapper.insertEyePageVersion(eyePageVersion);
    }

    /**
     * 修改页面版本记录
     * 
     * @param eyePageVersion 页面版本记录
     * @return 结果
     */
    @Override
    public int updateEyePageVersion(EyePageVersion eyePageVersion)
    {
        eyePageVersion.setUpdateTime(DateUtils.getNowDate());
        return eyePageVersionMapper.updateEyePageVersion(eyePageVersion);
    }

    /**
     * 删除页面版本记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyePageVersionByIds(String ids)
    {
        return eyePageVersionMapper.deleteEyePageVersionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除页面版本记录信息
     * 
     * @param id 页面版本记录ID
     * @return 结果
     */
    @Override
    public int deleteEyePageVersionById(Long id)
    {
        return eyePageVersionMapper.deleteEyePageVersionById(id);
    }
    
    @Override
    public EyePageVersion checkFileMd5(String md5){
    	EyePageVersion eyePageVersion = new EyePageVersion();
    	eyePageVersion.setFileMd5(md5);
    	List<EyePageVersion> list = eyePageVersionMapper.selectEyePageVersionList(eyePageVersion);
    	if(list!=null && list.size()>0){
    		return list.get(0);
    	}else{
    		return null;
    	}
	}
}
