package com.voika.myundefined.interfaces.controller.open;

import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.email.MailClient;
import com.voika.myundefined.infrastructure.entity.email.SendEmailDO;
import com.voika.myundefined.infrastructure.exception.BusinessException;
import com.voika.myundefined.infrastructure.jwt.IJwt;
import com.voika.myundefined.infrastructure.utils.JwtUtil;
import com.voika.myundefined.infrastructure.redis.IRedis;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public JsonData test() {
        try {
            A a = new A();
            a.setKey1("2");
            a.setKey2(null);
            return JsonData.success(a);
        } catch (BusinessException e) {
            int code = null == e.getCode() ? 1 : e.getCode();
            return JsonData.error(e.getMessage(), code);
        } catch (Exception e) {
            String msg = "测试时出现异常";
            log.error(msg, e);
            return JsonData.error(msg);
        }
    }

    private void func() {
        try {
            // core
            throw new BusinessException("哎呀～网络开小差了");
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            log.error("出现异常{}", e);
        }
    }

}
@Data
class A {
    private String key1;
    private String key2;
}
