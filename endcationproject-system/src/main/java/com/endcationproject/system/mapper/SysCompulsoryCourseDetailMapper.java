package com.endcationproject.system.mapper;

import java.util.List;
import java.util.Map;

import com.endcationproject.system.domain.SysCompulsoryCourseDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 课程详情Mapper接口
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public interface SysCompulsoryCourseDetailMapper
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
     * 删除课程详情
     *
     * @param id 课程详情主键
     * @return 结果
     */
    public int deleteSysCompulsoryCourseDetailById(Long id);

    /**
     * 批量删除课程详情
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCompulsoryCourseDetailByIds(Long[] ids);

    List<Map<String, Object>> pullDownCourse(@Param("flag") String flag);

    Map<String, Object> getStuCourses(@Param("userId") Long userId);

    List<SysCompulsoryCourseDetail> list2(@Param("courses") List<String> courses);
}
