package com.hy.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.ssm.bean.Student;
import com.hy.ssm.bo.StudentBo;
import com.hy.ssm.cache.RedisCache;
import com.hy.ssm.sqlprovider.StudentSql;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Auther: wangsq
 * @Date: 2019/12/3 11:22
 * @Description:
 */
@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface StudentMapper1 extends BaseMapper<Student>{
    @SelectProvider(type = StudentSql.class,method = "queryAll")
    public List<StudentBo> queryAll(IPage<StudentBo> page, @Param("stu") Student student);
}
