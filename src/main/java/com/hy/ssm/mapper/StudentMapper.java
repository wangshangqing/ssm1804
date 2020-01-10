package com.hy.ssm.mapper;

import com.hy.ssm.bean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: wangsq
 * @Date: 2019/11/27 15:33
 * @Description:
 */
@Mapper
public interface StudentMapper {
    @Select("select * from student")
    public List<Student> queryAll();

    @SelectKey(keyProperty = "stuNo",statement = "select uuid()",before = true,resultType = String.class)
    @Insert("insert into student(stuno,stuname,sex) values(#{stuNo},#{stuName},#{sex})")
    public void insert(Student student);

    @Delete("delete from student where stuno=#{value}")
    public void delete(String id);
}
