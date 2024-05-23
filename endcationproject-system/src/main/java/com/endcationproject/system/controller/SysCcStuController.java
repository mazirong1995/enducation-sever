package com.endcationproject.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.endcationproject.common.core.domain.entity.SysUser;
import com.endcationproject.common.utils.SecurityUtils;
import com.endcationproject.system.service.ISysUserService;
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
import com.endcationproject.system.domain.SysCcStu;
import com.endcationproject.system.service.ISysCcStuService;
import com.endcationproject.common.utils.poi.ExcelUtil;
import com.endcationproject.common.core.page.TableDataInfo;

/**
 * 学生选课Controller
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/system/stu")
public class SysCcStuController extends BaseController {
    @Autowired
    private ISysCcStuService sysCcStuService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 教师查询学生选课列表
     */
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysCcStu sysCcStu) {

        Long userId = SecurityUtils.getUserId();
        SysUser sysUser = sysUserService.selectUserById(userId);
        if(!"1".equals(sysUser.getFlag())){
            userId = null;
        }
        List<String> stuIds = new ArrayList<>();
        if (sysCcStu.getStuId() != null && !"".equals(sysCcStu.getStuId())) {
            stuIds.add(sysCcStu.getStuId().toString());
        } else {
            //教师查询同一班级下的学生id
            stuIds = sysCcStuService.getStuIds(userId);
        }
        startPage();
        List<SysCcStu> list = sysCcStuService.selectSysCcStuData(stuIds);
        return getDataTable(list);
    }

    /**
     * 教师查询学生选课列表页面的学生下拉
     */
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @GetMapping("/pullDown")
    public AjaxResult pullDown() {
        Long userId = SecurityUtils.getUserId();
        SysUser sysUser = sysUserService.selectUserById(userId);
        if(!"1".equals(sysUser.getFlag())){
            userId = null;
        }
        List<Map<String, Object>> stuIdsForPD = sysCcStuService.getStuIdsForPD(userId);
        return success(stuIdsForPD);
    }


    /**
     * 查询学生自己选课列表
     */
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @GetMapping("/list1")
    public TableDataInfo list1(SysCcStu sysCcStu) {
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
    public void export(HttpServletResponse response, SysCcStu sysCcStu) {
        List<SysCcStu> list = sysCcStuService.selectSysCcStuList(sysCcStu);
        ExcelUtil<SysCcStu> util = new ExcelUtil<SysCcStu>(SysCcStu.class);
        util.exportExcel(response, list, "学生选课数据");
    }

    /**
     * 获取学生选课详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysCcStuService.selectSysCcStuById(id));
    }

    /**
     * 新增学生选课
     */
    @PreAuthorize("@ss.hasPermi('system:stu:edit')")
    @Log(title = "学生选课", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCcStu sysCcStu) {
        return toAjax(sysCcStuService.insertSysCcStu(sysCcStu));
    }

    /**
     * 新增学生选课
     */
    @PreAuthorize("@ss.hasPermi('system:stu:edit')")
    @Log(title = "学生选课", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    public AjaxResult add1(@RequestBody JSONObject jsonObject) {
        SysCcStu sysCcStu = new SysCcStu();
        //获取当前登录人的id
        Long userId = SecurityUtils.getUserId();
        sysCcStu.setStuId(userId);
        List<SysCcStu> sysCcStus = sysCcStuService.selectSysCcStuList(sysCcStu);
        if (sysCcStus.size() > 0) {
            sysCcStu.setId(sysCcStus.get(0).getId());
            sysCcStu.setCcIds(jsonObject.getJSONArray("ccIds").toString());
            return toAjax(sysCcStuService.updateSysCcStu(sysCcStu));
        } else {
            sysCcStu.setCcIds(jsonObject.getJSONArray("ccIds").toString());
            return toAjax(sysCcStuService.insertSysCcStu(sysCcStu));
        }
    }

    /**
     * 修改学生选课
     */
    @PreAuthorize("@ss.hasPermi('system:stu:edit')")
    @Log(title = "学生选课", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCcStu sysCcStu) {
        return toAjax(sysCcStuService.updateSysCcStu(sysCcStu));
    }

    /**
     * 删除学生选课
     */
    @PreAuthorize("@ss.hasPermi('system:stu:edit')")
    @Log(title = "学生选课", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysCcStuService.deleteSysCcStuByIds(ids));
    }
}
