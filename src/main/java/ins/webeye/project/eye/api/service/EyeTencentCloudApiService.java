/******************************************************************************
* CREATETIME : 2020年8月13日 下午4:57:12
******************************************************************************/
package ins.webeye.project.eye.api.service;

import java.util.HashMap;
import java.util.Map;

import ins.webeye.project.eye.vo.SaasVo;
import ins.webeye.project.eye.vo.TecentAuthVo;
import ins.webeye.project.eye.vo.TencentCloudApiVo;
import ins.webeye.project.eye.vo.TencentVerifyVo;
import ins.webeye.project.system.config.service.IConfigService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.DetectAuthRequest;
import com.tencentcloudapi.faceid.v20180301.models.DetectAuthResponse;
import com.tencentcloudapi.faceid.v20180301.models.GetActionSequenceRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetActionSequenceResponse;
import com.tencentcloudapi.faceid.v20180301.models.GetDetectInfoRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetDetectInfoResponse;
import com.tencentcloudapi.faceid.v20180301.models.IdCardVerificationRequest;
import com.tencentcloudapi.faceid.v20180301.models.IdCardVerificationResponse;
import com.tencentcloudapi.faceid.v20180301.models.LivenessRecognitionRequest;
import com.tencentcloudapi.faceid.v20180301.models.LivenessRecognitionResponse;



/**
 * <pre></pre>
 * @author ★XiaoQuan
 * @CreateTime 2020年8月13日
 */
@Service
public class EyeTencentCloudApiService {
    
	@Autowired
    private IConfigService configService;

	
    private static String secretId;				//AK
    private static String secretKey;			//SK
    private static String region;				//区域
    private static FaceidClient faceidClient;	//人脸核验客户端
    private static String ruleId;				//用于细分客户使用场景，申请开通服务后，可以在腾讯云慧眼人脸核身控制台（https://console.cloud.tencent.com/faceid） 自助接入里面创建，审核通过后即可调用。如有疑问，请加慧眼小助手微信（faceid001）进行咨询。
    
    /**
     * 初始化人脸核验客户端
     * @modified:
     * ☆XiaoQuan(2020年8月13日 ): <br>
     */
    private void initFaceidClient(){
    	if(StringUtils.isBlank(secretId))secretId = configService.selectConfigByKey("tencent.cloud.secretId");
    	if(StringUtils.isBlank(secretKey))secretKey = configService.selectConfigByKey("tencent.cloud.secretKey");
    	if(StringUtils.isBlank(region))region = configService.selectConfigByKey("tencent.cloud.region");
    	if(StringUtils.isBlank(ruleId))ruleId = configService.selectConfigByKey("tencent.cloud.ruleId");
    	if(faceidClient == null){
    		Credential cred = new Credential(secretId, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid." + region + ".tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            faceidClient = new FaceidClient(cred, region, clientProfile);
    	}
    }
    
    /**
     * 腾讯人脸核验-获取动作顺序
     * @return
     * @throws TencentCloudSDKException 
     * @modified:
     * ☆XiaoQuan(2020年8月13日 ): <br>
     */
    public String getActionSequence() throws TencentCloudSDKException{
    	initFaceidClient();
    	String params = "{}";
        GetActionSequenceRequest req = GetActionSequenceRequest.fromJsonString(params, GetActionSequenceRequest.class);
        GetActionSequenceResponse resp = faceidClient.GetActionSequence(req);
    	return GetActionSequenceResponse.toJsonString(resp);
    }

    /**
     * 腾讯-人脸核验
     * @param jsonData
     * @return
     * @throws TencentCloudSDKException
     * @modified:
     * ☆XiaoQuan(2020年8月13日 ): <br>
     */
    public String livenessRecognition(String jsonData) throws TencentCloudSDKException{
    	initFaceidClient();
    	/* 参数拼装示例
    	 * String params = "{"
        		+ "\"IdCard\":\"333444555566667777\","
        		+ "\"Name\":\"小米\","
        		+ "\"VideoBase64\":\"BASE64\","
        		+ "\"LivenessType\":\"ACTION\""
        		+ "}";*/
		LivenessRecognitionRequest req = LivenessRecognitionRequest.fromJsonString(jsonData, LivenessRecognitionRequest.class);
	    LivenessRecognitionResponse resp = faceidClient.LivenessRecognition(req);
	    return LivenessRecognitionResponse.toJsonString(resp);
    }
    
    
    /**
     * 获取实名核身结果信息
     * @param secretId      腾讯云账户 Id
     * @param secretKey     腾讯云账户 secretKey
     * @param endpoint      仅在验证签名串生成时有效
     * @param bizToken      人脸核身流程的标识，调用DetectAuth接口时生成。
     * @param ruleId        用于细分客户使用场景
     * @return              输出参数参考 https://cloud.tencent.com/document/api/1007/33560
     * @throws TencentCloudSDKException 
     */
    public String getDetectInfo(TencentCloudApiVo tencentCloudApiVo) throws TencentCloudSDKException {
		initFaceidClient();
        JSONObject jsonObject = JSONObject.parseObject(tencentCloudApiVo.getJsonData());
        jsonObject.put("RuleId",ruleId);
        GetDetectInfoRequest req = GetDetectInfoRequest.fromJsonString(jsonObject.toString(), GetDetectInfoRequest.class);
        GetDetectInfoResponse resp = faceidClient.GetDetectInfo(req);
        return GetDetectInfoRequest.toJsonString(resp);
    }
    
    
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
     * @throws TencentCloudSDKException 
     */
    public String getDetectAuth(TencentCloudApiVo tencentCloudApiVo) throws TencentCloudSDKException{
		initFaceidClient();
        JSONObject jsonObject = JSONObject.parseObject(tencentCloudApiVo.getJsonData());
		jsonObject.put("RuleId",ruleId);
        DetectAuthRequest req = DetectAuthRequest.fromJsonString(jsonObject.toString(), DetectAuthRequest.class);
        DetectAuthResponse resp = faceidClient.DetectAuth(req);
        return DetectAuthRequest.toJsonString(resp);
    }
    
    /**
     * 实名认证接口
     * @param verifyVo
     * @return
     * @throws Exception
     * @modified:
     * ☆XiaoQuan(2020年8月19日 ): <br>
     */
    public String idCardVerification(TencentVerifyVo verifyVo) throws Exception {
    	 //ak/sk从接口传过来有点奇怪
    	 Credential cred = new Credential(verifyVo.getSecretId(), verifyVo.getSecretKey());
    	 HttpProfile httpProfile = new HttpProfile();
         httpProfile.setEndpoint(verifyVo.getEndpoint());
         ClientProfile clientProfile = new ClientProfile();
         clientProfile.setHttpProfile(httpProfile);
         FaceidClient client = new FaceidClient(cred, verifyVo.getRegion(), clientProfile);
         String params = "{\"IdCard\":\""+verifyVo.getIdCard()+"\",\"Name\":\""+verifyVo.getName()+"\"}";
         IdCardVerificationRequest req = IdCardVerificationRequest.fromJsonString(params, IdCardVerificationRequest.class);
         IdCardVerificationResponse resp = client.IdCardVerification(req);
         return IdCardVerificationResponse.toJsonString(resp);
    }


}
