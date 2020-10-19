package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QualityCheckVo {
    @ApiModelProperty(value = "订单号", required = true)
    private String orderId;

    @ApiModelProperty(value = "质检结果", required = true)
    private String checkStatus;

    @ApiModelProperty(value = "质检信息", required = true)
    private String checkMessage;
}
