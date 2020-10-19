package ins.webeye.project.eye.checklog.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 质检轨迹对象 eye_check_log
 * 
 * @author wangtao
 * @date 2020-08-20
 */
public class EyeCheckLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 订单表主键 */
    @Excel(name = "订单表主键")
    private Long eyeOrderId;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderId;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 质检结果，0-质检通过，1-质检未通过 */
    @Excel(name = "质检结果，0-质检通过，1-质检未通过")
    private String checkStatus;

    /** 质检结果描述 */
    @Excel(name = "质检结果描述")
    private String checkMessage;

    /** 质检时间 */
    @Excel(name = "质检时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 状态（0正常） */
    @Excel(name = "状态", readConverterExp = "0=正常")
    private String status;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createUser;

    /** 更新者 */
    @Excel(name = "更新者")
    private String updateUser;
    
    /** 系统编码 */
    @Excel(name = "系统编码")
    private String sysCode;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setEyeOrderId(Long eyeOrderId)
    {
        this.eyeOrderId = eyeOrderId;
    }

    public Long getEyeOrderId()
    {
        return eyeOrderId;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setPolicyNo(String policyNo)
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo()
    {
        return policyNo;
    }
    public void setCheckStatus(String checkStatus)
    {
        this.checkStatus = checkStatus;
    }

    public String getCheckStatus()
    {
        return checkStatus;
    }
    public void setCheckMessage(String checkMessage)
    {
        this.checkMessage = checkMessage;
    }

    public String getCheckMessage()
    {
        return checkMessage;
    }
    public void setCheckTime(Date checkTime)
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime()
    {
        return checkTime;
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
    public String getSysCode() {
		return sysCode;
	}

	
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("eyeOrderId", getEyeOrderId())
            .append("orderId", getOrderId())
            .append("policyNo", getPolicyNo())
            .append("checkStatus", getCheckStatus())
            .append("checkMessage", getCheckMessage())
            .append("checkTime", getCheckTime())
            .append("status", getStatus())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("sysCode", getSysCode())
            .toString();
    }
}
