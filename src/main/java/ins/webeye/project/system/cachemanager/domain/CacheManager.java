package ins.webeye.project.system.cachemanager.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 缓存管理对象 sys_cache_manager
 * 
 * @author webeye
 * @date 2020-09-08
 */
public class CacheManager extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 缓存的Key */
    @Excel(name = "缓存的Key")
    private String cacheKey;

    /** 缓存名称 */
    @Excel(name = "缓存名称")
    private String cacheKeyDesc;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createUser;

    /** 更新者 */
    private String updateUser;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCacheKey(String cacheKey)
    {
        this.cacheKey = cacheKey;
    }

    public String getCacheKey()
    {
        return cacheKey;
    }
    public void setCacheKeyDesc(String cacheKeyDesc)
    {
        this.cacheKeyDesc = cacheKeyDesc;
    }

    public String getCacheKeyDesc()
    {
        return cacheKeyDesc;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cacheKey", getCacheKey())
            .append("cacheKeyDesc", getCacheKeyDesc())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
