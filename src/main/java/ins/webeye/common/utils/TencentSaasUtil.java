package ins.webeye.common.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.DetectAuthRequest;
import com.tencentcloudapi.faceid.v20180301.models.DetectAuthResponse;
import com.tencentcloudapi.faceid.v20180301.models.GetDetectInfoRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetDetectInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯云活体认证工具类
 * created by wangtao
 * 2020-08-13
 */
@Slf4j
@Component
public class TencentSaasUtil {

    /**
     * 实名核身鉴权
     * @param secretId      腾讯云账户 Id
     * @param secretKey     腾讯云账户 secretKey
     * @param endpoint      仅在验证签名串生成时有效
     * @param region        Region  地域参数
     * @param ruleId        用于细分客户使用场景，申请开通服务后，可以在腾讯云慧眼人脸核身控制台
     *                      （https://console.cloud.tencent.com/faceid） 自助接入里面创建，审核通过后即可调用
     * @param redirectUrl   回调地址
     * @return
     */
    public String getDetectAuth(String secretId,String secretKey,String endpoint,String region,String ruleId,String redirectUrl) throws Exception {
        Map<String,String> returnMap = new HashMap<String,String>(2);
        String authInfo = null;
        try{
            Credential cred = new Credential(secretId, secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint(endpoint);

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, region, clientProfile);


            String params = "{\"RedirectUrl\":\""+redirectUrl+"\",\"RuleId\":\""+ruleId+"\"}";
            DetectAuthRequest req = DetectAuthRequest.fromJsonString(params, DetectAuthRequest.class);

            DetectAuthResponse resp = client.DetectAuth(req);
            // 输出 JSON 格式的字符串回包
            authInfo = DetectAuthRequest.toJsonString(resp);


        } catch (Exception e) {
            log.error("活体认证获取token失败:{}",e.getMessage());
            throw new Exception("活体认证获取token失败:"+e.getMessage());
        }
        return authInfo;
    }

    /**
     * 获取实名核身结果信息
     * @param secretId      腾讯云账户 Id
     * @param secretKey     腾讯云账户 secretKey
     * @param endpoint      仅在验证签名串生成时有效
     * @param bizToken      人脸核身流程的标识，调用DetectAuth接口时生成。
     * @param ruleId        用于细分客户使用场景
     * @return              输出参数参考 https://cloud.tencent.com/document/api/1007/33560
     */
    public String getDetectInfo(String secretId,String secretKey,String endpoint,String bizToken,String ruleId) throws Exception {
        Map<String,String> returnMap = new HashMap<String,String>(2);

        String detectInfo = null;
        try{
            Credential cred = new Credential(secretId, secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint(endpoint);

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, endpoint, clientProfile);

            String params = "{\"BizToken\":\""+bizToken+"\",\"RuleId\":\""+ruleId+"\"}";

            GetDetectInfoRequest req = GetDetectInfoRequest.fromJsonString(params, GetDetectInfoRequest.class);

            GetDetectInfoResponse resp = client.GetDetectInfo(req);
            //输出 JSON 格式的字符串回包
            detectInfo = GetDetectInfoRequest.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            log.error("获取实名核身结果信息失败"+e.getMessage());
            throw new Exception("获取实名核身结果信息失败"+e.getMessage());
        }
        return detectInfo;
    }


}
