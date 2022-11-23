package com.voika.myundefined.interfaces.controller.open;

import cn.hutool.core.date.DateUtil;
import com.voika.myundefined.infrastructure.client.redis.IRedis;
import com.voika.myundefined.infrastructure.utils.JwtUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @RequestMapping
    public Map<String,Object> test(@RequestBody Body body) {

        Map<String, Object> resp = new HashMap<>();
        try {
            String birth = body.getBirth();
            DateUtil.parseLocalDateTime(birth,"yyyy-MM-dd HH:mm:ss");
            System.out.println(birth);
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
@Data
class Body {
    private String birth;
}