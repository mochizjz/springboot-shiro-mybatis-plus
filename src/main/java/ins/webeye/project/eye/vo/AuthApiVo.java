package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 对接第三方认证接口vo
 */
@ApiModel(description = "对接第三方认证接口的请求信息")
@Data
public class AuthApiVo {

    @NotNull(message = "消费方系统编码")
    @ApiModelProperty(value = "消费方系统代码", required = true)
    private String systemCode;

    @NotNull(message = "消费方ID不能为空")
    @ApiModelProperty(value = "消费方ID", required = true)
    private String consumerID; 

    @NotNull(message = "消费方密码不能为空")
    @ApiModelProperty(value = "消费方密码", required = true)
    private String consumerPWD; 

    @NotNull(message = "jsonData不能为空")
    @ApiModelProperty(value = "请求json", required = true)
    private Object jsonData;
    
}
