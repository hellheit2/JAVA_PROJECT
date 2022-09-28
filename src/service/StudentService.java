package service;

import dto.Lecture;
import dto.Student;
import exception.LectureDuplicationException;
import view.MainView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentService {

    final static StudentService INSTANCE = new StudentService();

    public List<Student> stuList = new ArrayList<>();

    StudentService(){
        Student test = new Student();
        test.setId("test");
        test.setPwd("1234");
        test.setStuId(11111);
        test.setStuName("Kim");
        test.setStuMajor("컴퓨터공학과");
        stuList.add(test);

        printStudent(); // 저장된 학생 출력 테스트용
    }

    public void printStudent(){
        // 학생추가
        for(Student stuTemp: stuList){
            System.out.println(stuTemp.toString());
        }
    }


    //StudentService
    public Student getStudentByLoginInfo(Map<String, String> id_pwd){
        String id = id_pwd.get("id");
        String pwd = id_pwd.get("pwd");

        for(Student tempStu : stuList){

            String chkID = tempStu.getId();
            String chkPwd = tempStu.getPwd();

            if (chkID.equals(id) && chkPwd.equals(pwd)) {
                return tempStu;
            }
        }
        return null;
    }

    public void addStudentLecture(Student student, int index){

        Lecture lecture = LectureManager.INSTANCE.getLectureByIndex(index);

        if(isMyLecture(student,lecture.getName()) == false) {
            student.getMyLecture().add(lecture);
            System.out.println("강의 신청이 완료되었습니다.");
        }else{
            System.out.println("이미 수강 중인 강의 입니다.");
        }
    }

    public void delStudentLecture(Student student, int index) {

        Lecture lecture = LectureManager.INSTANCE.getLectureByIndex(index,student.getMyLecture());

        if(isMyLecture(student,lecture.getName()) == false) {
            System.out.println("올바른 강의명을 입력해주세요.");
        }else{
            student.getMyLecture().remove(lecture);
        }

    }
    public boolean isMyLecture(Student student, String lecName){
        for (Lecture tempLec: student.getMyLecture()) {
            if(tempLec.getName().equals(lecName))
                return true;
        }
        return false;
    }
    public void printStudentLecture(List<Lecture> myLecture){

        if(myLecture.isEmpty()){
            System.out.println("수강 중인 강의가 없습니다.");
        }else{
            IOUtil.INSTANCE.printLectureList(myLecture);
        }
    }

}
