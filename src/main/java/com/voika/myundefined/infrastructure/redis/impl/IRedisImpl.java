package com.voika.myundefined.infrastructure.redis.impl;

import com.voika.myundefined.infrastructure.redis.IRedis;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class IRedisImpl implements IRedis {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, String val, long expir, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key,val,expir,timeUnit);
    }

    /**
     * 默认秒级
     * @param key
     * @param val
     * @param expir
     */
    @Override
    public void set(String key, String val, long expir) {
        set(key,val,expir,TimeUnit.SECONDS);
    }

    /**
     * 没有过期时间，不建议使用该方法
     * @param key
     * @param val
     */
    @Override
    public void set(String key, String val) {
        stringRedisTemplate.opsForValue().set(key,val);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
