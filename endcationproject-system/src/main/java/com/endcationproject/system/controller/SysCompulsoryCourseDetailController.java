package com.endcationproject.system.controller;

import java.io.*;
import java.util.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.endcationproject.common.annotation.Anonymous;
import com.endcationproject.common.utils.SecurityUtils;
import com.endcationproject.system.domain.vo.TreeVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.endcationproject.common.annotation.Log;
import com.endcationproject.common.core.controller.BaseController;
import com.endcationproject.common.core.domain.AjaxResult;
import com.endcationproject.common.enums.BusinessType;
import com.endcationproject.system.domain.SysCompulsoryCourseDetail;
import com.endcationproject.system.service.ISysCompulsoryCourseDetailService;
import com.endcationproject.common.utils.poi.ExcelUtil;
import com.endcationproject.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程详情Controller
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/system/detail")
public class SysCompulsoryCourseDetailController extends BaseController {
    @Autowired
    private ISysCompulsoryCourseDetailService sysCompulsoryCourseDetailService;

    /**
     * 查询必修课程详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        startPage();
        sysCompulsoryCourseDetail.setFlag("0");
        List<SysCompulsoryCourseDetail> list = sysCompulsoryCourseDetailService.selectSysCompulsoryCourseDetailList(sysCompulsoryCourseDetail);
        return getDataTable(list);
    }

    /**
     * 查询选修课程详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping("/list1")
    public TableDataInfo list1(SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        startPage();
        sysCompulsoryCourseDetail.setFlag("1");
        List<SysCompulsoryCourseDetail> list = sysCompulsoryCourseDetailService.selectSysCompulsoryCourseDetailList(sysCompulsoryCourseDetail);
        return getDataTable(list);
    }

    /**
     * 查询学生已选课程详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping("/list2")
    public TableDataInfo list2(SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        //根据学生id，查询已经选的课程
        Map<String, Object> result = sysCompulsoryCourseDetailService.getStuCourses(SecurityUtils.getUserId());
        startPage();
        List<TreeVo> list = new ArrayList<>();
        if (result != null) {
            Object ccIds = result.get("ccIds");
            String s = String.valueOf(ccIds);
            String[] split = s.split(",");
            list = sysCompulsoryCourseDetailService.list2_1(Arrays.asList(split));
        }
        return getDataTable(list);
    }

    /**
     * 查询必修课程下拉
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping("/pullDownCourse")
    public AjaxResult pullDownCourse() {
        List<Map<String, Object>> result = sysCompulsoryCourseDetailService.pullDownCourse("0");
        return success(result);
    }

    /**
     * 查询选修课程下拉
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping("/pullDownCourse1")
    public AjaxResult pullDownCourse1() {
        List<Map<String, Object>> result = sysCompulsoryCourseDetailService.pullDownCourse("1");
        return success(result);
    }

    /**
     * 查询全部课程下拉
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping("/pullDownCourse2")
    public AjaxResult pullDownCourse2() {
        List<Map<String, Object>> result = sysCompulsoryCourseDetailService.pullDownCourse(null);
        return success(result);
    }

    /**
     * 导出课程详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @Log(title = "课程详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        List<SysCompulsoryCourseDetail> list = sysCompulsoryCourseDetailService.selectSysCompulsoryCourseDetailList(sysCompulsoryCourseDetail);
        ExcelUtil<SysCompulsoryCourseDetail> util = new ExcelUtil<SysCompulsoryCourseDetail>(SysCompulsoryCourseDetail.class);
        util.exportExcel(response, list, "课程详情数据");
    }

    /**
     * 获取课程详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysCompulsoryCourseDetailService.selectSysCompulsoryCourseDetailById(id));
    }

    /**
     * 新增课程详情
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "课程详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        return toAjax(sysCompulsoryCourseDetailService.insertSysCompulsoryCourseDetail(sysCompulsoryCourseDetail));
    }

    /**
     * 修改课程详情
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "课程详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        return toAjax(sysCompulsoryCourseDetailService.updateSysCompulsoryCourseDetail(sysCompulsoryCourseDetail));
    }

    /**
     * 删除课程详情
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "课程详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysCompulsoryCourseDetailService.deleteSysCompulsoryCourseDetailByIds(ids));
    }



    //@PreAuthorize("@ss.hasPermi('system:detail:edit')")
   // @Log(title = "课程详情", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/uploadVidoe3")
    @Anonymous
    public Map<String,String> savaVideotest(@RequestParam("file") List<MultipartFile> files,@RequestParam("id") String id)
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

    //根据id删除文件地址
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping("/detailPathById")
    public AjaxResult detailPathById(SysCompulsoryCourseDetail sysCompulsoryCourseDetail) {
        sysCompulsoryCourseDetail.setCcdDataPath("");
        sysCompulsoryCourseDetailService.updatePathById(sysCompulsoryCourseDetail);
        return success("删除地址成功");
    }
/*
    @GetMapping("/output/{classId}/{checkName}")
    public void checkToExcel(@PathVariable String checkName, @PathVariable String classId, HttpServletResponse response) {
        HSSFWorkbook checkInfo = checkExcelService.getCheckInfo(classId, checkName);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + checkName + "签到统计表.xls");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            checkInfo.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    /**
     * 在线预览
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("/downLoad")
    @Anonymous
    public void downLoad(@RequestParam(value = "id") String id, HttpServletRequest request, HttpServletResponse response) {
        //获取视频文件流
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            String path = sysCompulsoryCourseDetailService.selectSysCompulsoryCourseDetailById(Long.parseLong(id)).getCcdDataPath();
            outputStream = response.getOutputStream();
            fileInputStream = new FileInputStream(new File(path));
            byte[] cache = new byte[1024];
            response.setHeader(HttpHeaders.CONTENT_TYPE, "video/mp4");
            response.setHeader(HttpHeaders.CONTENT_LENGTH, fileInputStream.available()+"");
            int flag;
            while ((flag = fileInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, flag);
            }
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            //log.error("文件传输错误", e);
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

    //@GetMapping("/download/{path}")
    public void download(@PathVariable("path") String path ,
                         HttpServletResponse response){
        File file = new File("C:\\Users\\Kaven\\Desktop\\images\\" + path);
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //文件是否存在
            if (file.exists()) {
                //设置响应
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while(bis.read(buffer) != -1){
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bis != null) {
                    bis.close();
                }
                if(os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
