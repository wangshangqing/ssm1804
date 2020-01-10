package com.hy.ssm.controller;

import com.hy.ssm.LayUiData;
import com.hy.ssm.UploadBean;
import com.hy.ssm.bean.Classes;
import com.hy.ssm.bean.Student;
import com.hy.ssm.bo.StudentBo;
import com.hy.ssm.service.IClassesService;
import com.hy.ssm.service.IRedisService;
import com.hy.ssm.service.IStudentService;
import io.swagger.annotations.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: wangsq
 * @Date: 2019/11/29 10:47
 * @Description:
 */
@Controller
@RequestMapping("student")
@Api("学生管理的相关接口")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassesService classesService;
    @Autowired
    private IRedisService redisService;

    @RequestMapping(value = "/queryAll.do",method = RequestMethod.POST)
    @ApiOperation(httpMethod = "post",value = "根据学生的信息进行分页模糊查询")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功！",response = LayUiData.class),
            @ApiResponse(code = 500,message = "查询失败！")
    })
    public LayUiData queryAll(@ApiParam(required = true,name = "page",value = "当前第几页") @RequestParam Integer page, @ApiParam(required = true,name = "limit",value = "一页几条")@RequestParam Integer limit,@ApiParam(required = false,name = "student",value = "根据学生的基本信息进行模糊查询") Student student){
        System.out.println("-----");
        return studentService.queryAll(page,limit,student);
    }

    @RequestMapping("/queryCla.do")
    @ResponseBody
    public List<Classes> queryCla(){
        return classesService.list(null);
    }


    @RequestMapping("/toAdd.do")
    public String toAdd(Student student,Model model){
        model.addAttribute("list_cla",classesService.list(null));
        return "add";
    }

    @RequestMapping("/add.do")
    @ResponseBody
    public String add(Student student){
        String res="0";
        try {
            studentService.updateById(student);
        }catch (Exception e){
            res="1";
        }
        return res;
    }

    @RequestMapping("/add1.do")
    @ResponseBody
    public String add1(Student student){
        String res="0";
        try {
            studentService.save(student);
        }catch (Exception e){
            res="1";
        }
        return res;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public String delete(String id){
        String res="0";
        try {
            studentService.removeById(id);
        }catch (Exception e){
            res="1";
        }
        return res;
    }

    @RequestMapping("/toUpdate.do")
    public String toUpdate(String id,Model model){
        model.addAttribute("stu",studentService.getById(id));
        return "update";
    }

    /**
     * 批量删除
     * @return
     */
    @RequestMapping("/moreDel.do")
    @ResponseBody
    public String moreDel(String ids){
        studentService.removeByIds(Arrays.asList(ids.split(",")));
        return "0";
    }

    @RequestMapping("/fileupload.do")
    @ResponseBody
    public UploadBean fileupload(@RequestParam("file") MultipartFile pictureFile, HttpServletRequest req){
        UploadBean uploadBean=new UploadBean();
        uploadBean.setCode("0");

        // 图片上传
        // 设置图片名称，不能重复，可以使用uuid
        String picName = UUID.randomUUID().toString().replaceAll("-","");
        // 获取文件名
        String oriName = pictureFile.getOriginalFilename();
        // 获取图片后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        try {
            // 开始上传
            System.out.println("===:"+req.getSession().getServletContext().getRealPath("/"));
            String p=req.getSession().getServletContext().getRealPath("/");

            pictureFile.transferTo(new File(p+"\\upload\\"+picName + extName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        uploadBean.setFileName(picName+extName);
        return uploadBean;
    }


    @RequestMapping("/toExecl.do")
    public String toExecl(){
        return "importexecl";
    }


    @RequestMapping("/importExcel.do")
    public String importExcel(@RequestParam("file") MultipartFile pictureFile){
        try {
            List<Student> list=studentService.importExecl(pictureFile.getInputStream());
            studentService.saveBatch(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "importexecl";
    }

    @RequestMapping("/exportExcel.do")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=test.xls");
        response.setContentType("application/x-excel;charset=utf-8");
        Workbook workbook=studentService.exportExecl();
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/testRedis.do")
    @ResponseBody
    public void  testRedis(String k,String v){
        StudentBo studentBo=new StudentBo();
        studentBo.setStuName("马云");
        redisService.saveOrUpdate(k,studentBo);
    }

    @RequestMapping("/testRedisGet.do")
    @ResponseBody
    public Object  testRedisGet(String k){
       return redisService.getKeyString(k);
    }

    @RequestMapping("/queryById.do")
    @ResponseBody
    public Student queryById(String id){
        return studentService.queryStuById(id);
    }


    @RequestMapping("/updateStu.do")
    @ResponseBody
    public Student updateStu(){
        Student student=new Student();
        student.setStuNo("1");
        student.setStuName("李四");
        return studentService.updateStu(student);
    }

    @RequestMapping("/del.do")
    @ResponseBody
    public void del(String id){
         studentService.deleteStu(id);
    }

}
