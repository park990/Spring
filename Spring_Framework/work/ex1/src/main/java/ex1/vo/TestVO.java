package ex1.vo;

public class TestVO {
    public TestVO() {
        System.out.println("testvo Construct");
    }

    private String msg; // 외부에서는 property개념이 멤버변수다


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
