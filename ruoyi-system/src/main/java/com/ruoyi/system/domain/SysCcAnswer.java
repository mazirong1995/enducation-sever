package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程考试答案库对象 sys_cc_answer
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
public class SysCcAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 课程id */
    @Excel(name = "课程id")
    private Long ccId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long stuId;

    /** 考评教师id */
    @Excel(name = "考评教师id")
    private Long teaId;

    /** 课程考试答案图片地址数组 */
    @Excel(name = "课程考试答案图片地址数组")
    private String anPath;

    /** 成绩 */
    @Excel(name = "成绩")
    private String anGrade;

    /** 备注 */
    @Excel(name = "备注")
    private String anRemark;

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
    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }
    public void setTeaId(Long teaId) 
    {
        this.teaId = teaId;
    }

    public Long getTeaId() 
    {
        return teaId;
    }
    public void setAnPath(String anPath) 
    {
        this.anPath = anPath;
    }

    public String getAnPath() 
    {
        return anPath;
    }
    public void setAnGrade(String anGrade) 
    {
        this.anGrade = anGrade;
    }

    public String getAnGrade() 
    {
        return anGrade;
    }
    public void setAnRemark(String anRemark) 
    {
        this.anRemark = anRemark;
    }

    public String getAnRemark() 
    {
        return anRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ccId", getCcId())
            .append("stuId", getStuId())
            .append("teaId", getTeaId())
            .append("anPath", getAnPath())
            .append("anGrade", getAnGrade())
            .append("anRemark", getAnRemark())
            .toString();
    }
}
