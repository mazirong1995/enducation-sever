package com.endcationproject.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.endcationproject.common.annotation.Excel;
import com.endcationproject.common.core.domain.BaseEntity;

/**
 * 课程详情对象 sys_compulsory_course_detail
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public class SysCompulsoryCourseDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 课程id */
    @Excel(name = "课程id")
    private Long ccId;

    /** 课程明细名称 */
    @Excel(name = "课程明细名称")
    private String ccdName;

    /** 课程视频地址 */
    @Excel(name = "课程视频地址")
    private String ccdDataPath;

    /** 课程备注 */
    @Excel(name = "课程备注")
    private String ccdRemark;

    private String flag;

    private String ccName;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCcId(Long ccId)
    {
        this.ccId = ccId;
    }

    public Long getCcId()
    {
        return ccId;
    }
    public void setCcdName(String ccdName)
    {
        this.ccdName = ccdName;
    }

    public String getCcdName()
    {
        return ccdName;
    }
    public void setCcdDataPath(String ccdDataPath)
    {
        this.ccdDataPath = ccdDataPath;
    }

    public String getCcdDataPath()
    {
        return ccdDataPath;
    }
    public void setCcdRemark(String ccdRemark)
    {
        this.ccdRemark = ccdRemark;
    }

    public String getCcdRemark()
    {
        return ccdRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ccId", getCcId())
            .append("ccdName", getCcdName())
            .append("ccdDataPath", getCcdDataPath())
            .append("ccdRemark", getCcdRemark())
            .toString();
    }
}
