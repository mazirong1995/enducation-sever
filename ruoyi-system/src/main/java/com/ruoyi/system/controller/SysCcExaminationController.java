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
import com.ruoyi.system.domain.SysCcExamination;
import com.ruoyi.system.service.ISysCcExaminationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程考试题库Controller
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/system/examination")
public class SysCcExaminationController extends BaseController
{
    @Autowired
    private ISysCcExaminationService sysCcExaminationService;

    /**
     * 查询课程考试题库列表
     */
    @PreAuthorize("@ss.hasPermi('system:examination:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysCcExamination sysCcExamination)
    {
        startPage();
        List<SysCcExamination> list = sysCcExaminationService.selectSysCcExaminationList(sysCcExamination);
        return getDataTable(list);
    }

    /**
     * 导出课程考试题库列表
     */
    @PreAuthorize("@ss.hasPermi('system:examination:query')")
    @Log(title = "课程考试题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCcExamination sysCcExamination)
    {
        List<SysCcExamination> list = sysCcExaminationService.selectSysCcExaminationList(sysCcExamination);
        ExcelUtil<SysCcExamination> util = new ExcelUtil<SysCcExamination>(SysCcExamination.class);
        util.exportExcel(response, list, "课程考试题库数据");
    }

    /**
     * 获取课程考试题库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:examination:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysCcExaminationService.selectSysCcExaminationById(id));
    }

    /**
     * 新增课程考试题库
     */
    @PreAuthorize("@ss.hasPermi('system:examination:edit')")
    @Log(title = "课程考试题库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCcExamination sysCcExamination)
    {
        return toAjax(sysCcExaminationService.insertSysCcExamination(sysCcExamination));
    }

    /**
     * 修改课程考试题库
     */
    @PreAuthorize("@ss.hasPermi('system:examination:edit')")
    @Log(title = "课程考试题库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCcExamination sysCcExamination)
    {
        return toAjax(sysCcExaminationService.updateSysCcExamination(sysCcExamination));
    }

    /**
     * 删除课程考试题库
     */
    @PreAuthorize("@ss.hasPermi('system:examination:edit')")
    @Log(title = "课程考试题库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCcExaminationService.deleteSysCcExaminationByIds(ids));
    }
}
