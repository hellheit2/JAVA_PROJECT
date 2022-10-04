package dto;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String id;
    private String pwd;
    private int stuId;
    private String stuName;
    private String stuMajor;
    private List<Lecture> myLecture = new ArrayList<>();

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
        String lecture = "";

        for(Lecture temp : myLecture){
            lecture += (temp.getName() + ", ");
        }
        lecture = lecture.substring(0,lecture.length()-2);


        return "학번 : " + stuId +
                ", 성명 : " + stuName +
                ", 전공 : " + stuMajor +
                ", 강의 목록 : " + lecture;
    }
}
