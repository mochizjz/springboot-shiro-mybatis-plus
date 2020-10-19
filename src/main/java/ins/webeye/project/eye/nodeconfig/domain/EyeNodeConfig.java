package ins.webeye.project.eye.nodeconfig.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 节点配置对象 eye_node_config
 * 
 * @author xiaoquan
 * @date 2020-07-16
 */
public class EyeNodeConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 节点代码 */
    @Excel(name = "节点代码")
    private String nodeCode;

    /** 节点名称 */
    @Excel(name = "节点名称")
    private String nodeName;

    /** 关联产品代码 */
    @Excel(name = "关联产品代码")
    private String relationProductCode;

    /** 节点类型 */
    @Excel(name = "节点类型")
    private String nodeType;

    /** 操作最少用时 */
    @Excel(name = "操作最少用时")
    private Integer operMinTime;

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
    public void setNodeCode(String nodeCode)
    {
        this.nodeCode = nodeCode;
    }

    public String getNodeCode()
    {
        return nodeCode;
    }
    public void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    public String getNodeName()
    {
        return nodeName;
    }
    public void setRelationProductCode(String relationProductCode)
    {
        this.relationProductCode = relationProductCode;
    }

    public String getRelationProductCode()
    {
        return relationProductCode;
    }
    public void setNodeType(String nodeType)
    {
        this.nodeType = nodeType;
    }

    public String getNodeType()
    {
        return nodeType;
    }
    public void setOperMinTime(Integer operMinTime)
    {
        this.operMinTime = operMinTime;
    }

    public Integer getOperMinTime()
    {
        return operMinTime;
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
            .append("nodeCode", getNodeCode())
            .append("nodeName", getNodeName())
            .append("relationProductCode", getRelationProductCode())
            .append("nodeType", getNodeType())
            .append("operMinTime", getOperMinTime())
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
