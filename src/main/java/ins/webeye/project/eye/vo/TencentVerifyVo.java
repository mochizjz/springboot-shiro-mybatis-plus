package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 腾讯身份验证请求vo
 */
@ApiModel(description = "腾讯云身份验证接口的请求信息")
@Data
public class TencentVerifyVo {

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
     * idCard  身份证参数
     */
    @NotNull(message = "idCard不能为空")
    @ApiModelProperty(value = "身份证号", required = true)
    private  String idCard;

    /**
     * name  姓名参数
     */
    @NotNull(message = "name不能为空")
    @ApiModelProperty(value = "姓名", required = true)
    private  String name;

}
