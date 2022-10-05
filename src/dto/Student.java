package dto;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String stuId;
    private String stuPwd;
    private String stuNo;
    private String stuName;
    private String stuMajor;
    private List<Lecture> myLecture = new ArrayList<>();

    public Student(){

    }

    public Student(String id, String pwd, String stuId, String stuName, String stuMajor, List<Lecture> myLecture) {
        this.stuId = id;
        this.stuPwd = pwd;
        this.stuNo = stuId;
        this.stuName = stuName;
        this.stuMajor = stuMajor;
        this.myLecture = myLecture;
    }

    public String getStuId() {
        return stuId;
    }

    public String getStuPwd() {
        return stuPwd;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setStuPwd(String stuPwd) {
        this.stuPwd = stuPwd;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
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
        result += String.format("%-12s", stuNo).replace(" "," ");
        result += String.format("%-10s",stuName).replace(" "," ");
        result += String.format("%-10s",stuMajor).replace(" "," ");


        return result;
    }
}
