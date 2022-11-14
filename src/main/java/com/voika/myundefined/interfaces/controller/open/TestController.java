package com.voika.myundefined.interfaces.controller.open;

import com.voika.myundefined.infrastructure.requestdata.RequestData;
import com.voika.myundefined.infrastructure.requestdata.TokenUser;
import com.voika.myundefined.infrastructure.utils.JwtUtil;
import com.voika.myundefined.infrastructure.client.redis.IRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

//    @Resource
    private JwtUtil jwtUtil;

    @Resource(name = "iRedisImpl")
    private IRedis redis;

    @RequestMapping
    public Map<String,Object> test(String parm) {

        Map<String, Object> resp = new HashMap<>();

        TokenUser tokenUser = RequestData.TokenUser;
        resp.put("parm",parm);
        resp.put("code",200);
        resp.put("data",tokenUser);
        resp.put("ref",true);
        resp.put("msg","测试接口通畅");
        try {
            redis.set("username","测试张三",20, TimeUnit.SECONDS);
            String username = redis.get("username");
            System.out.println(username);
            String s = jwtUtil.generateToken(resp);
            System.out.println(s);
        }catch (Exception e) {
            log.error("",e);
            resp.put("code",500);
            resp.put("msg","出现异常了");
            resp.put("ref",false);
            return resp;
        }
        return resp;
    }

}
