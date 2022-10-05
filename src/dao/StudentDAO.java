package dao;

import dto.Lecture;
import dto.Student;
import exception.LectureOutOfRangeException;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static List<Student> stuList = new ArrayList<>();
    private static Student selectStu;

    static{ //db 읽어오는거로 변경
        Student test = new Student();
        test.setStuId("test");
        test.setStuPwd("1234");
        test.setStuNo("11111111");
        test.setStuName("Kim");
        test.setStuMajor("컴퓨터공학과");
        stuList.add(test);
    }

    public StudentDAO() {
    }
    public static Student selectUserInfo(String stuId){
        for(Student temp : stuList){
            if (temp.getStuId().equals(stuId))
                return temp;
        }
        return null;
    }

    public static Lecture getStuLectureByIndex(int index) throws LectureOutOfRangeException {

        if(isRangeOfIndex(index)){
            return selectStu.getMyLecture().get(index - 1);
        }else{
            throw new LectureOutOfRangeException("올바른 강의 번호를 입력해주세요.");
        }
    }
    public static boolean isRangeOfIndex(int index) {
        if (index > selectStu.getMyLecture().size())
            return false;
        return true;
    }

    public static List<Student> getStuList() {
        return stuList;
    }

    public static void setStuList(List<Student> stuList) {
        StudentDAO.stuList = stuList;
    }

    public static Student getSelectStu() {
        return selectStu;
    }

    public static void setSelectStu(Student selectStu) {
        StudentDAO.selectStu = selectStu;
    }
}
