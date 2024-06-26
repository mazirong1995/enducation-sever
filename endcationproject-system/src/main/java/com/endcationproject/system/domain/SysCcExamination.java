package com.endcationproject.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.endcationproject.common.annotation.Excel;
import com.endcationproject.common.core.domain.BaseEntity;

/**
 * 课程考试题库对象 sys_cc_examination
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public class SysCcExamination extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 课程id */
    @Excel(name = "课程id")
    private Long ccId;

    /** 课程id */
    private String ccName;

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    /** 课程考试图片地址数组 */
    @Excel(name = "课程考试文档")
    private String ccExaminationPath;

    /** 课程考试图片地址数组 */
    @Excel(name = "课程考试名称")
    private String ccExaminationName;

    /** 课程考试图片地址数组 */
    @Excel(name = "课程考试文档")
    private String ccExaminationPathName;

    public String getCcExaminationName() {
        return ccExaminationName;
    }

    public void setCcExaminationName(String ccExaminationName) {
        this.ccExaminationName = ccExaminationName;
    }

    public String getCcExaminationPathName() {
        return ccExaminationPathName;
    }

    public void setCcExaminationPathName(String ccExaminationPathName) {
        this.ccExaminationPathName = ccExaminationPathName;
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
    public void setCcExaminationPath(String ccExaminationPath)
    {
        this.ccExaminationPath = ccExaminationPath;
    }

    public String getCcExaminationPath()
    {
        return ccExaminationPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ccId", getCcId())
            .append("ccExaminationPath", getCcExaminationPath())
            .toString();
    }
}
