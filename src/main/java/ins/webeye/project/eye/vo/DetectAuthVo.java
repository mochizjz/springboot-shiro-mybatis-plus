package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求实名核身鉴权响应数据")
public class DetectAuthVo {

    @ApiModelProperty("用于发起核身流程的URL")
    private String url;

    @ApiModelProperty(value = "一次核身流程的标识，有效时间为7,200秒;完成核身后，可用该标识获取验证结果信息")
    private String bizToken;

    @ApiModelProperty(value = "唯一请求 ID，每次请求都会返回.定位问题时需要提供该次请求的 RequestId")
    private String requestId;


}
