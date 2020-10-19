package ins.webeye.project.eye.order.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 订单录制记录对象 eye_order_page
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
public class EyeOrderPage extends BaseEntity
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

    /** 当前页面id */
    @Excel(name = "当前页面id")
    private String pageId;

    /** 节点id */
    @Excel(name = "节点id")
    private String nodeCode;

    /** 操作人员id */
    @Excel(name = "操作人员id")
    private String userId;

    /** 开始录制时间 */
    @Excel(name = "开始录制时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 结束录制时间 */
    @Excel(name = "结束录制时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** X秒 */
    @Excel(name = "X秒")
    private Integer recordTime;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String filePath;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String clientIp;
    
	/** 终端类型 */
	@Excel(name = "终端类型")
	private String clientType;

    /** 客户端城市编码 */
    @Excel(name = "客户端城市编码")
    private String clientCityCode;

    /** 客户端城市名称 */
    @Excel(name = "客户端城市名称")
    private String clientCityName;

    /** 客户端浏览器类型 */
    @Excel(name = "客户端浏览器类型")
    private String clientBrowser;

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
    
    /** 操作类型:录制-1，截屏-2 */
    @Excel(name = "操作类型")
    private String operType;
    
    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 投保人姓名 */
    @Excel(name = "投保人姓名")
    private String policyName;

    /** 投保人证件 */
    @Excel(name = "投保人证件")
    private String policyIdcard;
    
    /** 文件大小 */
    @Excel(name = "文件大小")
    private double fileSize;
    
    /** 当前页面url */
    @Excel(name = "当前页面url")
    private String nodeUrl;

    /** 系统编码 */
    @Excel(name = "系统编码")
    private String sysCode;

    /** 返回sdk版本 */
    @Excel(name = "返回sdk版本")
    private String sdkType;
    
	/** 播放偏移时间 */
	private Long playSetTime;

	/** 产品代码 */
	private String productCode;

	/** 产品名称 */
	private String productName;

    private List<EyeOrderPage> childEyeOrderPageList= new ArrayList<EyeOrderPage>();
    
    private Map<String,String> imgMap = new HashMap<String,String>();//id和图片map（暂时回放页面使用）
    
	/** 是否显示回放按钮 */
	private boolean showPayBtn = true;

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
    public void setPageId(String pageId)
    {
        this.pageId = pageId;
    }

    public String getPageId()
    {
        return pageId;
    }
    public void setNodeCode(String nodeCode)
    {
        this.nodeCode = nodeCode;
    }

    public String getNodeCode()
    {
        return nodeCode;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    
    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime()
    {
        return beginTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setRecordTime(Integer recordTime)
    {
        this.recordTime = recordTime;
    }

    public Integer getRecordTime()
    {
        return recordTime;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }
    public void setClientIp(String clientIp)
    {
        this.clientIp = clientIp;
    }

    public String getClientIp()
    {
        return clientIp;
    }
    public void setClientCityCode(String clientCityCode)
    {
        this.clientCityCode = clientCityCode;
    }

    public String getClientCityCode()
    {
        return clientCityCode;
    }
    public void setClientCityName(String clientCityName)
    {
        this.clientCityName = clientCityName;
    }

    public String getClientCityName()
    {
        return clientCityName;
    }
    public void setClientBrowser(String clientBrowser)
    {
        this.clientBrowser = clientBrowser;
    }

    public String getClientBrowser()
    {
        return clientBrowser;
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

    public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public List<EyeOrderPage> getChildEyeOrderPageList() {
		return childEyeOrderPageList;
	}

	public void setChildEyeOrderPageList(List<EyeOrderPage> childEyeOrderPageList) {
		this.childEyeOrderPageList = childEyeOrderPageList;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyIdcard() {
		return policyIdcard;
	}

	public void setPolicyIdcard(String policyIdcard) {
		this.policyIdcard = policyIdcard;
	}
	
	public double getFileSize() {
		return fileSize;
	}

	
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getNodeUrl() {
		return nodeUrl;
	}

	
	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

	
	public String getSysCode() {
		return sysCode;
	}

	
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	
	public String getSdkType() {
		return sdkType;
	}

	
	public void setSdkType(String sdkType) {
		this.sdkType = sdkType;
	}

	public Map<String,String> getImgMap() {
		return imgMap;
	}

	public void setImgMap(Map<String,String> imgMap) {
		this.imgMap = imgMap;
	}
	

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("token", getToken())
            .append("orderId", getOrderId())
            .append("pageId", getPageId())
            .append("nodeCode", getNodeCode())
            .append("userId", getUserId())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("recordTime", getRecordTime())
            .append("filePath", getFilePath())
			.append("clientType",getClientType())
            .append("clientIp", getClientIp())
            .append("clientCityCode", getClientCityCode())
            .append("clientCityName", getClientCityName())
            .append("clientBrowser", getClientBrowser())
            .append("status", getStatus())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .append("operType",getOperType())
            .append("policyNo", getPolicyNo())
            .append("policyName", getPolicyName())
            .append("policyIdcard", getPolicyIdcard())
            .append("fileSize", getFileSize())
            .append("nodeUrl", getNodeUrl())
            .append("sysCode", getSysCode())
            .append("sdkType", getSdkType())
            .toString();
    }

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getPlaySetTime() {
		return playSetTime;
	}

	public void setPlaySetTime(Long playSetTime) {
		this.playSetTime = playSetTime;
	}

	public boolean isShowPayBtn() {
		return showPayBtn;
	}

	public void setShowPayBtn(boolean showPayBtn) {
		this.showPayBtn = showPayBtn;
	}
}
