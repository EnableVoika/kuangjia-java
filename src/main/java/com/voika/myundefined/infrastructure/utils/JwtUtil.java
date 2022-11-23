package com.voika.myundefined.infrastructure.utils;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * 生成token时，没有传过期时间，默认是两天
 */
public class JwtUtil {

    /**
     * 如果是放入springboot项目，可以解开该注解
     */
//    @Value("${my-config.jwt.secrity-key}")
//    private String secret;
    private String secret = "myundefined.myproject.RytlockBrimstone.4jiIheuih238hf83BYBfbiuyNWePJwt";

//    private final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public JwtUtil() {
    }


    /**
     * 有效期，单位秒
     * - 默认2周
     * 如果是放入springboot项目，可以解开该注解
     */
//    @Value("${my-config.jwt.expire}")
//    private String expirationTimeInSecond;
    private String expirationTimeInSecond = "172800";

    /**
     * 解析token
     * 从token中获取claim
     * 解析失败这个方法会抛出异常
     *
     * @return
     */
    public Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(this.secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims parse(String token, Class clazz) {
        return null;
    }

    /**
     * 获取token到期的时间时间
     * token几号、多久到期
     * @param token token
     * @return 过期的那一刻
     */
    public Date getExpir(String token) {
        return Jwts.parser()
                .setSigningKey(this.secret.getBytes())
                .parseClaimsJws(token)
                .getBody().getExpiration();
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    public Boolean isExpired(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(this.secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
        return false;
    }

    /**
     * 计算token的过期时间
     * 用于生成token时确定截止日期,作为参数传给创建token的方法
     * @return 过期时间
     */
    private Date calculationExpirTime(long expire) {
        return new Date(System.currentTimeMillis() + expire * 1000);
    }

    /**
     * 为指定用户生成token
     *
     * @param claims 用户信息
     * @return token
     */
    public String generateToken(Map<String, Object> claims) {
        return generateToken(claims, Long.valueOf(expirationTimeInSecond));
    }

    public String generateToken(Map<String, Object> claims, long expire) {
        Date expirationTime = this.calculationExpirTime(expire);
        byte[] keyBytes = secret.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expirationTime)
                // 可以改成自定义的算法
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * @param claims json对象
     * @param expire 过期时间
     * @return
     */
    public String generateToken(String claims, long expire) {
        Map map = JSONUtil.toBean(claims, Map.class);
        return generateToken(map, expire);
    }

    /**
     * @param claims json对象
     * @return
     */
    public String generateToken(String claims) {
        Map map = JSONUtil.toBean(claims, Map.class);
        return generateToken(map);
    }

    public String generateToken(Object obj) {
        return generateToken(obj, Long.valueOf(expirationTimeInSecond));
    }

    public String generateToken(Object obj, long expir) {
        String json = JSONUtil.toJsonStr(obj);
        Map jsonMap = JSONUtil.toBean(json, Map.class);
        return generateToken(jsonMap, expir);
    }

    /**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public void validateToken(String token) {
        Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(token);
    }

}
