package com.voika.myundefined.infrastructure.jwt;

import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.Map;

/**
 * 过渡jwtutil的接口
 */
public interface IJwt {

    /**
     * 解析token
     * 从token中获取claim
     * 解析失败这个方法会抛出异常
     *
     * @return
     */
    Claims parse(String token);

    Claims parse(String token, Class clazz);

    /**
     * 获取token到期的时间时间
     * token几号、多久到期
     *
     * @param token token
     * @return 过期的那一刻
     */
    Date getExpir(String token);

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    Boolean isExpired(String token);

    /**
     * 为指定用户生成token
     *
     * @param claims 用户信息
     * @return token
     */
    String generateToken(Map<String, Object> claims);

    String generateToken(Map<String, Object> claims, long expire);

    /**
     * @param claims json对象
     * @param expire 过期时间
     * @return
     */
    String generateToken(String claims, long expire);

    /**
     * @param claims json对象
     * @return
     */
    String generateToken(String claims);

    String generateToken(Object obj);

    String generateToken(Object obj, long expir);

    /**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    void validateToken(String token);

}
