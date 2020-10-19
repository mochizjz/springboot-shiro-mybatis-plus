package ins.webeye.project.eye.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ins.webeye.common.utils.TencentSaasUtil;
import ins.webeye.project.eye.api.service.IEyeSaasApiService;
import ins.webeye.project.eye.vo.DetectAuthVo;
import ins.webeye.project.eye.vo.SaasVo;
import ins.webeye.project.eye.vo.TecentAuthVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EyeSaasApiServiceImpl implements IEyeSaasApiService {

    @Autowired
    TencentSaasUtil tencentSaasUtil;

    @Override
    public DetectAuthVo getDetectAuth(SaasVo saasVo) throws Exception {

        String detectStr = tencentSaasUtil.getDetectAuth(saasVo.getSecretId(),saasVo.getSecretKey(),saasVo.getEndpoint(),
                saasVo.getRegion(),saasVo.getRuleId(),saasVo.getRedirectUrl());

        DetectAuthVo info = JSONObject.parseObject(detectStr,DetectAuthVo.class);
        return info;
    }

    @Override
    public TecentAuthVo getDetectInfo(SaasVo saasVo) throws Exception {

        String detectInfo = tencentSaasUtil.getDetectInfo(saasVo.getSecretId(),saasVo.getSecretKey(),
                saasVo.getEndpoint(),saasVo.getBizToken(),saasVo.getRuleId());

        JSONObject obj = JSON.parseObject(detectInfo);
        String str = obj.get("DetectInfo").toString();
        TecentAuthVo info = JSONObject.toJavaObject(JSON.parseObject(str),TecentAuthVo.class);

        return info;
    }
}
