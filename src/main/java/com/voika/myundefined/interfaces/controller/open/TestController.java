package com.voika.myundefined.interfaces.controller.open;

import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.client.KafkaClient;
import com.voika.myundefined.infrastructure.client.MailClient;
import com.voika.myundefined.infrastructure.exception.BusinessException;
import com.voika.myundefined.infrastructure.interfaces.IJwt;
import com.voika.myundefined.infrastructure.jwt.impl.IJwtImpl;
import com.voika.myundefined.infrastructure.redis.IRedis;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @Resource(name = "iRedisImpl")
    private IRedis redis;

    @Resource(name = "jwt")
    private IJwt jwt;

    @Resource(name = "kafkaClient")
    private KafkaClient kafkaClient;

    /**
     * 测试
     */
    @RequestMapping
    public JsonData test(HttpServletRequest request) {
        try {
            kafkaClient.sendMessage("test1","ooo1111");
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
