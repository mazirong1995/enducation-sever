package com.endcationproject.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.endcationproject.common.annotation.Excel;
import com.endcationproject.common.core.domain.BaseEntity;

/**
 * 学生选课对象 sys_cc_stu
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public class SysCcStu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 学生id */
    @Excel(name = "学生id")
    private Long stuId;

    @Excel(name = "学生id")
    private String stuIdName;

    /** 课程数组 */
    @Excel(name = "课程数组")
    private String ccIds;

    /** 课程数组 */
    @Excel(name = "课程数组")
    private String ccIdsName;

    public String getStuIdName() {
        return stuIdName;
    }

    public void setStuIdName(String stuIdName) {
        this.stuIdName = stuIdName;
    }

    public String getCcIdsName() {
        return ccIdsName;
    }

    public void setCcIdsName(String ccIdsName) {
        this.ccIdsName = ccIdsName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStuId(Long stuId)
    {
        this.stuId = stuId;
    }

    public Long getStuId()
    {
        return stuId;
    }
    public void setCcIds(String ccIds)
    {
        this.ccIds = ccIds;
    }

    public String getCcIds()
    {
        return ccIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("stuId", getStuId())
            .append("ccIds", getCcIds())
            .toString();
    }
}
