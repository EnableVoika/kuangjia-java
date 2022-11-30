import com.voika.myundefined.infrastructure.utils.UrlUtil;

public class Test2 {

    public static void main(String[] args) {
        String s = UrlUtil.resolveClassPath("classpath");
        System.out.println(s);
    }
}
