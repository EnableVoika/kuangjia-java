import com.voika.myundefined.infrastructure.entity.po.BasePO;

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