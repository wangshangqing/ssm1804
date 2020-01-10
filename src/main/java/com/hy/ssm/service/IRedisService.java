package com.hy.ssm.service;

/**
 * @Auther: wangsq
 * @Date: 2019/12/31 10:57
 * @Description:
 */
public interface IRedisService {
    public void saveOrUpdate(String k,Object v);
    public Object getKeyString(String k);
}
