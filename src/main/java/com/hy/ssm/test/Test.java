package com.hy.ssm.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.ssm.mapper.StudentMapper1;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: wangsq
 * @Date: 2019/11/27 15:45
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring.xml");
        StudentMapper1 studentMapper1=context.getBean(StudentMapper1.class);
        IPage page=new Page(1,5);
        studentMapper1.queryAll(page,null);

        studentMapper1.queryAll(page,null);
    }
}
