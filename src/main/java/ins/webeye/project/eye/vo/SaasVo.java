package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 腾讯云活体认证请求vo
 */
@ApiModel(description = "腾讯云活体认证接口的请求信息")
@Data
public class SaasVo {

    /**
     * 腾讯云账户 secretId
     */
    @NotNull(message = "secretId不能为空")
    @ApiModelProperty(value = "腾讯云账户 secretId", required = true)
    private String secretId;

    /**
     * 腾讯云账户 secretKey
     */
    @NotNull(message = "secretKey不能为空")
    @ApiModelProperty(value = "腾讯云账户 secretKey", required = true)
    private String secretKey;

    /**
     * 仅在验证签名串生成时有效
     */
    @NotNull(message = "endpoint不能为空")
    @ApiModelProperty(value = "仅在验证签名串生成时有效", required = true)
    private String endpoint;

    /**
     * Region  地域参数
     */
    @NotNull(message = "region不能为空")
    @ApiModelProperty(value = "地域参数", required = true)
    private  String region;

    /**
     * ruleId  用于细分客户使用场景，申请开通服务后，可以在腾讯云慧眼人脸核身控制台
     *         （https://console.cloud.tencent.com/faceid） 自助接入里面创建，审核通过后即可调用）
     */
    @NotNull(message = "用于细分客户使用场景")
    @ApiModelProperty(value = "ruleId", required = true)
    private  String ruleId;

    /**
     * redirectUrl  腾讯云回调业务系统地址
     *  请求DetectAuth接口时不能为空
     */
    @ApiModelProperty(value = "腾讯云回调业务系统地址", required = false)
    private  String redirectUrl;

    /**
     * bizToken  人脸核身流程的标识，调用DetectAuth接口时生成。
     *  请求GetDetectInfo接口时不能为空
     */
    @ApiModelProperty(value = "人脸核身流程的标识，调用getDetectAuth接口时生成", required = false)
    private  String bizToken;



}
