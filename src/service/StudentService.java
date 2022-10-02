package service;

import dao.StudentDAO;
import dto.Lecture;
import dto.Student;
import dto.Time;
import exception.LectureDuplicationException;
import exception.OutOfWeekdayException;
import exception.TimeConflictException;
import utility.InputUtil;
import utility.OutputUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    public StudentService(){
    }

    // 수강 내역---------------------------------------------------------------------------
    public static void printStudentLecture(List<Lecture> myLecture){

        if(myLecture.isEmpty()){
            OutputUtil.errorMessage("수강 중인 강의가 없습니다.");
        }else{
            OutputUtil.printLectureList(myLecture);
        }
    }


    // 수강신청 ----------------------------------------------------------------------------------------
    public static void addStudentLecture(Student student, Lecture lecture) throws LectureDuplicationException, TimeConflictException {

        if(isMyLecture(student,lecture.getName()) == false){
            if(timeConflictCheck(student,lecture) == true) {
                student.getMyLecture().add(lecture);
                OutputUtil.successMessage("강의 신청이 완료되었습니다.");
            }else{
                throw new TimeConflictException("해당 시간에 이미 수업이 있습니다.");
            }
        }else{
            throw new LectureDuplicationException("이미 존재하는 수업 입니다.");
        }
    }
    // 신청 가능한 시간인지 확인
    public static boolean timeConflictCheck(Student student, Lecture lecture){
        List<Lecture> stuLec = student.getMyLecture();
        List<Time> newTime = lecture.getTime();

        for(Time chkTime : newTime){

            for(Lecture tempLec : stuLec){

                List<Time> lecTime = tempLec.getTime();
                for(Time tempTime : lecTime) {
                    if(isTimeAvailable(tempTime,chkTime))
                        return false;
                }
            }
        }
        return true;
    }
    public static boolean isTimeAvailable(Time time1, Time time2){
        return isSameDay(time1,time2) && timeIncluded(time1,time2);
    }
    // 요일이 겹치는지 확인
    public static boolean isSameDay(Time time1, Time time2){
        return time1.getDay().equals(time2.getDay());
    }
    // 시간이 겹치는지 확인
    public static boolean timeIncluded(Time time1, Time time2){
        return (time1.getStartTime().before(time2.getEndTime()))
                && (time1.getEndTime().after(time2.getStartTime()));
    }

    // 수강 취소 ----------------------------------------------------------------------------------------
    public static void delStudentLecture(Student student, Lecture lecture) {
        OutputUtil.successMessage("수강 철회가 완료되었습니다.("+lecture.getName()+")");
        student.getMyLecture().remove(lecture);
    }
    public static boolean isMyLecture(Student student, String lecName){
        for (Lecture tempLec: student.getMyLecture()) {
            if(tempLec.getName().equals(lecName))
                return true;
        }
        return false;
    }

    // 시간표 확인 ----------------------------------------------------------------------
    public static void showSchedule(List<Lecture> lectureList) {


        String schedule[][] = new String[5][8];
        for (int i = 0; i < schedule.length; i++) {
            schedule[i] = new String[]{" "," "," "," "," "," "," "," "};
        }

        Map<String,String> lecMap = new HashMap<>();

        int n = 65;

        for(Lecture lecture : lectureList){
            String key = String.valueOf((char)(n++));
            lecMap.put(key, lecture.getName());
            for(Time time : lecture.getTime()){
                try {
                    int day = InputUtil.dayOfWeekToInt(time.getDay());
                    int start = InputUtil.timestampToHour(time.getStartTime());
                    int end = InputUtil.timestampToHour(time.getEndTime());
                    for (int period = start - 9; period < end - 9; period++) {
                        schedule[day][period] = key;
                    }
                }catch(OutOfWeekdayException e){
                    System.out.println(e.getMsg());
                    return;
                }
            }
        }

        for(String key : lecMap.keySet()) {
            String value = lecMap.get(key);
            System.out.println(key + " : " + value);
        }

        System.out.println("┌─────┬───────┬───────┬───────┬───────┬───────┐");
        System.out.println("|     |  MON  |  TUE  |  WED  |  THU  |  FRI  |");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            System.out.println("├─────┼───────┼───────┼───────┼───────┼───────┤");
            sb.setLength(0);
            sb.append("|  "+ (i + 1)+"  |");
            for (int j = 0; j < 5; j++) {
                sb.append("   " + schedule[j][i] + "   |");
            }
            System.out.println(sb);
        }
        System.out.println("└─────┴───────┴───────┴───────┴───────┴───────┘");

    }
}
