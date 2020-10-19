package ins.webeye.project.eye.pageversion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 页面版本记录对象 eye_page_version
 * 
 * @author xiaoquan
 * @date 2020-07-23
 */
public class EyePageVersion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 节点id */
    @Excel(name = "节点id")
    private String nodeCode;

    /** 当前页面id */
    @Excel(name = "当前页面id")
    private String pageId;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件MD5 */
    @Excel(name = "文件MD5")
    private String fileMd5;

    /** 文件类型:录制文件:recordFile;抓取文件:urlImage;当前版本文件:orgHtml */
    @Excel(name = "文件类型:录制文件:recordFile;抓取图片:urlFile;当前版本文件:orgHtml")
    private String fileType;
    
    private String nodeUrl;

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
    public void setNodeCode(String nodeCode)
    {
        this.nodeCode = nodeCode;
    }

    public String getNodeCode()
    {
        return nodeCode;
    }
    public void setPageId(String pageId)
    {
        this.pageId = pageId;
    }

    public String getPageId()
    {
        return pageId;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }
    public void setFileMd5(String fileMd5)
    {
        this.fileMd5 = fileMd5;
    }

    public String getFileMd5()
    {
        return fileMd5;
    }
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getFileType()
    {
        return fileType;
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
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("nodeCode", getNodeCode())
            .append("pageId", getPageId())
            .append("filePath", getFilePath())
            .append("fileMd5", getFileMd5())
            .append("fileType", getFileType())
            .append("nodeUrl", getNodeUrl())
            .append("status", getStatus())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .append("sysCode", getSysCode())
            .toString();
    }

	
	public String getNodeUrl() {
		return nodeUrl;
	}

	
	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}
}
