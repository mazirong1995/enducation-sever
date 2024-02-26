package com.ruoyi.system.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.SysCompulsoryCourse;
import com.ruoyi.system.service.ISysCompulsoryCourseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程Controller
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/system/course")
public class SysCompulsoryCourseController extends BaseController
{
    @Autowired
    private ISysCompulsoryCourseService sysCompulsoryCourseService;

    /**
     * 查询必修课程列表
     */
    @PreAuthorize("@ss.hasPermi('system:course:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysCompulsoryCourse sysCompulsoryCourse)
    {
        startPage();
        sysCompulsoryCourse.setCcFlag("0");
        List<SysCompulsoryCourse> list = sysCompulsoryCourseService.selectSysCompulsoryCourseList(sysCompulsoryCourse);
        return getDataTable(list);
    }

    /**
     * 查询选修课程列表
     */
    @PreAuthorize("@ss.hasPermi('system:course:query')")
    @GetMapping("/list1")
    public TableDataInfo list1(SysCompulsoryCourse sysCompulsoryCourse)
    {
        startPage();
        sysCompulsoryCourse.setCcFlag("1");
        List<SysCompulsoryCourse> list = sysCompulsoryCourseService.selectSysCompulsoryCourseList(sysCompulsoryCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @PreAuthorize("@ss.hasPermi('system:course:query')")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCompulsoryCourse sysCompulsoryCourse)
    {
        List<SysCompulsoryCourse> list = sysCompulsoryCourseService.selectSysCompulsoryCourseList(sysCompulsoryCourse);
        ExcelUtil<SysCompulsoryCourse> util = new ExcelUtil<SysCompulsoryCourse>(SysCompulsoryCourse.class);
        util.exportExcel(response, list, "课程数据");
    }

    /**
     * 获取课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:course:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysCompulsoryCourseService.selectSysCompulsoryCourseById(id));
    }

    /**
     * 新增必修课程
     */
    @PreAuthorize("@ss.hasPermi('system:course:edit')")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCompulsoryCourse sysCompulsoryCourse)
    {
        sysCompulsoryCourse.setCcFlag("0");
        return toAjax(sysCompulsoryCourseService.insertSysCompulsoryCourse(sysCompulsoryCourse));
    }

    /**
     * 新增选修课程
     */
    @PreAuthorize("@ss.hasPermi('system:course:edit')")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    public AjaxResult add1(@RequestBody SysCompulsoryCourse sysCompulsoryCourse)
    {
        sysCompulsoryCourse.setCcFlag("1");
        return toAjax(sysCompulsoryCourseService.insertSysCompulsoryCourse(sysCompulsoryCourse));
    }

    /**
     * 修改课程
     */
    @PreAuthorize("@ss.hasPermi('system:course:edit')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCompulsoryCourse sysCompulsoryCourse)
    {
        return toAjax(sysCompulsoryCourseService.updateSysCompulsoryCourse(sysCompulsoryCourse));
    }

    /**
     * 删除课程
     */
    @PreAuthorize("@ss.hasPermi('system:course:edit')")
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCompulsoryCourseService.deleteSysCompulsoryCourseByIds(ids));
    }

    /**
     * 课程教师下拉
     */
    @PreAuthorize("@ss.hasPermi('system:course:query')")
    @GetMapping(value = {"/pullDown/","/pullDown/{uesrId}"})
    public AjaxResult getPullDown(@PathVariable(value = "userId", required = false) Long uesrId)
    {
        List<Map<String,Object>> result = sysCompulsoryCourseService.getPullDown(uesrId);
        return success(result);
    }
}
