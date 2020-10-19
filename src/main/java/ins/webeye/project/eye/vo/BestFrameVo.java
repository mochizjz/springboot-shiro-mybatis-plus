package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BestFrameVo {

    @ApiModelProperty(value = "视频最佳帧截图Base64")
    private String bestFrame;


}
