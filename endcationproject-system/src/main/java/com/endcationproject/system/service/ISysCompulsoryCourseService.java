package com.endcationproject.system.service;

import java.util.List;
import java.util.Map;

import com.endcationproject.system.domain.SysCompulsoryCourse;

/**
 * 课程Service接口
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public interface ISysCompulsoryCourseService
{
    /**
     * 查询课程
     *
     * @param id 课程主键
     * @return 课程
     */
    public SysCompulsoryCourse selectSysCompulsoryCourseById(Long id);

    /**
     * 查询课程列表
     *
     * @param sysCompulsoryCourse 课程
     * @return 课程集合
     */
    public List<SysCompulsoryCourse> selectSysCompulsoryCourseList(SysCompulsoryCourse sysCompulsoryCourse);

    /**
     * 新增课程
     *
     * @param sysCompulsoryCourse 课程
     * @return 结果
     */
    public int insertSysCompulsoryCourse(SysCompulsoryCourse sysCompulsoryCourse);

    /**
     * 修改课程
     *
     * @param sysCompulsoryCourse 课程
     * @return 结果
     */
    public int updateSysCompulsoryCourse(SysCompulsoryCourse sysCompulsoryCourse);

    /**
     * 批量删除课程
     *
     * @param ids 需要删除的课程主键集合
     * @return 结果
     */
    public int deleteSysCompulsoryCourseByIds(Long[] ids);

    /**
     * 删除课程信息
     *
     * @param id 课程主键
     * @return 结果
     */
    public int deleteSysCompulsoryCourseById(Long id);

    List<Map<String, Object>> getPullDown(String uesrId);

    List<Map<String, Object>> getPullDownForStu(String uesrId);

    List<String> getCourseIds(Long userId);

    List<SysCompulsoryCourse> selectSysCompulsoryCourses(List<String> courseIds);
}
