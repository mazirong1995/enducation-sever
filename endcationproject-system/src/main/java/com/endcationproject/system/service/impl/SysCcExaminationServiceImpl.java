package com.endcationproject.system.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.endcationproject.common.utils.SecurityUtils;
import com.endcationproject.system.service.ISysCompulsoryCourseDetailService;
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
public class SysCcExaminationServiceImpl implements ISysCcExaminationService {
    @Autowired
    private SysCcExaminationMapper sysCcExaminationMapper;
    @Autowired
    private ISysCompulsoryCourseDetailService sysCompulsoryCourseDetailService;

    /**
     * 查询课程考试题库
     *
     * @param id 课程考试题库主键
     * @return 课程考试题库
     */
    @Override
    public SysCcExamination selectSysCcExaminationById(Long id) {
        SysCcExamination e = sysCcExaminationMapper.selectSysCcExaminationById(id);
        String a = e.getCcExaminationPath();
        if(a.contains("&")){
            String filename = a.substring(a.indexOf("&")+1,a.lastIndexOf("&"));
            e.setCcExaminationPathName(filename);
        }
        return e;
    }

    /**
     * 查询课程考试题库列表
     *
     * @param sysCcExamination 课程考试题库
     * @return 课程考试题库
     */
    @Override
    public List<SysCcExamination> selectSysCcExaminationList(SysCcExamination sysCcExamination) {
        List<SysCcExamination> sysCcExaminations = sysCcExaminationMapper.selectSysCcExaminationList(sysCcExamination);
        sysCcExaminations.forEach(e -> {
            String a = e.getCcExaminationPath();
            if (a.contains("&")) {
                String filename = a.substring(a.indexOf("&") + 1, a.lastIndexOf("&"));
                e.setCcExaminationPathName(filename);
            }
        });
        return sysCcExaminations;
    }

    @Override
    public List<SysCcExamination> selectSysCcExaminationList1(List<String> asList) {
        List<SysCcExamination> sysCcExaminations = sysCcExaminationMapper.selectSysCcExaminationList1(asList);
        sysCcExaminations.forEach(e -> {
            String a = e.getCcExaminationPath();
            if (a.contains("&")) {
                String filename = a.substring(a.indexOf("&") + 1, a.lastIndexOf("&"));
                e.setCcExaminationPathName(filename);
            }
        });
        return sysCcExaminations;
    }

    /**
     * 新增课程考试题库
     *
     * @param sysCcExamination 课程考试题库
     * @return 结果
     */
    @Override
    public int insertSysCcExamination(SysCcExamination sysCcExamination) {
        return sysCcExaminationMapper.insertSysCcExamination(sysCcExamination);
    }

    /**
     * 修改课程考试题库
     *
     * @param sysCcExamination 课程考试题库
     * @return 结果
     */
    @Override
    public int updateSysCcExamination(SysCcExamination sysCcExamination) {
        return sysCcExaminationMapper.updateSysCcExamination(sysCcExamination);
    }

    /**
     * 批量删除课程考试题库
     *
     * @param ids 需要删除的课程考试题库主键
     * @return 结果
     */
    @Override
    public int deleteSysCcExaminationByIds(Long[] ids) {
        return sysCcExaminationMapper.deleteSysCcExaminationByIds(ids);
    }

    /**
     * 删除课程考试题库信息
     *
     * @param id 课程考试题库主键
     * @return 结果
     */
    @Override
    public int deleteSysCcExaminationById(Long id) {
        return sysCcExaminationMapper.deleteSysCcExaminationById(id);
    }

    @Override
    public List<Map<String, Object>> pullDownExaminatName() {
        //获取学生选择的课程id
        Map<String, Object> result = sysCompulsoryCourseDetailService.getStuCourses(SecurityUtils.getUserId());
        String[] split;
        if (result != null) {
            Object ccIds = result.get("ccIds");
            String s = String.valueOf(ccIds).replace("[", "").replace("]", "");
           split = s.split(",");
        }else{
            return null;
        }
        List<Map<String, Object>> pullDown = sysCcExaminationMapper.selectExaminationByUser(Arrays.asList(split));
        return pullDown;
    }
}
