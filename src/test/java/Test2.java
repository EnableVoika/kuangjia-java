import cn.hutool.core.bean.BeanUtil;
import com.voika.myundefined.infrastructure.utils.JwtUtil;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

public class Test2 {

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> user = new HashMap<String, Object>() {{
            put("name", "张三");
            put("age", 22);
            put("sex", "男");
        }};
        String token = jwtUtil.generateToken(user, 20);
        System.out.println(token);
        test2();
    }

    public static void test2() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzZXgiOiLnlLciLCJuYW1lIjoi5byg5LiJIiwiYWdlIjoyMiwiaWF0IjoxNjY4NDIxMDI4LCJleHAiOjE2Njg0MjEwNDh9.z9xPes6xwidxrMchF-ho17rkQflqOaEhRSz1Q2Wl25c";
        JwtUtil jwtUtil = new JwtUtil();
//        Claims parse = jwtUtil.parse(token);
        jwtUtil.validateToken(token);
//        Map map = BeanUtil.copyProperties(parse, Map.class);
//        System.out.println(map);
    }

}
