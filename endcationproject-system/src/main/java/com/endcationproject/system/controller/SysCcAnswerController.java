package com.endcationproject.system.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.endcationproject.common.annotation.Anonymous;
import com.endcationproject.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.endcationproject.common.annotation.Log;
import com.endcationproject.common.core.controller.BaseController;
import com.endcationproject.common.core.domain.AjaxResult;
import com.endcationproject.common.enums.BusinessType;
import com.endcationproject.system.domain.SysCcAnswer;
import com.endcationproject.system.service.ISysCcAnswerService;
import com.endcationproject.common.utils.poi.ExcelUtil;
import com.endcationproject.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程考试答案库Controller
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/system/answer")
public class SysCcAnswerController extends BaseController {
    @Autowired
    private ISysCcAnswerService sysCcAnswerService;

    /**
     * 查询课程考试答案库列表
     */
    @PreAuthorize("@ss.hasPermi('system:answer:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysCcAnswer sysCcAnswer) {
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
    public void export(HttpServletResponse response, SysCcAnswer sysCcAnswer) {
        List<SysCcAnswer> list = sysCcAnswerService.selectSysCcAnswerList(sysCcAnswer);
        ExcelUtil<SysCcAnswer> util = new ExcelUtil<SysCcAnswer>(SysCcAnswer.class);
        util.exportExcel(response, list, "课程考试答案库数据");
    }

    /**
     * 获取课程考试答案库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:answer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysCcAnswerService.selectSysCcAnswerById(id));
    }

    /**
     * 新增课程考试答案库
     */
    @PreAuthorize("@ss.hasPermi('system:answer:edit')")
    @Log(title = "课程考试答案库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCcAnswer sysCcAnswer) {
        return toAjax(sysCcAnswerService.insertSysCcAnswer(sysCcAnswer));
    }

    /**
     * 学生新增课程考试答案库
     */
    @PreAuthorize("@ss.hasPermi('system:answer:edit')")
    @Log(title = "课程考试答案库", businessType = BusinessType.INSERT)
    @PostMapping("/stuAnswer")
    public AjaxResult add1(@RequestBody SysCcAnswer sysCcAnswer) {
        sysCcAnswer.setStuId(SecurityUtils.getUserId());
        return toAjax(sysCcAnswerService.insertSysCcAnswer(sysCcAnswer));
    }

    /**
     * 教师修改课程考试答案库
     */
    @PreAuthorize("@ss.hasPermi('system:answer:edit')")
    @Log(title = "课程考试答案库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCcAnswer sysCcAnswer) {
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
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysCcAnswerService.deleteSysCcAnswerByIds(ids));
    }

    @PostMapping(value = "/uploadVidoe3")
    @Anonymous
    public Map<String,String> savaVideotest(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") String id)
            throws IllegalStateException {
        Map<String,String> resultMap = new HashMap<>();
        try{
            MultipartFile file = files.get(0);
            //获取文件后缀，因此此后端代码可接收一切文件，上传格式前端限定
            String fileName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)
                    .toLowerCase();
            // 重构文件名称
            String SavePath = "D:/上传文件";
            File path = new File(SavePath);
            if(!path.exists()){
                path.mkdirs();
            }
            System.out.println("前端传递的保存路径："+SavePath);
            String pikId = UUID.randomUUID().toString().replaceAll("-", "");
            String newVidoeName = pikId+"&"+fileName+ "&." + fileExt;
            System.out.println("重构文件名防止上传同名文件："+newVidoeName);
            //保存视频
            File fileSave = new File(SavePath, newVidoeName);
            file.transferTo(fileSave);
            //构造Map将视频信息返回给前端
            //视频名称重构后的名称
            resultMap.put("name",file.getOriginalFilename());
            //正确保存视频则设置返回码为200
            resultMap.put("resCode","200");
            //返回视频保存路径
            resultMap.put("url",SavePath + "/" + newVidoeName);
            return  resultMap;
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            //保存视频错误则设置返回码为400
            resultMap.put("resCode","400");
            return  resultMap ;
        }
    }
}
