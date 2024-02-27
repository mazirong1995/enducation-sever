package com.ruoyi.system.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysCcStu;
import com.ruoyi.system.service.ISysCcStuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生选课Controller
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/system/stu")
public class SysCcStuController extends BaseController
{
    @Autowired
    private ISysCcStuService sysCcStuService;

    /**
     * 教师查询学生选课列表
     * todo 传参用户id
     */
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysCcStu sysCcStu)
    {
        //教师查询同一班级下的学生id
        List<String> stuIds = sysCcStuService.getStuIds();
        startPage();
        List<SysCcStu> list = sysCcStuService.selectSysCcStuData(stuIds);
        return getDataTable(list);
    }

    /**
     * 查询学生自己选课列表
     * todo 传参用户id
     */
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @GetMapping("/list1")
    public TableDataInfo list1(SysCcStu sysCcStu)
    {
        sysCcStu.setStuId(SecurityUtils.getUserId());
        startPage();
        List<SysCcStu> list = sysCcStuService.selectSysCcStuList(sysCcStu);
        return getDataTable(list);
    }

    /**
     * 导出学生选课列表
     */
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @Log(title = "学生选课", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCcStu sysCcStu)
    {
        List<SysCcStu> list = sysCcStuService.selectSysCcStuList(sysCcStu);
        ExcelUtil<SysCcStu> util = new ExcelUtil<SysCcStu>(SysCcStu.class);
        util.exportExcel(response, list, "学生选课数据");
    }

    /**
     * 获取学生选课详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysCcStuService.selectSysCcStuById(id));
    }

    /**
     * 新增学生选课
     */
    @PreAuthorize("@ss.hasPermi('system:stu:edit')")
    @Log(title = "学生选课", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCcStu sysCcStu)
    {
        return toAjax(sysCcStuService.insertSysCcStu(sysCcStu));
    }

    /**
     * 修改学生选课
     */
    @PreAuthorize("@ss.hasPermi('system:stu:edit')")
    @Log(title = "学生选课", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCcStu sysCcStu)
    {
        return toAjax(sysCcStuService.updateSysCcStu(sysCcStu));
    }

    /**
     * 删除学生选课
     */
    @PreAuthorize("@ss.hasPermi('system:stu:edit')")
    @Log(title = "学生选课", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCcStuService.deleteSysCcStuByIds(ids));
    }
}
