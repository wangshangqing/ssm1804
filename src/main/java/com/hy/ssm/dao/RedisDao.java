package com.hy.ssm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Auther: wangsq
 * @Date: 2019/12/31 10:51
 * @Description:
 */
@Repository
public class RedisDao {
    @Autowired
    private RedisTemplate redisTemplate;

    public void saveoRupdateString(String k,Object v){
        redisTemplate.opsForValue().set(k,v);
    }

    public Object getKey(String k){
        return redisTemplate.opsForValue().get(k);
    }
}
