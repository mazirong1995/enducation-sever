package com.endcationproject.system.service;

import java.util.List;
import java.util.Map;

import com.endcationproject.system.domain.SysCcStu;

/**
 * 学生选课Service接口
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public interface ISysCcStuService
{
    /**
     * 查询学生选课
     *
     * @param id 学生选课主键
     * @return 学生选课
     */
    public SysCcStu selectSysCcStuById(Long id);

    /**
     * 查询学生选课列表
     *
     * @param sysCcStu 学生选课
     * @return 学生选课集合
     */
    public List<SysCcStu> selectSysCcStuList(SysCcStu sysCcStu);

    /**
     * 新增学生选课
     *
     * @param sysCcStu 学生选课
     * @return 结果
     */
    public int insertSysCcStu(SysCcStu sysCcStu);

    /**
     * 修改学生选课
     *
     * @param sysCcStu 学生选课
     * @return 结果
     */
    public int updateSysCcStu(SysCcStu sysCcStu);

    /**
     * 批量删除学生选课
     *
     * @param ids 需要删除的学生选课主键集合
     * @return 结果
     */
    public int deleteSysCcStuByIds(Long[] ids);

    /**
     * 删除学生选课信息
     *
     * @param id 学生选课主键
     * @return 结果
     */
    public int deleteSysCcStuById(Long id);

    List<String> getStuIds(Long userId);

    List<Map<String,Object>> getStuIdsForPD(Long userId);

    List<SysCcStu> selectSysCcStuData(List<String> stuIds);
}
