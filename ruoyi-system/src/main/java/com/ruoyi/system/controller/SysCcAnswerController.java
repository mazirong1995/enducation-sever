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
import com.ruoyi.system.domain.SysCcAnswer;
import com.ruoyi.system.service.ISysCcAnswerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
     * 修改课程考试答案库
     */
    @PreAuthorize("@ss.hasPermi('system:answer:edit')")
    @Log(title = "课程考试答案库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCcAnswer sysCcAnswer)
    {
        return toAjax(sysCcAnswerService.updateSysCcAnswer(sysCcAnswer));
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
