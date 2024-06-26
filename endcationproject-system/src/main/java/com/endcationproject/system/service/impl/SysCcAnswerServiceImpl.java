package com.endcationproject.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.endcationproject.common.utils.SecurityUtils;
import com.endcationproject.system.domain.SysCompulsoryCourseDetail;
import com.endcationproject.system.domain.vo.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.endcationproject.system.mapper.SysCcAnswerMapper;
import com.endcationproject.system.domain.SysCcAnswer;
import com.endcationproject.system.service.ISysCcAnswerService;

/**
 * 课程考试答案库Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@Service
public class SysCcAnswerServiceImpl implements ISysCcAnswerService {
    @Autowired
    private SysCcAnswerMapper sysCcAnswerMapper;

    /**
     * 查询课程考试答案库
     *
     * @param id 课程考试答案库主键
     * @return 课程考试答案库
     */
    @Override
    public SysCcAnswer selectSysCcAnswerById(Long id) {
        SysCcAnswer e = sysCcAnswerMapper.selectSysCcAnswerById(id);
        String a = e.getAnPath();
        if(a.contains("&")){
            String filename = a.substring(a.indexOf("&")+1,a.lastIndexOf("&"));
            e.setAnPathName(filename);
        }
        return e;
    }

    /**
     * 查询课程考试答案库列表
     *
     * @param sysCcAnswer 课程考试答案库
     * @return 课程考试答案库
     */
    @Override
    public List<SysCcAnswer> selectSysCcAnswerList(SysCcAnswer sysCcAnswer) {
        List<SysCcAnswer> sysCcAnswers = sysCcAnswerMapper.selectSysCcAnswerList(sysCcAnswer);
        sysCcAnswers.forEach(e->{
            String a = e.getAnPath();
            if(a.contains("&")){
                String filename = a.substring(a.indexOf("&")+1,a.lastIndexOf("&"));
                e.setAnPathName(filename);
            }
        });
        return sysCcAnswers;
    }


    /**
     * 教师查询课程考试答案库列表
     * todo 树状结构
     *
     * @param sysCcAnswer 课程考试答案库
     * @return 课程考试答案库
     */
    @Override
    public List<TreeVo> selectSysCcAnswerList1(SysCcAnswer sysCcAnswer) {
        List<SysCcAnswer> sysCcAnswers = sysCcAnswerMapper.selectSysCcAnswerList(sysCcAnswer);
        Map<String, List<SysCcAnswer>> collect = sysCcAnswers.stream().collect(Collectors.groupingBy(SysCcAnswer::getCcIdName));
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
     * 新增课程考试答案库
     *
     * @param sysCcAnswer 课程考试答案库
     * @return 结果
     */
    @Override
    public int insertSysCcAnswer(SysCcAnswer sysCcAnswer) {
        return sysCcAnswerMapper.insertSysCcAnswer(sysCcAnswer);
    }

    /**
     * 修改课程考试答案库
     *
     * @param sysCcAnswer 课程考试答案库
     * @return 结果
     */
    @Override
    public int updateSysCcAnswer(SysCcAnswer sysCcAnswer) {
        return sysCcAnswerMapper.updateSysCcAnswer(sysCcAnswer);
    }

    /**
     * 批量删除课程考试答案库
     *
     * @param ids 需要删除的课程考试答案库主键
     * @return 结果
     */
    @Override
    public int deleteSysCcAnswerByIds(Long[] ids) {
        return sysCcAnswerMapper.deleteSysCcAnswerByIds(ids);
    }

    /**
     * 删除课程考试答案库信息
     *
     * @param id 课程考试答案库主键
     * @return 结果
     */
    @Override
    public int deleteSysCcAnswerById(Long id) {
        return sysCcAnswerMapper.deleteSysCcAnswerById(id);
    }

    @Override
    public List<SysCcAnswer> selectSysCcAnswerListForStu(SysCcAnswer sysCcAnswer) {
        sysCcAnswer.setStuId(SecurityUtils.getUserId());
        List<SysCcAnswer> sysCcAnswers = sysCcAnswerMapper.selectSysCcAnswerListForStu(sysCcAnswer);
        sysCcAnswers.forEach(e->{
            String a = e.getAnPath();
            if(a.contains("&")){
                String filename = a.substring(a.indexOf("&")+1,a.lastIndexOf("&"));
                e.setAnPathName(filename);
            }
        });
        return sysCcAnswers;
    }
}
