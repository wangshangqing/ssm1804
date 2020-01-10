package com.hy.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.ssm.bean.Classes;
import com.hy.ssm.mapper.ClassesMapper;
import com.hy.ssm.service.IClassesService;
import org.springframework.stereotype.Service;

/**
 * @Auther: wangsq
 * @Date: 2019/12/18 10:37
 * @Description:
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper,Classes> implements IClassesService{
}
