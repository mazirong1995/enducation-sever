package com.endcationproject.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.endcationproject.common.utils.SecurityUtils;
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
import com.endcationproject.common.annotation.Log;
import com.endcationproject.common.core.controller.BaseController;
import com.endcationproject.common.core.domain.AjaxResult;
import com.endcationproject.common.enums.BusinessType;
import com.endcationproject.system.domain.SysCcAnswer;
import com.endcationproject.system.service.ISysCcAnswerService;
import com.endcationproject.common.utils.poi.ExcelUtil;
import com.endcationproject.common.core.page.TableDataInfo;

/**
 * 课程考试答案库Controller
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/system/answer")
public class SysCcAnswerController extends BaseController
{
    @Autowired
    private ISysCcAnswerService sysCcAnswerService;

    /**
     * 查询课程考试答案库列表
     */
    @PreAuthorize("@ss.hasPermi('system:answer:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysCcAnswer sysCcAnswer)
    {
        startPage();
        List<SysCcAnswer> list = sysCcAnswerService.selectSysCcAnswerList(sysCcAnswer);
        return getDataTable(list);
    }

    /**
     * 导出课程考试答案库列表
     */
    @PreAuthorize("@ss.hasPermi('system:answer:query')")
    @Log(title = "课程考试答案库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCcAnswer sysCcAnswer)
    {
        List<SysCcAnswer> list = sysCcAnswerService.selectSysCcAnswerList(sysCcAnswer);
        ExcelUtil<SysCcAnswer> util = new ExcelUtil<SysCcAnswer>(SysCcAnswer.class);
        util.exportExcel(response, list, "课程考试答案库数据");
    }

    /**
     * 获取课程考试答案库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:answer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysCcAnswerService.selectSysCcAnswerById(id));
    }

    /**
     * 新增课程考试答案库
     */
    @PreAuthorize("@ss.hasPermi('system:answer:edit')")
    @Log(title = "课程考试答案库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCcAnswer sysCcAnswer)
    {
        return toAjax(sysCcAnswerService.insertSysCcAnswer(sysCcAnswer));
    }

    /**
     * 学生新增课程考试答案库
     */
    @PreAuthorize("@ss.hasPermi('system:answer:edit')")
    @Log(title = "课程考试答案库", businessType = BusinessType.INSERT)
    @PostMapping("/stuAnswer")
    public AjaxResult add1(@RequestBody SysCcAnswer sysCcAnswer)
    {
        sysCcAnswer.setStuId(SecurityUtils.getUserId());
        return toAjax(sysCcAnswerService.insertSysCcAnswer(sysCcAnswer));
    }

    /**
     * 教师修改课程考试答案库
     */
    @PreAuthorize("@ss.hasPermi('system:answer:edit')")
    @Log(title = "课程考试答案库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCcAnswer sysCcAnswer)
    {
        SysCcAnswer sysCcAnswer1 = sysCcAnswerService.selectSysCcAnswerById(sysCcAnswer.getId());
        sysCcAnswer1.setTeaId(SecurityUtils.getUserId());
        sysCcAnswer1.setAnGrade(sysCcAnswer.getAnGrade());
        sysCcAnswer1.setAnRemark(sysCcAnswer.getAnRemark());
        return toAjax(sysCcAnswerService.updateSysCcAnswer(sysCcAnswer1));
    }

    /**
     * 删除课程考试答案库
     */
    @PreAuthorize("@ss.hasPermi('system:answer:edit')")
    @Log(title = "课程考试答案库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCcAnswerService.deleteSysCcAnswerByIds(ids));
    }
}
