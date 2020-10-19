package ins.webeye.project.eye.record.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.utils.DateUtils;
import ins.webeye.project.eye.record.domain.EyeRecordPageHis;
import ins.webeye.project.eye.record.mapper.EyeRecordPageHisMapper;
import ins.webeye.project.eye.record.service.IEyeRecordPageHisService;
import lombok.extern.slf4j.Slf4j;

/**
 * 页面录制记录Service业务层处理
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
@Service
@Slf4j
public class EyeRecordPageHisServiceImpl implements IEyeRecordPageHisService
{
    @Autowired
    private EyeRecordPageHisMapper eyeRecordPageHisMapper;


    /**
     * 新增页面录制记录
     * 
     * @param eyeRecordPageHis 页面录制历史记录
     * @return 结果
     */
    @Override
    public int copyToEyeRecordPageHis(EyeRecordPageHis eyeRecordPageHis)
    {
        eyeRecordPageHis.setCreateTime(DateUtils.getNowDate());
        //eyeRecordPage.setCreateUser(ShiroUtils.getLoginName());
        return eyeRecordPageHisMapper.insertEyeRecordPageHis(eyeRecordPageHis);
    }

    
}
