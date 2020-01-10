package com.hy.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.ssm.LayUiData;
import com.hy.ssm.bean.Student;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

/**
 * @Auther: wangsq
 * @Date: 2019/12/5 11:24
 * @Description:
 */
public interface IStudentService extends IService<Student>{
    /**
     * 查询所有学生
     * @param student
     * @return
     */
    public LayUiData queryAll(Integer page,Integer limit,Student student);

    public List<Student> importExecl(InputStream in);

    public Workbook exportExecl();

    public Student queryStuById(String id);

    public Student updateStu(Student student);

    public void deleteStu(String id);

}
