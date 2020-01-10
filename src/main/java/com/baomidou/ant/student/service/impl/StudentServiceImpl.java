package com.baomidou.ant.student.service.impl;

import com.baomidou.ant.student.entity.Student;
import com.baomidou.ant.student.mapper.StudentMapper;
import com.baomidou.ant.student.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-12-05
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
