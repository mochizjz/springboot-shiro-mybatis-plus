package ins.webeye.project.eye.clientconfig.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 系统/渠道配置对象 eye_client_config
 * 
 * @author webeye
 * @date 2020-08-30
 */
public class EyeClientConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 系统/渠道代码 */
    @Excel(name = "系统/渠道代码")
    private String clientCode;

    /** 系统/渠道名称 */
    @Excel(name = "系统/渠道名称")
    private String clientName;

    /** 终端类型 */
    @Excel(name = "终端类型")
    private String clientType;

    /** 系统域名 */
    @Excel(name = "系统域名")
    private String clientUrl;

    /** SDK类型 */
    @Excel(name = "SDK类型")
    private String sdkType;

    /** 自动录制 */
    @Excel(name = "自动录制")
    private String autoStart;

    /** 联系人 */
    private String contactName;

    /** 联系人邮箱 */
    private String contactMail;

    /** 接口授权ID */
    private String authorizeId;

    /** 接口授权密钥 */
    private String authorizeKey;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 状态 */
    @Excel(name = "状态")
    private String httpsOnly;

    /** 创建者 */
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
    public void setClientType(String clientType)
    {
        this.clientType = clientType;
    }

    public String getClientType()
    {
        return clientType;
    }
    public void setClientUrl(String clientUrl)
    {
        this.clientUrl = clientUrl;
    }

    public String getClientUrl()
    {
        return clientUrl;
    }
    public void setSdkType(String sdkType)
    {
        this.sdkType = sdkType;
    }

    public String getSdkType()
    {
        return sdkType;
    }
    public void setAutoStart(String autoStart)
    {
        this.autoStart = autoStart;
    }

    public String getAutoStart()
    {
        return autoStart;
    }
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactName()
    {
        return contactName;
    }
    public void setContactMail(String contactMail)
    {
        this.contactMail = contactMail;
    }

    public String getContactMail()
    {
        return contactMail;
    }
    public void setAuthorizeId(String authorizeId)
    {
        this.authorizeId = authorizeId;
    }

    public String getAuthorizeId()
    {
        return authorizeId;
    }
    public void setAuthorizeKey(String authorizeKey)
    {
        this.authorizeKey = authorizeKey;
    }

    public String getAuthorizeKey()
    {
        return authorizeKey;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setHttpsOnly(String httpsOnly)
    {
        this.httpsOnly = httpsOnly;
    }

    public String getHttpsOnly()
    {
        return httpsOnly;
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
            .append("clientType", getClientType())
            .append("clientUrl", getClientUrl())
            .append("sdkType", getSdkType())
            .append("autoStart", getAutoStart())
            .append("contactName", getContactName())
            .append("contactMail", getContactMail())
            .append("authorizeId", getAuthorizeId())
            .append("authorizeKey", getAuthorizeKey())
            .append("status", getStatus())
            .append("httpsOnly", getHttpsOnly())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .toString();
    }
}
