package ins.webeye.project.eye.ordercheck.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 回溯对账查询对象 eye_order_check
 * 
 * @author webeye
 * @date 2020-09-08
 */
public class EyeOrderCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 系统/渠道 */
    @Excel(name = "系统/渠道")
    private String sysCode;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderId;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String productCode;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 投保人姓名 */
    @Excel(name = "投保人姓名")
    private String policyName;

    /** 投保人证件 */
    @Excel(name = "投保人证件")
    private String policyIdcard;

    /** 录制凭证token */
    @Excel(name = "录制凭证token")
    private String token;

	/** 支付状态 */
	@Excel(name = "支付状态")
	private String payStatus;

    /** 对账结果 */
    @Excel(name = "对账结果")
    private String checkStatus;

    /** 对账结果描述 */
    @Excel(name = "对账结果描述")
    private String checkMessage;

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
    public void setSysCode(String sysCode)
    {
        this.sysCode = sysCode;
    }

    public String getSysCode()
    {
        return sysCode;
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
    public void setPolicyNo(String policyNo)
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo()
    {
        return policyNo;
    }
    public void setPolicyName(String policyName)
    {
        this.policyName = policyName;
    }

    public String getPolicyName()
    {
        return policyName;
    }
    public void setPolicyIdcard(String policyIdcard)
    {
        this.policyIdcard = policyIdcard;
    }

    public String getPolicyIdcard()
    {
        return policyIdcard;
    }
    public void setToken(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
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
            .append("sysCode", getSysCode())
            .append("orderId", getOrderId())
            .append("productCode", getProductCode())
            .append("policyNo", getPolicyNo())
            .append("policyName", getPolicyName())
            .append("policyIdcard", getPolicyIdcard())
            .append("token", getToken())
            .append("checkStatus", getCheckStatus())
            .append("checkMessage", getCheckMessage())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .toString();
    }

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
}
