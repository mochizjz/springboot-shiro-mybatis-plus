package ins.webeye.project.eye.pageconfig.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 页面管理对象 eye_page_config
 * 
 * @author xiaoquan
 * @date 2020-08-02
 */
public class EyePageConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 系统代码 */
    @Excel(name = "系统代码")
    private String sysCode;

    /** 页面url */
    @Excel(name = "页面url")
    private String pageUrl;

    /** 页面名称 */
    @Excel(name = "页面名称")
    private String pageName;

    /** 节点代码 */
    @Excel(name = "节点代码")
    private String nodeCode;

    /** 排除计算内容 */
    @Excel(name = "排除计算内容")
    private String excludeContent;

    /** 附加js方法 */
    @Excel(name = "附加js方法")
    private String addJsFunction;

    /** 状态 */
    @Excel(name = "状态")
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
    public void setPageUrl(String pageUrl)
    {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl()
    {
        return pageUrl;
    }
    public void setPageName(String pageName)
    {
        this.pageName = pageName;
    }

    public String getPageName()
    {
        return pageName;
    }
    public void setNodeCode(String nodeCode)
    {
        this.nodeCode = nodeCode;
    }

    public String getNodeCode()
    {
        return nodeCode;
    }
    public void setExcludeContent(String excludeContent)
    {
        this.excludeContent = excludeContent;
    }

    public String getExcludeContent()
    {
        return excludeContent;
    }
    public void setAddJsFunction(String addJsFunction)
    {
        this.addJsFunction = addJsFunction;
    }

    public String getAddJsFunction()
    {
        return addJsFunction;
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
            .append("sysCode", getSysCode())
            .append("pageUrl", getPageUrl())
            .append("pageName", getPageName())
            .append("nodeCode", getNodeCode())
            .append("excludeContent", getExcludeContent())
            .append("addJsFunction", getAddJsFunction())
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
