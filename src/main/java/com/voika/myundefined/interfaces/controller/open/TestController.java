package com.voika.myundefined.interfaces.controller.open;

import cn.hutool.core.net.url.UrlBuilder;
import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.email.MailClient;
import com.voika.myundefined.infrastructure.entity.email.SendEmailDO;
import com.voika.myundefined.infrastructure.exception.BusinessException;
import com.voika.myundefined.infrastructure.jwt.IJwt;
import com.voika.myundefined.infrastructure.utils.JwtUtil;
import com.voika.myundefined.infrastructure.redis.IRedis;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    //    @Resource
    private JwtUtil jwtUtil;

    @Resource(name = "iRedisImpl")
    private IRedis redis;

    @Resource(name = "jwt")
    private IJwt jwt;

    @Resource
    private MailClient mailClient;

    /**
     * 测试
     */
    @RequestMapping
    public JsonData test(HttpServletRequest request) {
        try {
            return JsonData.success();
        } catch (BusinessException e) {
            int code = null == e.getCode() ? 1 : e.getCode();
            return JsonData.error(e.getMessage(), code);
        } catch (Exception e) {
            String msg = "测试时出现异常";
            log.error(msg, e);
            return JsonData.error(msg);
        }
    }





}
@Data
class A {
    private String key1;
    private String key2;
    private Date key3;
}
