package com.endcationproject.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.endcationproject.system.mapper.SysCcExaminationMapper;
import com.endcationproject.system.domain.SysCcExamination;
import com.endcationproject.system.service.ISysCcExaminationService;

/**
 * 课程考试题库Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
@Service
public class SysCcExaminationServiceImpl implements ISysCcExaminationService 
{
    @Autowired
    private SysCcExaminationMapper sysCcExaminationMapper;

    /**
     * 查询课程考试题库
     * 
     * @param id 课程考试题库主键
     * @return 课程考试题库
     */
    @Override
    public SysCcExamination selectSysCcExaminationById(Long id)
    {
        return sysCcExaminationMapper.selectSysCcExaminationById(id);
    }

    /**
     * 查询课程考试题库列表
     * 
     * @param sysCcExamination 课程考试题库
     * @return 课程考试题库
     */
    @Override
    public List<SysCcExamination> selectSysCcExaminationList(SysCcExamination sysCcExamination)
    {
        return sysCcExaminationMapper.selectSysCcExaminationList(sysCcExamination);
    }

    /**
     * 新增课程考试题库
     * 
     * @param sysCcExamination 课程考试题库
     * @return 结果
     */
    @Override
    public int insertSysCcExamination(SysCcExamination sysCcExamination)
    {
        return sysCcExaminationMapper.insertSysCcExamination(sysCcExamination);
    }

    /**
     * 修改课程考试题库
     * 
     * @param sysCcExamination 课程考试题库
     * @return 结果
     */
    @Override
    public int updateSysCcExamination(SysCcExamination sysCcExamination)
    {
        return sysCcExaminationMapper.updateSysCcExamination(sysCcExamination);
    }

    /**
     * 批量删除课程考试题库
     * 
     * @param ids 需要删除的课程考试题库主键
     * @return 结果
     */
    @Override
    public int deleteSysCcExaminationByIds(Long[] ids)
    {
        return sysCcExaminationMapper.deleteSysCcExaminationByIds(ids);
    }

    /**
     * 删除课程考试题库信息
     * 
     * @param id 课程考试题库主键
     * @return 结果
     */
    @Override
    public int deleteSysCcExaminationById(Long id)
    {
        return sysCcExaminationMapper.deleteSysCcExaminationById(id);
    }
}
