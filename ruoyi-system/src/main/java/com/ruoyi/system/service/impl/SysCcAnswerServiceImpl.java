package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.SysCompulsoryCourseDetail;
import com.ruoyi.system.domain.vo.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCcAnswerMapper;
import com.ruoyi.system.domain.SysCcAnswer;
import com.ruoyi.system.service.ISysCcAnswerService;

/**
 * 课程考试答案库Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
@Service
public class SysCcAnswerServiceImpl implements ISysCcAnswerService 
{
    @Autowired
    private SysCcAnswerMapper sysCcAnswerMapper;

    /**
     * 查询课程考试答案库
     * 
     * @param id 课程考试答案库主键
     * @return 课程考试答案库
     */
    @Override
    public SysCcAnswer selectSysCcAnswerById(Long id)
    {
        return sysCcAnswerMapper.selectSysCcAnswerById(id);
    }

    /**
     * 查询课程考试答案库列表
     * 
     * @param sysCcAnswer 课程考试答案库
     * @return 课程考试答案库
     */
    @Override
    public List<SysCcAnswer> selectSysCcAnswerList(SysCcAnswer sysCcAnswer)
    {
        return sysCcAnswerMapper.selectSysCcAnswerList(sysCcAnswer);
    }


    /**
     * 教师查询课程考试答案库列表
     * todo 树状结构
     * @param sysCcAnswer 课程考试答案库
     * @return 课程考试答案库
     */
    @Override
    public List<TreeVo> selectSysCcAnswerList1(SysCcAnswer sysCcAnswer)
    {
        List<SysCcAnswer> sysCcAnswers = sysCcAnswerMapper.selectSysCcAnswerList(sysCcAnswer);
        Map<String, List<SysCcAnswer>> collect = sysCcAnswers.stream().collect(Collectors.groupingBy(SysCcAnswer::getCcIdName));
        List<TreeVo> result = new ArrayList<>();
        collect.forEach((key,value)->{
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
    public int insertSysCcAnswer(SysCcAnswer sysCcAnswer)
    {
        return sysCcAnswerMapper.insertSysCcAnswer(sysCcAnswer);
    }

    /**
     * 修改课程考试答案库
     * 
     * @param sysCcAnswer 课程考试答案库
     * @return 结果
     */
    @Override
    public int updateSysCcAnswer(SysCcAnswer sysCcAnswer)
    {
        return sysCcAnswerMapper.updateSysCcAnswer(sysCcAnswer);
    }

    /**
     * 批量删除课程考试答案库
     * 
     * @param ids 需要删除的课程考试答案库主键
     * @return 结果
     */
    @Override
    public int deleteSysCcAnswerByIds(Long[] ids)
    {
        return sysCcAnswerMapper.deleteSysCcAnswerByIds(ids);
    }

    /**
     * 删除课程考试答案库信息
     * 
     * @param id 课程考试答案库主键
     * @return 结果
     */
    @Override
    public int deleteSysCcAnswerById(Long id)
    {
        return sysCcAnswerMapper.deleteSysCcAnswerById(id);
    }
}
