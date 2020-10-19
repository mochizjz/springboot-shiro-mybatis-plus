package ins.webeye.project.eye.recallmanage.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ins.webeye.framework.aspectj.lang.annotation.Excel;
import ins.webeye.framework.web.domain.BaseEntity;

/**
 * 回溯管理对象 sys_recallmanage
 * 
 * @author webeye
 * @date 2020-07-08
 */
public class EyeRecallmanage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 回溯编码 */
    @Excel(name = "回溯编码")
    private String recallnum;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String projectname;

    /** 订单号 */
    @Excel(name = "订单号")
    private String ordernum;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policynum;

    /** 投保人姓名 */
    @Excel(name = "投保人姓名")
    private String policyname;

    /** 证件 */
    @Excel(name = "证件")
    private String policyidnum;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String address;

    /** 录制时间 */
    @Excel(name = "录制时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordingtime;

    /** 视频/图片编码 */
    @Excel(name = "视频/图片编码")
    private String videonum;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String filename;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filepath;

    /** 第三方存证平台 */
    @Excel(name = "第三方存证平台")
    private String existplatform;

    /** 送存时间 */
    @Excel(name = "送存时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date existtiem;

    /** 存证 */
    @Excel(name = "存证")
    private String existencestate;//送存状态  1：已送存 0：未送存

    /** 验真 */
    @Excel(name = "验真")
    private String truthstate;   //验真状态  1：已验真 0：未验真
    
    private String  events;
    
    @Excel(name = "环节/类型")
    private String  type;//类型

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setRecallnum(String recallnum)
    {
        this.recallnum = recallnum;
    }

    public String getRecallnum()
    {
        return recallnum;
    }
    public void setProjectname(String projectname)
    {
        this.projectname = projectname;
    }

    public String getProjectname()
    {
        return projectname;
    }
    public void setOrdernum(String ordernum)
    {
        this.ordernum = ordernum;
    }

    public String getOrdernum()
    {
        return ordernum;
    }
    public void setPolicynum(String policynum)
    {
        this.policynum = policynum;
    }

    public String getPolicynum()
    {
        return policynum;
    }
    public void setPolicyname(String policyname)
    {
        this.policyname = policyname;
    }

    public String getPolicyname()
    {
        return policyname;
    }
    public void setPolicyidnum(String policyidnum)
    {
        this.policyidnum = policyidnum;
    }

    public String getPolicyidnum()
    {
        return policyidnum;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setRecordingtime(Date recordingtime)
    {
        this.recordingtime = recordingtime;
    }

    public Date getRecordingtime()
    {
        return recordingtime;
    }
    public void setVideonum(String videonum)
    {
        this.videonum = videonum;
    }

    public String getVideonum()
    {
        return videonum;
    }
    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getFilename()
    {
        return filename;
    }
    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    public String getFilepath()
    {
        return filepath;
    }
    public void setExistplatform(String existplatform)
    {
        this.existplatform = existplatform;
    }

    public String getExistplatform()
    {
        return existplatform;
    }
    public void setExisttiem(Date existtiem)
    {
        this.existtiem = existtiem;
    }

    public Date getExisttiem()
    {
        return existtiem;
    }
    public void setExistencestate(String existencestate)
    {
        this.existencestate = existencestate;
    }

    public String getExistencestate()
    {
        return existencestate;
    }
    public void setTruthstate(String truthstate)
    {
        this.truthstate = truthstate;
    }

    public String getTruthstate()
    {
        return truthstate;
    }

    public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recallnum", getRecallnum())
            .append("projectname", getProjectname())
            .append("ordernum", getOrdernum())
            .append("policynum", getPolicynum())
            .append("policyname", getPolicyname())
            .append("policyidnum", getPolicyidnum())
            .append("address", getAddress())
            .append("recordingtime", getRecordingtime())
            .append("videonum", getVideonum())
            .append("filename", getFilename())
            .append("filepath", getFilepath())
            .append("existplatform", getExistplatform())
            .append("existtiem", getExisttiem())
            .append("existencestate", getExistencestate())
            .append("truthstate", getTruthstate())
            .append("type", getType())
            .toString();
    }
}
