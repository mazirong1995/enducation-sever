package com.ruoyi.system.controller;

import java.util.List;
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
import com.ruoyi.system.domain.SysCompulsoryCourseDetail;
import com.ruoyi.system.service.ISysCompulsoryCourseDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程详情Controller
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/system/detail")
public class SysCompulsoryCourseDetailController extends BaseController
{
    @Autowired
    private ISysCompulsoryCourseDetailService sysCompulsoryCourseDetailService;

    /**
     * 查询课程详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysCompulsoryCourseDetail sysCompulsoryCourseDetail)
    {
        startPage();
        List<SysCompulsoryCourseDetail> list = sysCompulsoryCourseDetailService.selectSysCompulsoryCourseDetailList(sysCompulsoryCourseDetail);
        return getDataTable(list);
    }

    /**
     * 导出课程详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @Log(title = "课程详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCompulsoryCourseDetail sysCompulsoryCourseDetail)
    {
        List<SysCompulsoryCourseDetail> list = sysCompulsoryCourseDetailService.selectSysCompulsoryCourseDetailList(sysCompulsoryCourseDetail);
        ExcelUtil<SysCompulsoryCourseDetail> util = new ExcelUtil<SysCompulsoryCourseDetail>(SysCompulsoryCourseDetail.class);
        util.exportExcel(response, list, "课程详情数据");
    }

    /**
     * 获取课程详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysCompulsoryCourseDetailService.selectSysCompulsoryCourseDetailById(id));
    }

    /**
     * 新增课程详情
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "课程详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCompulsoryCourseDetail sysCompulsoryCourseDetail)
    {
        return toAjax(sysCompulsoryCourseDetailService.insertSysCompulsoryCourseDetail(sysCompulsoryCourseDetail));
    }

    /**
     * 修改课程详情
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "课程详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCompulsoryCourseDetail sysCompulsoryCourseDetail)
    {
        return toAjax(sysCompulsoryCourseDetailService.updateSysCompulsoryCourseDetail(sysCompulsoryCourseDetail));
    }

    /**
     * 删除课程详情
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "课程详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCompulsoryCourseDetailService.deleteSysCompulsoryCourseDetailByIds(ids));
    }
}
