package com.ruoyi.system.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SysCompulsoryCourse;
import org.apache.ibatis.annotations.Param;

/**
 * 课程Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
public interface SysCompulsoryCourseMapper 
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
     * 删除课程
     * 
     * @param id 课程主键
     * @return 结果
     */
    public int deleteSysCompulsoryCourseById(Long id);

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCompulsoryCourseByIds(Long[] ids);

    List<Map<String, Object>> getPullDown(@Param("uesrId") Long uesrId);

    List<String> getCourseIds(@Param("uesrId") Long userId);

    List<SysCompulsoryCourse> selectSysCompulsoryCourses(@Param("courseIds") List<String> courseIds);
}
