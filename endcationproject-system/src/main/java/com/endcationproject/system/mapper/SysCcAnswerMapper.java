package com.endcationproject.system.mapper;

import java.util.List;
import com.endcationproject.system.domain.SysCcAnswer;

/**
 * 课程考试答案库Mapper接口
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public interface SysCcAnswerMapper
{
    /**
     * 查询课程考试答案库
     *
     * @param id 课程考试答案库主键
     * @return 课程考试答案库
     */
    public SysCcAnswer selectSysCcAnswerById(Long id);

    /**
     * 查询课程考试答案库列表
     *
     * @param sysCcAnswer 课程考试答案库
     * @return 课程考试答案库集合
     */
    public List<SysCcAnswer> selectSysCcAnswerList(SysCcAnswer sysCcAnswer);

    /**
     * 新增课程考试答案库
     *
     * @param sysCcAnswer 课程考试答案库
     * @return 结果
     */
    public int insertSysCcAnswer(SysCcAnswer sysCcAnswer);

    /**
     * 修改课程考试答案库
     *
     * @param sysCcAnswer 课程考试答案库
     * @return 结果
     */
    public int updateSysCcAnswer(SysCcAnswer sysCcAnswer);

    /**
     * 删除课程考试答案库
     *
     * @param id 课程考试答案库主键
     * @return 结果
     */
    public int deleteSysCcAnswerById(Long id);

    /**
     * 批量删除课程考试答案库
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCcAnswerByIds(Long[] ids);

    List<SysCcAnswer> selectSysCcAnswerListForStu(SysCcAnswer sysCcAnswer);
}
