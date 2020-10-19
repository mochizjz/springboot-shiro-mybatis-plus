package ins.webeye.project.eye.order.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 订单记录对象 eye_order
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
@TableName("eye_order")
public class EyeOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @TableId(value = "id")
    private Long id;

    /** token */
    @Excel(name = "token")
    private String token;

	/** 对接方系统编码 */
	@Excel(name = "对接方系统编码")
	private String sysCode;

    /** 订单id */
    @Excel(name = "订单id")
    private String orderId;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 投保人姓名 */
    @Excel(name = "投保人姓名")
    private String policyName;

    /** 投保人证件 */
    @Excel(name = "投保人证件")
    private String policyIdcard;

    /** 状态（0正常） */
    @Excel(name = "状态", readConverterExp = "0=正常")
    private String status;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createUser;

    /** 更新者 */
    @Excel(name = "更新者")
    private String updateUser;

    /** 标记 */
    @Excel(name = "标记")
    private String flag;
    
    /** 质检结果 */
    @Excel(name = "质检结果")
    private String checkStatus;

    /** 质检结果描述 */
    @Excel(name = "质检结果描述")
    private String checkMessage;

    /** 检查时间 */
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 回溯验真码 */
    @Excel(name = "回溯验真码")
    private String verifyCode;

    /** 验真检查时间 */
    @Excel(name = "验真检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date verifyTime;

    /** 第三方验真 */
    @Excel(name = "第三方验真")
    private String verifyThirdParty;
    
    /** 文件大小 */
    @Excel(name = "文件大小")
    private double fileSize;

    /** 涉及诉讼 */
    @Excel(name = "涉及诉讼")
    private String isLitigations;

    /** 归档状态 */
    @Excel(name = "归档状态")
    private String archStatus;

    /** 归档时间 */
    @Excel(name = "归档时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date archTime;

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
    public void setVerifyCode(String verifyCode)
    {
        this.verifyCode = verifyCode;
    }

    public String getVerifyCode()
    {
        return verifyCode;
    }
    public void setVerifyTime(Date verifyTime)
    {
        this.verifyTime = verifyTime;
    }

    public Date getVerifyTime()
    {
        return verifyTime;
    }
    public void setVerifyThirdParty(String verifyThirdParty)
    {
        this.verifyThirdParty = verifyThirdParty;
    }

    public String getVerifyThirdParty()
    {
        return verifyThirdParty;
    }

    
	public double getFileSize() {
		return fileSize;
	}

	
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	
	public String getIsLitigations() {
		return isLitigations;
	}

	
	public void setIsLitigations(String isLitigations) {
		this.isLitigations = isLitigations;
	}

	
	public String getArchStatus() {
		return archStatus;
	}

	
	public void setArchStatus(String archStatus) {
		this.archStatus = archStatus;
	}

	
	public Date getArchTime() {
		return archTime;
	}

	
	public void setArchTime(Date archTime) {
		this.archTime = archTime;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("token", getToken())
				.append("sysCode",getSysCode())
            .append("orderId", getOrderId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("policyNo", getPolicyNo())
            .append("policyName", getPolicyName())
            .append("policyIdcard", getPolicyIdcard())
            .append("status", getStatus())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .append("checkStatus", getCheckStatus())
            .append("checkMessage", getCheckMessage())
            .append("checkTime", getCheckTime())
            .append("verifyCode", getVerifyCode())
            .append("verifyTime", getVerifyTime())
            .append("verifyThirdParty", getVerifyThirdParty())
            .append("fileSize", getFileSize())
            .append("isLitigations", getIsLitigations())
            .append("archStatus", getArchStatus())
            .append("archTime", getArchTime())
            .toString();
    }

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
}
