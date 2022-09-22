package test;

public class Student {

    private String id;
    private int pwd;
    private int stuId;
    private String stuName;
    private String stuMajor;
    private Schedule schedule;

    public Student(){

    }

    public String getId() {
        return id;
    }

    public int getPwd() {
        return pwd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(int pwd) {
        this.pwd = pwd;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
