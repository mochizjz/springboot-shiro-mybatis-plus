package ins.webeye.project.eye.faceapilog.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 实名验证接口日志对象 eye_faceapi_log
 * 
 * @author XiaoQuan
 * @date 2020-08-16
 */
public class EyeFaceapiLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 调用方代码 */
    @Excel(name = "调用方代码")
    private String clientCode;

    /** 调用方名称 */
    @Excel(name = "调用方名称")
    private String clientName;

    /** 接口类型 */
    @Excel(name = "接口类型")
    private String faceType;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String userIdcard;

    /** 识别结果 */
    @Excel(name = "识别结果")
    private String recognitionData;

    /** 识别日期 */
    @Excel(name = "识别日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recognitionDate;

    /** 人脸图片 */
    @Excel(name = "人脸图片")
    private String facePicture;

    /** 状态 */
    private String status;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createUser;

    /** 更新者 */
    private String updateUser;

    /** 标记 */
    private String flag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setClientCode(String clientCode)
    {
        this.clientCode = clientCode;
    }

    public String getClientCode()
    {
        return clientCode;
    }
    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getClientName()
    {
        return clientName;
    }
    public void setFaceType(String faceType)
    {
        this.faceType = faceType;
    }

    public String getFaceType()
    {
        return faceType;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setUserIdcard(String userIdcard)
    {
        this.userIdcard = userIdcard;
    }

    public String getUserIdcard()
    {
        return userIdcard;
    }
    public void setRecognitionData(String recognitionData)
    {
        this.recognitionData = recognitionData;
    }

    public String getRecognitionData()
    {
        return recognitionData;
    }
    public void setRecognitionDate(Date recognitionDate)
    {
        this.recognitionDate = recognitionDate;
    }

    public Date getRecognitionDate()
    {
        return recognitionDate;
    }
    public void setFacePicture(String facePicture)
    {
        this.facePicture = facePicture;
    }

    public String getFacePicture()
    {
        return facePicture;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getCreateUser()
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getFlag()
    {
        return flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("clientCode", getClientCode())
            .append("clientName", getClientName())
            .append("faceType", getFaceType())
            .append("userName", getUserName())
            .append("userIdcard", getUserIdcard())
            .append("recognitionData", getRecognitionData())
            .append("recognitionDate", getRecognitionDate())
            .append("facePicture", getFacePicture())
            .append("status", getStatus())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .toString();
    }
}
