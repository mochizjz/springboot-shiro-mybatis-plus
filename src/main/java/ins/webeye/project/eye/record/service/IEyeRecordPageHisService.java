package ins.webeye.project.eye.record.service;

import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.record.domain.EyeRecordPageHis;

import java.util.List;

/**
 * 页面录制记录Service接口
 *
 * @author xiaoquan
 * @date 2020-07-15
 */
public interface IEyeRecordPageHisService
{
    /**
     * 新增页面录制记录
     *
     * @param eyeRecordPageHis 页面录制历史记录
     * @return 结果
     */
    public int copyToEyeRecordPageHis(EyeRecordPageHis eyeRecordPageHis);
}
