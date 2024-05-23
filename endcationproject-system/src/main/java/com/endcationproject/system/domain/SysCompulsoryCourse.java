package com.endcationproject.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.endcationproject.common.annotation.Excel;
import com.endcationproject.common.core.domain.BaseEntity;

/**
 * 课程对象 sys_compulsory_course
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public class SysCompulsoryCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String ccName;

    /** 课程教师id */
    @Excel(name = "课程教师id")
    private String ccTeacher;

    /** 课程教师id */
    @Excel(name = "课程教师id")
    private String ccTeacherName;

    public String getCcTeacherName() {
        return ccTeacherName;
    }

    public void setCcTeacherName(String ccTeacherName) {
        this.ccTeacherName = ccTeacherName;
    }

    /** 课程时长 */
    @Excel(name = "课程时长")
    private String ccDuration;

    /** 课程开始时间 */
    @Excel(name = "课程开始时间")
    private String ccStartTime;

    /** 课程结束时间 */
    @Excel(name = "课程结束时间")
    private String ccEndTime;

    /** 课程预计考试时间 */
    @Excel(name = "课程预计考试时间")
    private String ccCheckTime;

    /** 0-必修、1-选修 */
    @Excel(name = "0-必修、1-选修")
    private String ccFlag;

    /** 备注 */
    @Excel(name = "备注")
    private String ccRemark;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCcName(String ccName)
    {
        this.ccName = ccName;
    }

    public String getCcName()
    {
        return ccName;
    }
    public void setCcTeacher(String ccTeacher)
    {
        this.ccTeacher = ccTeacher;
    }

    public String getCcTeacher()
    {
        return ccTeacher;
    }
    public void setCcDuration(String ccDuration)
    {
        this.ccDuration = ccDuration;
    }

    public String getCcDuration()
    {
        return ccDuration;
    }
    public void setCcStartTime(String ccStartTime)
    {
        this.ccStartTime = ccStartTime;
    }

    public String getCcStartTime()
    {
        return ccStartTime;
    }
    public void setCcEndTime(String ccEndTime)
    {
        this.ccEndTime = ccEndTime;
    }

    public String getCcEndTime()
    {
        return ccEndTime;
    }
    public void setCcCheckTime(String ccCheckTime)
    {
        this.ccCheckTime = ccCheckTime;
    }

    public String getCcCheckTime()
    {
        return ccCheckTime;
    }
    public void setCcFlag(String ccFlag)
    {
        this.ccFlag = ccFlag;
    }

    public String getCcFlag()
    {
        return ccFlag;
    }
    public void setCcRemark(String ccRemark)
    {
        this.ccRemark = ccRemark;
    }

    public String getCcRemark()
    {
        return ccRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ccName", getCcName())
            .append("ccTeacher", getCcTeacher())
            .append("ccDuration", getCcDuration())
            .append("ccStartTime", getCcStartTime())
            .append("ccEndTime", getCcEndTime())
            .append("ccCheckTime", getCcCheckTime())
            .append("ccFlag", getCcFlag())
            .append("ccRemark", getCcRemark())
            .toString();
    }
}
