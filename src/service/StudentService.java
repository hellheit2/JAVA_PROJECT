package service;

import dto.Lecture;
import dto.Student;
import dto.Time;
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

    // 수강 내역---------------------------------------------------------------------------
    public void printStudentLecture(List<Lecture> myLecture){

        if(myLecture.isEmpty()){
            System.out.println("수강 중인 강의가 없습니다.");
        }else{
            IOUtil.INSTANCE.printLectureList(myLecture);
        }
    }

    // 수강신청 ----------------------------------------------------------------------------------------
    public void addStudentLecture(Student student, int index) throws LectureDuplicationException {

        Lecture lecture = LectureManager.INSTANCE.getLectureByIndex(index);

        if(isMyLecture(student,lecture.getName()) == false){
            if(isTimeAvailable(student,lecture) == true) {
                student.getMyLecture().add(lecture);
                System.out.println("강의 신청이 완료되었습니다.");
            }else{
                System.out.println("해당 시간에 이미 수업이 있습니다.");
            }
        }else{
            throw new LectureDuplicationException("이미 존재하는 수업 입니다.");
        }
    }
    public boolean isTimeAvailable(Student student, Lecture lecture){
        List<Lecture> stuLec = student.getMyLecture();
        List<Time> lecTime = null;

        List<Time> newTime = lecture.getTime();

        for(Time chkTime : newTime){

            for(Lecture tempLec : stuLec){

                lecTime = tempLec.getTime();
                for(Time tempTime : lecTime) {
                    if (isSameDay(tempTime,chkTime)
                            && timeIncluded(tempTime,chkTime))
                        return false;
                }
            }
        }
        return true;
    }
    public boolean isSameDay(Time time, Time chkTime){
        return time.getDay().equals(chkTime.getDay());
    }
    public boolean timeIncluded(Time time, Time chkTime){
        return (time.getStartTime() < chkTime.getEndTime())
                && (time.getEndTime() > chkTime.getStartTime());
    }


    // 수강 취소 ----------------------------------------------------------------------------------------
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

    // 시간표 확인 ----------------------------------------------------------------------
    public String makeSchedule(){
        String top = "┌─────┬───────┬───────┬───────┬───────┬───────┐";
        String mid = "├─────┼───────┼───────┼───────┼───────┼───────┤";
        String end = "└─────┴───────┴───────┴───────┴───────┴───────┘";
        System.out.println("┌─────┬───────┬───────┬───────┬───────┬───────┐");
        System.out.println("|     |  MON  |  TUE  |  WED  |  THU  |  FRI  |");
        System.out.println("├─────┼───────┼───────┼───────┼───────┼───────┤");
        System.out.println("|  1  |   A   |   B   |   C   |   D   |   E   |");
        System.out.println("├─────┼───────┼───────┼───────┼───────┼───────┤");
        System.out.println("|  2  |       |   B   |   C   |   D   |   E   |");
        System.out.println("├─────┼───────┼───────┼───────┼───────┼───────┤");
        System.out.println("|  3  |       |   B   |   C   |   D   |   E   |");
        System.out.println("├─────┼───────┼───────┼───────┼───────┼───────┤");
        System.out.println("|  4  |   C   |   B   |   C   |   D   |   E   |");
        System.out.println("├─────┼───────┼───────┼───────┼───────┼───────┤");
        System.out.println("|  5  |       |   B   |   C   |   D   |   E   |");
        System.out.println("├─────┼───────┼───────┼───────┼───────┼───────┤");
        System.out.println("|  6  |   D   |   B   |   C   |   D   |   E   |");
        System.out.println("├─────┼───────┼───────┼───────┼───────┼───────┤");
        System.out.println("|  7  |   B   |   B   |   C   |   D   |   E   |");
        System.out.println("└─────┴───────┴───────┴───────┴───────┴───────┘");

        return top;
    }


}
