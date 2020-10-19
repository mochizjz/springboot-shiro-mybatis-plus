package ins.webeye.project.eye.record.mapper;

import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.record.domain.EyeRecordPageHis;

import java.util.List;
import java.util.Map;

/**
 * 页面录制记录Mapper接口
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
public interface EyeRecordPageHisMapper
{
    /**
     * 新增页面录制记录
     * 
     * @param eyeRecordPageHis 页面录制记录
     * @return 结果
     */
    public int insertEyeRecordPageHis(EyeRecordPageHis eyeRecordPageHis);


}
