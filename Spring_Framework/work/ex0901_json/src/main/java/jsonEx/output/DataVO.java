package jsonEx.output;

public class DataVO {
    private String ename, email;

    public DataVO(String ename, String email) {
        this.ename = ename;
        this.email = email;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
