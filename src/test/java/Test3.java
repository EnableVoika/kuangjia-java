import com.voika.myundefined.infrastructure.entity.po.BasePO;
import com.voika.myundefined.infrastructure.utils.StringUtil;
import com.voika.myundefined.infrastructure.utils.UrlUtil;

import java.util.HashMap;
import java.util.Map;

public class Test3 {

    public static void main(String[] args) {
        SonPO sonPO = new SonPO().create();
        System.out.println(sonPO);
    }

}

class SonPO extends BasePO {
    private String name;
    private Integer age;
}