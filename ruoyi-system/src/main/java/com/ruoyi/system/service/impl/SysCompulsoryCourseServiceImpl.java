package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCompulsoryCourseMapper;
import com.ruoyi.system.domain.SysCompulsoryCourse;
import com.ruoyi.system.service.ISysCompulsoryCourseService;

/**
 * 课程Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
@Service
public class SysCompulsoryCourseServiceImpl implements ISysCompulsoryCourseService 
{
    @Autowired
    private SysCompulsoryCourseMapper sysCompulsoryCourseMapper;

    /**
     * 查询课程
     * 
     * @param id 课程主键
     * @return 课程
     */
    @Override
    public SysCompulsoryCourse selectSysCompulsoryCourseById(Long id)
    {
        return sysCompulsoryCourseMapper.selectSysCompulsoryCourseById(id);
    }

    /**
     * 查询课程列表
     * 
     * @param sysCompulsoryCourse 课程
     * @return 课程
     */
    @Override
    public List<SysCompulsoryCourse> selectSysCompulsoryCourseList(SysCompulsoryCourse sysCompulsoryCourse)
    {
        return sysCompulsoryCourseMapper.selectSysCompulsoryCourseList(sysCompulsoryCourse);
    }

    /**
     * 新增课程
     * 
     * @param sysCompulsoryCourse 课程
     * @return 结果
     */
    @Override
    public int insertSysCompulsoryCourse(SysCompulsoryCourse sysCompulsoryCourse)
    {
        return sysCompulsoryCourseMapper.insertSysCompulsoryCourse(sysCompulsoryCourse);
    }

    /**
     * 修改课程
     * 
     * @param sysCompulsoryCourse 课程
     * @return 结果
     */
    @Override
    public int updateSysCompulsoryCourse(SysCompulsoryCourse sysCompulsoryCourse)
    {
        return sysCompulsoryCourseMapper.updateSysCompulsoryCourse(sysCompulsoryCourse);
    }

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteSysCompulsoryCourseByIds(Long[] ids)
    {
        return sysCompulsoryCourseMapper.deleteSysCompulsoryCourseByIds(ids);
    }

    /**
     * 删除课程信息
     * 
     * @param id 课程主键
     * @return 结果
     */
    @Override
    public int deleteSysCompulsoryCourseById(Long id)
    {
        return sysCompulsoryCourseMapper.deleteSysCompulsoryCourseById(id);
    }

    @Override
    public List<Map<String, Object>> getPullDown(Long uesrId) {
        return sysCompulsoryCourseMapper.getPullDown(uesrId);
    }

    @Override
    public List<String> getCourseIds(Long userId) {
        return sysCompulsoryCourseMapper.getCourseIds(userId);
    }

    @Override
    public List<SysCompulsoryCourse> selectSysCompulsoryCourses(List<String> courseIds) {
        return sysCompulsoryCourseMapper.selectSysCompulsoryCourses(courseIds);
    }
}
