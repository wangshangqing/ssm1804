package com.hy.ssm.sqlprovider;

import com.hy.ssm.bean.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

/**
 * @Auther: wangsq
 * @Date: 2019/12/18 09:36
 * @Description:
 */
public class StudentSql {
    public String queryAll(@Param("stu") Student student){
        StringBuffer sql=new StringBuffer("select * from student s left join classes c on s.classno=c.cno where 1=1");
        if(!StringUtils.isEmpty(student) && !StringUtils.isEmpty(student.getStuName())){
            sql.append(" and stuname like '%"+student.getStuName()+"%'");
        }
        if(!StringUtils.isEmpty(student) && !StringUtils.isEmpty(student.getSex()) && !"-1".equals(student.getSex())){
            sql.append(" and sex="+student.getSex());
        }
        if(!StringUtils.isEmpty(student) && !StringUtils.isEmpty(student.getClassno()) && student.getClassno()!=-1){
            sql.append(" and classno="+student.getClassno());
        }
        return sql.toString();
    }
}
