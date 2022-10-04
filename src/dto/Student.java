package dto;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String id;
    private String pwd;
    private String stuId;
    private String stuName;
    private String stuMajor;
    private List<Lecture> myLecture = new ArrayList<>();

    public Student(){

    }

    public Student(String id, String pwd, String stuId, String stuName, String stuMajor, List<Lecture> myLecture) {
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

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
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

        char c = 0x20;
        String result = "";
        result += String.format("%-12s",stuId).replace(" "," ");
        result += String.format("%-10s",stuName).replace(" "," ");
        result += String.format("%-10s",stuMajor).replace(" "," ");


        return result;
    }
}
