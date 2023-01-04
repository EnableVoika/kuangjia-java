package com.voika.myundefined.interfaces.controller.open;

import cn.hutool.json.JSONUtil;
import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.entity.kafka.KaMessageDO;
import com.voika.myundefined.infrastructure.exception.BusinessException;
import com.voika.myundefined.infrastructure.interfaces.IHttpClient;
import com.voika.myundefined.infrastructure.interfaces.IJwt;
import com.voika.myundefined.infrastructure.kafka.KafkaClient;
import com.voika.myundefined.infrastructure.redis.IRedis;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @Resource(name = "iHttpClientImpl")
    private IHttpClient httpClient;

    @Value("${sms.host}")
    private String smsHost;

    /**
     * 测试
     */
    @RequestMapping
    public JsonData test(@RequestBody(required = false) KaMessageDO<String> message, HttpServletRequest request) {
        try {
            StringBuffer buffer = new StringBuffer("https://").append(smsHost);
            Map<String, String> headers = new HashMap<String, String>(){{
                put("Content-Type","application/json");
            }};
            Map<String, Object> params = new HashMap<String, Object>(){{
                put("Action","SendSms");
                put("Version","2021-01-11");
                put("Region","ap-beijing");
                List<String> mobile = new ArrayList<String>(){{
                    add("+8615628972290");
                }};
                put("PhoneNumberSet",mobile);
                put("SmsSdkAppId","1400774213");
                put("TemplateId","1627838");
                put("SignName","PayFailedException");
            }};
            String s = JSONUtil.toJsonStr(params);
            String respJson = httpClient.postMethod(buffer.toString(), headers, s);
            Map map = JSONUtil.toBean(respJson, Map.class);
            return JsonData.success(map);
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
