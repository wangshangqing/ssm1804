package com.hy.ssm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.ssm.LayUiData;
import com.hy.ssm.bean.Classes;
import com.hy.ssm.bean.Student;
import com.hy.ssm.bo.StudentBo;
import com.hy.ssm.mapper.ClassesMapper;
import com.hy.ssm.mapper.StudentMapper1;
import com.hy.ssm.service.IStudentService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wangsq
 * @Date: 2019/11/27 15:57
 * @Description:
 */
@Service
@Transactional
@EnableCaching
@CacheConfig(cacheNames = "stu")
public class StudentServiceImpl extends ServiceImpl<StudentMapper1,Student> implements IStudentService {
    @Autowired
    private StudentMapper1 studentMapper1;
    @Autowired
    private ClassesMapper classesMapper;

    /**
     * 查询所有学生
     * @param student
     * @return
     */
    @Override
    public LayUiData queryAll(Integer page,Integer limit,Student student) {
        IPage<StudentBo> p=new Page<StudentBo>(page,limit);
        List<StudentBo> list=studentMapper1.queryAll(p,student);
        p.setRecords(list);
        LayUiData layUiData=new LayUiData();
        layUiData.setCode(0);
        layUiData.setMsg("");
        layUiData.setCount(Integer.parseInt(String.valueOf(p.getTotal())));
        layUiData.setData(list);
        return layUiData;
    }

    @Override
    public List<Student> importExecl(InputStream in) {
        List<Student> list=new ArrayList<>();
        try {
            Workbook workbook= WorkbookFactory.create(in);
            Sheet sheet=workbook.getSheetAt(0);
            int num=sheet.getLastRowNum();
            for (int i = 1; i <=num ; i++) {
                Student student=new Student();
                Row row=sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell=row.getCell(j);
                    String val="";
                    if(cell.getCellType()==CellType.NUMERIC){
                        val=String.valueOf(cell.getNumericCellValue());
                    }else if(cell.getCellType()==CellType.STRING){
                        val=cell.getStringCellValue();
                    }

                    if(j==0){
                        student.setStuName(val);
                    }else if(j==1){
                        student.setSex(val);
                    }else{
                        System.out.println(val+"=====");
                        student.setClassno(Integer.parseInt(val.split(".")[0]));
                    }
                }
                list.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Workbook exportExecl() {
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet=workbook.createSheet("学生基本信息");

        List<Student> list=studentMapper1.selectList(null);
        Row row=sheet.createRow(0);
        Cell cell1=row.createCell(0);
        cell1.setCellValue("姓名");
        Cell cell2=row.createCell(1);
        cell2.setCellValue("性别");
        Cell cell3=row.createCell(2);
        cell3.setCellValue("班级");

        for (int i = 0; i <list.size() ; i++) {
            Row row1=sheet.createRow(i+1);
            Cell cell4=row1.createCell(0);
            cell4.setCellValue(list.get(i).getStuName());
            Cell cell5=row1.createCell(1);
            cell5.setCellValue(list.get(i).getSex());
            Cell cell6=row1.createCell(2);
            cell6.setCellValue(list.get(i).getClassno());
        }
        return workbook;
    }

    public void add(Student student, Classes classes){
        studentMapper1.insert(student);
        classesMapper.insert(classes);
    }

    @Override
    @Cacheable(key = "'stu_'+#id")
    public Student queryStuById(String id){
        return studentMapper1.selectById(id);
    }

    @Override
    @CachePut(key = "'stu_'+#student.stuNo")
    public Student updateStu(Student student){
        studentMapper1.updateById(student);
        return studentMapper1.selectById(student.getStuNo());
    }

    @Override
    @CacheEvict(key ="'stu_'+#id")
    public void deleteStu(String id){
        studentMapper1.deleteById(id);
    }
}
