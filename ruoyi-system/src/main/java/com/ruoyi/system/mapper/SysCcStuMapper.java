package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysCcStu;
import org.apache.ibatis.annotations.Param;

/**
 * 学生选课Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
public interface SysCcStuMapper 
{
    /**
     * 查询学生选课
     * 
     * @param id 学生选课主键
     * @return 学生选课
     */
    public SysCcStu selectSysCcStuById(Long id);

    /**
     * 查询学生选课列表
     * 
     * @param sysCcStu 学生选课
     * @return 学生选课集合
     */
    public List<SysCcStu> selectSysCcStuList(SysCcStu sysCcStu);

    /**
     * 新增学生选课
     * 
     * @param sysCcStu 学生选课
     * @return 结果
     */
    public int insertSysCcStu(SysCcStu sysCcStu);

    /**
     * 修改学生选课
     * 
     * @param sysCcStu 学生选课
     * @return 结果
     */
    public int updateSysCcStu(SysCcStu sysCcStu);

    /**
     * 删除学生选课
     * 
     * @param id 学生选课主键
     * @return 结果
     */
    public int deleteSysCcStuById(Long id);

    /**
     * 批量删除学生选课
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCcStuByIds(Long[] ids);

    List<String> getStuIds(@Param("userId") String userId);

    List<SysCcStu> selectSysCcStuData(@Param("stuIds") List<String> stuIds);
}
