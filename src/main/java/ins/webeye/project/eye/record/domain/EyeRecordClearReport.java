package ins.webeye.project.eye.record.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 清理操作记录对象 eye_record_clear_report
 * 
 * @author xiaoquan
 * @date 2020-07-20
 */
public class EyeRecordClearReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** token */
    @Excel(name = "token")
    private String token;

    /** 订单id */
    @Excel(name = "订单id")
    private String orderId;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 操作人员id */
    @Excel(name = "操作人员id")
    private String userId;

    /** 操作的路径过程 */
    @Excel(name = "操作的路径过程")
    private String actionPath;

    /** 录制最后时间 */
    @Excel(name = "录制最后时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 更新者 */
    @Excel(name = "更新者")
    private String updateUser;

    /** 标记 */
    @Excel(name = "标记")
    private String flag;
    
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
    public void setToken(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setActionPath(String actionPath)
    {
        this.actionPath = actionPath;
    }

    public String getActionPath()
    {
        return actionPath;
    }
    public void setRecordTime(Date recordTime)
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime()
    {
        return recordTime;
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
            .append("token", getToken())
            .append("orderId", getOrderId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("userId", getUserId())
            .append("actionPath", getActionPath())
            .append("recordTime", getRecordTime())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .append("sysCode", getSysCode())
            .toString();
    }
}
