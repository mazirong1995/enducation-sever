package com.endcationproject.system.service;

import java.util.List;
import java.util.Map;

import com.endcationproject.system.domain.SysCcExamination;

/**
 * 课程考试题库Service接口
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public interface ISysCcExaminationService
{
    /**
     * 查询课程考试题库
     *
     * @param id 课程考试题库主键
     * @return 课程考试题库
     */
    public SysCcExamination selectSysCcExaminationById(Long id);

    /**
     * 查询课程考试题库列表
     *
     * @param sysCcExamination 课程考试题库
     * @return 课程考试题库集合
     */
    public List<SysCcExamination> selectSysCcExaminationList(SysCcExamination sysCcExamination);

    /**
     * 新增课程考试题库
     *
     * @param sysCcExamination 课程考试题库
     * @return 结果
     */
    public int insertSysCcExamination(SysCcExamination sysCcExamination);

    /**
     * 修改课程考试题库
     *
     * @param sysCcExamination 课程考试题库
     * @return 结果
     */
    public int updateSysCcExamination(SysCcExamination sysCcExamination);

    /**
     * 批量删除课程考试题库
     *
     * @param ids 需要删除的课程考试题库主键集合
     * @return 结果
     */
    public int deleteSysCcExaminationByIds(Long[] ids);

    /**
     * 删除课程考试题库信息
     *
     * @param id 课程考试题库主键
     * @return 结果
     */
    public int deleteSysCcExaminationById(Long id);

    List<Map<String, Object>> pullDownExaminatName();

    List<SysCcExamination> selectSysCcExaminationList1(List<String> asList);
}
