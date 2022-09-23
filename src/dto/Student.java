package dto;

import java.util.List;

public class Student {

    private String id;
    private String pwd;
    private int stuId;
    private String stuName;
    private String stuMajor;
    private List<Lecture> myLecture;

    public Student(){

    }

    public Student(String id, String pwd, int stuId, String stuName, String stuMajor, List<Lecture> myLecture) {
        this.id = id;
        this.pwd = pwd;
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuMajor = stuMajor;
        this.myLecture = myLecture;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
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

    public List<Lecture> getMyLecture() {
        return myLecture;
    }

    public void setMyLecture(List<Lecture> myLecture) {
        this.myLecture = myLecture;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", pwd=" + pwd +
                ", stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuMajor='" + stuMajor + '\'' +
                ", myLecture=" + myLecture +
                '}';
    }
}
