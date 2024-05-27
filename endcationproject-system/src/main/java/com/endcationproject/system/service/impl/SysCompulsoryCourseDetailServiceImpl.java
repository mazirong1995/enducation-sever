package com.endcationproject.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.endcationproject.common.utils.uuid.IdUtils;
import com.endcationproject.system.domain.vo.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.endcationproject.system.mapper.SysCompulsoryCourseDetailMapper;
import com.endcationproject.system.domain.SysCompulsoryCourseDetail;
import com.endcationproject.system.service.ISysCompulsoryCourseDetailService;

/**
 * 课程详情Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@Service
public class SysCompulsoryCourseDetailServiceImpl implements ISysCompulsoryCourseDetailService {
    @Autowired
    private SysCompulsoryCourseDetailMapper sysCompulsoryCourseDetailMapper;

    /**
     * 查询课程详情
     *
     * @param id 课程详情主键
     * @return 课程详情
     */
    @Override
    public SysCompulsoryCourseDetail selectSysCompulsoryCourseDetailById(Long id) {
        SysCompulsoryCourseDetail e = sysCompulsoryCourseDetailMapper.selectSysCompulsoryCourseDetailById(id);
        String a = e.getCcdDataPath();
        if(a.contains("&")){
            String filename = a.substring(a.indexOf("&")+1,a.lastIndexOf("&"));
            e.setCcdDataPathName(filename);
        }
        return e;
    }

    /**
     * 查询课程详情列表
     *
     * @param sysCompulsoryCourseDetail 课程详情
     * @return 课程详情
     */
    @Override
    public List<SysCompulsoryCourseDetail> selectSysCompulsoryCourseDetailList(SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        List<SysCompulsoryCourseDetail> sysCompulsoryCourseDetails = sysCompulsoryCourseDetailMapper.selectSysCompulsoryCourseDetailList(sysCompulsoryCourseDetail);
        sysCompulsoryCourseDetails.forEach(e->{
            String a = e.getCcdDataPath();
            if(a.contains("&")){
                String filename = a.substring(a.indexOf("&")+1,a.lastIndexOf("&"));
                e.setCcdDataPathName(filename);
            }
        });
        return sysCompulsoryCourseDetails;
    }


    /**
     * 查询课程详情列表
     *
     * @param sysCompulsoryCourseDetail 课程详情
     * @return 课程详情
     */
    @Override
    public List<TreeVo> selectSysCompulsoryCourseDetailList1(SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        List<SysCompulsoryCourseDetail> sysCompulsoryCourseDetails = sysCompulsoryCourseDetailMapper.selectSysCompulsoryCourseDetailList(sysCompulsoryCourseDetail);
        Map<String, List<SysCompulsoryCourseDetail>> collect = sysCompulsoryCourseDetails.stream().collect(Collectors.groupingBy(SysCompulsoryCourseDetail::getCcName));
        List<TreeVo> result = new ArrayList<>();
        collect.forEach((key, value) -> {
            TreeVo treeVo = new TreeVo();
            treeVo.setName(key);
            treeVo.setChildren(value);
            result.add(treeVo);
        });
        return result;
    }

    /**
     * 新增课程详情
     *
     * @param sysCompulsoryCourseDetail 课程详情
     * @return 结果
     */
    @Override
    public int insertSysCompulsoryCourseDetail(SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        return sysCompulsoryCourseDetailMapper.insertSysCompulsoryCourseDetail(sysCompulsoryCourseDetail);
    }

    /**
     * 修改课程详情
     *
     * @param sysCompulsoryCourseDetail 课程详情
     * @return 结果
     */
    @Override
    public int updateSysCompulsoryCourseDetail(SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        return sysCompulsoryCourseDetailMapper.updateSysCompulsoryCourseDetail(sysCompulsoryCourseDetail);
    }

    /**
     * 批量删除课程详情
     *
     * @param ids 需要删除的课程详情主键
     * @return 结果
     */
    @Override
    public int deleteSysCompulsoryCourseDetailByIds(Long[] ids) {
        return sysCompulsoryCourseDetailMapper.deleteSysCompulsoryCourseDetailByIds(ids);
    }

    /**
     * 删除课程详情信息
     *
     * @param id 课程详情主键
     * @return 结果
     */
    @Override
    public int deleteSysCompulsoryCourseDetailById(Long id) {
        return sysCompulsoryCourseDetailMapper.deleteSysCompulsoryCourseDetailById(id);
    }

    @Override
    public List<Map<String, Object>> pullDownCourse(String flag) {
        return sysCompulsoryCourseDetailMapper.pullDownCourse(flag);
    }

    @Override
    public List<SysCompulsoryCourseDetail> list2(List<String> Courses) {
        return sysCompulsoryCourseDetailMapper.list2(Courses);
    }

    @Override
    public List<TreeVo> list2_1(List<String> Courses) {
        List<SysCompulsoryCourseDetail> sysCompulsoryCourseDetails = sysCompulsoryCourseDetailMapper.list2(Courses);
        sysCompulsoryCourseDetails.forEach(e->{
            String a = e.getCcdDataPath();
            if(a.contains("&")){
                String filename = a.substring(a.indexOf("&")+1,a.lastIndexOf("&"));
                e.setCcdDataPathName(filename);
            }
        });

        Map<String, List<SysCompulsoryCourseDetail>> collect = sysCompulsoryCourseDetails.stream().collect(Collectors.groupingBy(SysCompulsoryCourseDetail::getCcName));
        List<TreeVo> result = new ArrayList<>();
        collect.forEach((key, value) -> {
            TreeVo treeVo = new TreeVo();
            treeVo.setName(key);
            treeVo.setId(IdUtils.fastUUID());
            treeVo.setCcName(key);
            treeVo.setChildren(value);
            result.add(treeVo);
        });
        return result;
    }

    @Override
    public Map<String, Object> getStuCourses(Long userId) {
        return sysCompulsoryCourseDetailMapper.getStuCourses(userId);
    }

    @Override
    public void updatePathById(SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        sysCompulsoryCourseDetailMapper.updatePathById(sysCompulsoryCourseDetail);

    }
}
