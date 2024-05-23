package com.endcationproject.system.service;

import java.util.List;
import java.util.Map;

import com.endcationproject.system.domain.SysCompulsoryCourseDetail;
import com.endcationproject.system.domain.vo.TreeVo;

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
     * 查询课程详情列表
     * todo 树状结构
     * @param sysCompulsoryCourseDetail 课程详情
     * @return 课程详情集合
     */
    public List<TreeVo> selectSysCompulsoryCourseDetailList1(SysCompulsoryCourseDetail sysCompulsoryCourseDetail);

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

    /**
     * 学生已选课程详情列表
     * todo 树状结构
     * @return 课程详情集合
     */
    List<TreeVo> list2_1(List<String> Courses);

    Map<String, Object> getStuCourses(Long userId);
}
