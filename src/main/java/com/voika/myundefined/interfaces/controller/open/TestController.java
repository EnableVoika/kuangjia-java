package com.voika.myundefined.interfaces.controller.open;

import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.email.MailClient;
import com.voika.myundefined.infrastructure.entity.email.SendEmailDO;
import com.voika.myundefined.infrastructure.exception.BusinessException;
import com.voika.myundefined.infrastructure.jwt.IJwt;
import com.voika.myundefined.infrastructure.utils.JwtUtil;
import com.voika.myundefined.infrastructure.redis.IRedis;
import lombok.extern.slf4j.Slf4j;
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
            // service
            System.out.println(jwt);
            System.out.println(redis);
            Map<String, Object> user = new HashMap<String, Object>(){{
                put("name","张三");
                put("age",18);
                put("sexy","男");
            }};
            String token = jwt.generateToken(user);
            user = jwt.parse(token);
            System.out.println(user);
            SendEmailDO sendEmailDO = new SendEmailDO();
            sendEmailDO.setFromUser("2721688374@qq.com");
            sendEmailDO.setToUser("965840507@qq.com");
            sendEmailDO.setSubject("测试邮件");
            sendEmailDO.setContent("这是一封测试邮件");
            mailClient.sendEmail(sendEmailDO);
//            func();
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
