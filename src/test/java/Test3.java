import com.voika.myundefined.infrastructure.utils.StringUtil;
import com.voika.myundefined.infrastructure.utils.UrlUtil;

import java.util.HashMap;
import java.util.Map;

public class Test3 {

    public static void main(String[] args) {
        String baseUrl = "http://www.baidu.com&t=${}&m=${}";
        String query = StringUtil.separatorStr(baseUrl, "{}", "2022", "query");
        System.out.println(query);
    }

}
