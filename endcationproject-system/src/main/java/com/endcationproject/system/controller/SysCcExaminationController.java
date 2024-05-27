package com.endcationproject.system.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.endcationproject.common.annotation.Anonymous;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.endcationproject.common.annotation.Log;
import com.endcationproject.common.core.controller.BaseController;
import com.endcationproject.common.core.domain.AjaxResult;
import com.endcationproject.common.enums.BusinessType;
import com.endcationproject.system.domain.SysCcExamination;
import com.endcationproject.system.service.ISysCcExaminationService;
import com.endcationproject.common.utils.poi.ExcelUtil;
import com.endcationproject.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程考试题库Controller
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/system/examination")
public class SysCcExaminationController extends BaseController {
    @Autowired
    private ISysCcExaminationService sysCcExaminationService;

    /**
     * 查询课程考试题库列表
     */
    @PreAuthorize("@ss.hasPermi('system:examination:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysCcExamination sysCcExamination) {
        startPage();
        List<SysCcExamination> list = sysCcExaminationService.selectSysCcExaminationList(sysCcExamination);
        return getDataTable(list);
    }

    /**
     * 根据登陆人查询考试名称列表
     */
    @PreAuthorize("@ss.hasPermi('system:examination:query')")
    @GetMapping("/pullDownExaminatName")
    public AjaxResult pullDownExaminatName() {
        List<Map<String, Object>> result = sysCcExaminationService.pullDownExaminatName();
        return success(result);
    }

    /**
     * 导出课程考试题库列表
     */
    @PreAuthorize("@ss.hasPermi('system:examination:query')")
    @Log(title = "课程考试题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCcExamination sysCcExamination) {
        List<SysCcExamination> list = sysCcExaminationService.selectSysCcExaminationList(sysCcExamination);
        ExcelUtil<SysCcExamination> util = new ExcelUtil<SysCcExamination>(SysCcExamination.class);
        util.exportExcel(response, list, "课程考试题库数据");
    }

    /**
     * 获取课程考试题库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:examination:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysCcExaminationService.selectSysCcExaminationById(id));
    }

    /**
     * 新增课程考试题库
     */
    @PreAuthorize("@ss.hasPermi('system:examination:edit')")
    @Log(title = "课程考试题库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCcExamination sysCcExamination) {
        return toAjax(sysCcExaminationService.insertSysCcExamination(sysCcExamination));
    }

    /**
     * 修改课程考试题库
     */
    @PreAuthorize("@ss.hasPermi('system:examination:edit')")
    @Log(title = "课程考试题库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCcExamination sysCcExamination) {
        return toAjax(sysCcExaminationService.updateSysCcExamination(sysCcExamination));
    }

    /**
     * 删除课程考试题库
     */
    @PreAuthorize("@ss.hasPermi('system:examination:edit')")
    @Log(title = "课程考试题库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysCcExaminationService.deleteSysCcExaminationByIds(ids));
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

    @GetMapping("/downLoad")
    @Anonymous
    public void downLoad(@RequestParam(value = "id") String id, HttpServletRequest request, HttpServletResponse response) {
        //获取视频文件流
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            SysCcExamination sysCcExamination = sysCcExaminationService.selectSysCcExaminationById(Long.parseLong(id));
            String path = sysCcExamination.getCcExaminationPath();
            String fileName = sysCcExamination.getCcExaminationPathName();
            String fileExt = path.substring(path.lastIndexOf(".") + 1)
                    .toLowerCase();
            outputStream = response.getOutputStream();
            fileInputStream = new FileInputStream(new File(path));
            byte[] cache = new byte[1024];
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/msword");
            response.setHeader(HttpHeaders.CONTENT_LENGTH, fileInputStream.available()+"");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode((fileName+"."+fileExt),"UTF-8"));
            int flag;
            while ((flag = fileInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, flag);
            }
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            //log.error("文件传输错误", e);
            e.printStackTrace();
            throw new RuntimeException("文件传输错误");
        } finally{
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    // log.error("流释放错误", e);
                }
            }
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    //log.error("文件流释放错误", e);
                }
            }
        }
    }
}
