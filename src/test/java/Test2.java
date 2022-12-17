import com.voika.myundefined.infrastructure.utils.UrlUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Test2 {

    public static void main(String[] args) {
        String s = UrlUtil.resolveClassPath("classpath");
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now(ZoneId.of("Z")));
    }
}
