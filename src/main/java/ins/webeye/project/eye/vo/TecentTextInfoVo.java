package ins.webeye.project.eye.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TecentTextInfoVo {

    @ApiModelProperty(value = "本次核身最终结果。0为成功")
    private String errCode;

    @ApiModelProperty(value = "本次核身最终结果信息描述")
    private String errMsg;

    @ApiModelProperty(value = "本次核身最终获得的身份证号")
    private String idCard;

    @ApiModelProperty(value = "本次核身最终获得的姓名")
    private String name;

    @ApiModelProperty(value = "ocr阶段获取的民族")
    private String ocrNation;

    @ApiModelProperty(value = "ocr阶段获取的地址")
    private String ocrAddress;

    @ApiModelProperty(value = "ocr阶段获取的出生信息")
    private String ocrBirth;

    @ApiModelProperty(value = "ocr阶段获取的证件签发机关")
    private String ocrAuthority;

    @ApiModelProperty(value = "ocr阶段获取的证件有效期")
    private String ocrValidDate;

    @ApiModelProperty(value = "ocr阶段获取的姓名")
    private String ocrName;

    @ApiModelProperty(value = "ocr阶段获取的身份证号")
    private String ocrIdCard;

    @ApiModelProperty(value = "ocr阶段获取的性别")
    private String ocrGender;

    @ApiModelProperty(value = "活体检测阶段的错误码。0为成功")
    private String liveStatus;

    @ApiModelProperty(value = "活体检测阶段的错误信息")
    private String liveMsg;

    @ApiModelProperty(value = "一比一阶段的错误码。0为成功")
    private String comparestatus;

    @ApiModelProperty(value = "一比一阶段的错误信息")
    private String comparemsg;

    @ApiModelProperty(value = "地理位置信息")
    private String location;

    @ApiModelProperty(value = "DetectAuth结果传进来的Extra信息")
    private String extra;



}
