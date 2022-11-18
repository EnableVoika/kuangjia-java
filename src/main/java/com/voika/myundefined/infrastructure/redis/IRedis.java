package com.voika.myundefined.infrastructure.redis;

import java.util.concurrent.TimeUnit;

public interface IRedis {

    /**
     *
     * @param key 设置 key
     * @param val value
     * @param expir 过期时间
     * @param timeUnit 过期时间单位
     */
    void set(String key, String val, long expir, TimeUnit timeUnit);
    void set(String key, String val, long expir);
    void set(String key, String val);

    String get(String key);

}
