package com.hy.ssm.cache;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @Auther: wangsq
 * @Date: 2020/1/3 14:25
 * @Description:
 */
public class RedisRef {
    private JedisConnectionFactory jedisConnectionFactory;

    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }
}
