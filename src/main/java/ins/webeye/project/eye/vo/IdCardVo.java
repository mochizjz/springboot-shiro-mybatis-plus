package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 身份证信息
 */
@Data
public class IdCardVo {

    @ApiModelProperty(value = "身份证正面")
    private String ocrFront;

    @ApiModelProperty(value = "身份证反面")
    private String ocrBack;


}
