package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SysCompulsoryCourseDetail;

/**
 * 课程详情Service接口
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
public interface ISysCompulsoryCourseDetailService 
{
    /**
     * 查询课程详情
     * 
     * @param id 课程详情主键
     * @return 课程详情
     */
    public SysCompulsoryCourseDetail selectSysCompulsoryCourseDetailById(Long id);

    /**
     * 查询课程详情列表
     * 
     * @param sysCompulsoryCourseDetail 课程详情
     * @return 课程详情集合
     */
    public List<SysCompulsoryCourseDetail> selectSysCompulsoryCourseDetailList(SysCompulsoryCourseDetail sysCompulsoryCourseDetail);

    /**
     * 新增课程详情
     * 
     * @param sysCompulsoryCourseDetail 课程详情
     * @return 结果
     */
    public int insertSysCompulsoryCourseDetail(SysCompulsoryCourseDetail sysCompulsoryCourseDetail);

    /**
     * 修改课程详情
     * 
     * @param sysCompulsoryCourseDetail 课程详情
     * @return 结果
     */
    public int updateSysCompulsoryCourseDetail(SysCompulsoryCourseDetail sysCompulsoryCourseDetail);

    /**
     * 批量删除课程详情
     * 
     * @param ids 需要删除的课程详情主键集合
     * @return 结果
     */
    public int deleteSysCompulsoryCourseDetailByIds(Long[] ids);

    /**
     * 删除课程详情信息
     * 
     * @param id 课程详情主键
     * @return 结果
     */
    public int deleteSysCompulsoryCourseDetailById(Long id);

    List<Map<String, Object>> pullDownCourse(String flag);

    List<SysCompulsoryCourseDetail> list2(List<String> Courses);

    Map<String, Object> getStuCourses(String userId);
}
