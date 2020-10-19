package ins.webeye.project.eye.api.service;

import ins.webeye.project.eye.vo.DetectAuthVo;
import ins.webeye.project.eye.vo.SaasVo;
import ins.webeye.project.eye.vo.TecentAuthVo;

/**
 * 腾讯生物实名认证接口
 */
public interface IEyeSaasApiService {

    /**
     * 实名核身鉴权
     * @return  人脸核身流程的标识;输出参数参考 https://cloud.tencent.com/document/api/1007/31816
     */
    public DetectAuthVo getDetectAuth(SaasVo saasVo) throws Exception;

    /**
     * 获取实名核身结果信息
     * @return    输出参数参考 https://cloud.tencent.com/document/api/1007/33560
     */
    public TecentAuthVo getDetectInfo(SaasVo saasVo) throws Exception;

}
