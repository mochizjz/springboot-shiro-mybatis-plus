package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 阿里身份证识别请求vo
 */
@ApiModel(description = "阿里身份证识别请求vo")
@Data
public class OcrVo {

    @ApiModelProperty(value = "appcode", required = true)
    private String appcode;

    @ApiModelProperty(value = "图片类型", required = true)
    private String imageType;//imgUrl   imgBase64

    @ApiModelProperty(value = "图片url", required = true)
    private String imageURL;

    @ApiModelProperty(value = "身份证正反面类型 face：正面 back：反面", required = true)
    private String side;


}
