package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCcStuMapper;
import com.ruoyi.system.domain.SysCcStu;
import com.ruoyi.system.service.ISysCcStuService;

/**
 * 学生选课Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@Service
public class SysCcStuServiceImpl implements ISysCcStuService
{
    @Autowired
    private SysCcStuMapper sysCcStuMapper;

    /**
     * 查询学生选课
     *
     * @param id 学生选课主键
     * @return 学生选课
     */
    @Override
    public SysCcStu selectSysCcStuById(Long id)
    {
        return sysCcStuMapper.selectSysCcStuById(id);
    }

    /**
     * 查询学生选课列表
     *
     * @param sysCcStu 学生选课
     * @return 学生选课
     */
    @Override
    public List<SysCcStu> selectSysCcStuList(SysCcStu sysCcStu)
    {
        return sysCcStuMapper.selectSysCcStuList(sysCcStu);
    }

    /**
     * 新增学生选课
     *
     * @param sysCcStu 学生选课
     * @return 结果
     */
    @Override
    public int insertSysCcStu(SysCcStu sysCcStu)
    {
        List<SysCcStu> sysCcStus = sysCcStuMapper.selectSysCcStuList(sysCcStu);
        if(sysCcStus.size()>0){
            SysCcStu sysCcStusNew = new SysCcStu();
            sysCcStusNew.setId(sysCcStus.get(0).getId());
            sysCcStusNew.setCcIds(sysCcStu.getCcIds());
            return sysCcStuMapper.updateSysCcStu(sysCcStusNew);
        }else{
            return sysCcStuMapper.insertSysCcStu(sysCcStu);
        }

    }

    /**
     * 修改学生选课
     *
     * @param sysCcStu 学生选课
     * @return 结果
     */
    @Override
    public int updateSysCcStu(SysCcStu sysCcStu)
    {
        return sysCcStuMapper.updateSysCcStu(sysCcStu);
    }

    /**
     * 批量删除学生选课
     *
     * @param ids 需要删除的学生选课主键
     * @return 结果
     */
    @Override
    public int deleteSysCcStuByIds(Long[] ids)
    {
        return sysCcStuMapper.deleteSysCcStuByIds(ids);
    }

    /**
     * 删除学生选课信息
     *
     * @param id 学生选课主键
     * @return 结果
     */
    @Override
    public int deleteSysCcStuById(Long id)
    {
        return sysCcStuMapper.deleteSysCcStuById(id);
    }

    @Override
    public List<String> getStuIds() {
        return sysCcStuMapper.getStuIds(SecurityUtils.getUserId());
    }

    @Override
    public List<SysCcStu> selectSysCcStuData(List<String> stuIds) {
        return sysCcStuMapper.selectSysCcStuData(stuIds);
    }
}
