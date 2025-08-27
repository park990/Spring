package ex1.vo;

public class Test3VO {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isIsmember() {
        return ismember;
    }

    public void setIsmember(boolean ismember) {
        this.ismember = ismember;
    }

    private String name;
    private int age;
    private boolean ismember;


    public Test3VO(String name, int age, boolean ismember) {
        this.ismember = ismember;
        this.name=name;
        this.age=age;
        System.out.println("name:"+name+" age:"+age+" ismember:"+ismember);
    }


}
