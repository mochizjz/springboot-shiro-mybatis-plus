package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "实名核身结果响应数据")
public class TecentAuthVo {

    @ApiModelProperty(value = "文本类信息")
    private TecentTextInfoVo text;

    @ApiModelProperty(value = "身份证正反面照片")
    private IdCardVo idCardData;

    @ApiModelProperty(value = "视频最佳帧截图")
    private BestFrameVo bestFrame;



}
