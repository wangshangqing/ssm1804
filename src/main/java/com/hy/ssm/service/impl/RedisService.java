package com.hy.ssm.service.impl;

import com.hy.ssm.dao.RedisDao;
import com.hy.ssm.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: wangsq
 * @Date: 2019/12/31 10:57
 * @Description:
 */
@Service
public class RedisService implements IRedisService{
    @Autowired
    private RedisDao redisDao;

    @Override
    public void saveOrUpdate(String k, Object v) {
        redisDao.saveoRupdateString(k,v);
    }

    @Override
    public Object getKeyString(String k) {
        return redisDao.getKey(k);
    }
}
