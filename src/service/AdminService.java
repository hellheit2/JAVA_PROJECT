package service;

import controler.LectureController;
import controler.StudentController;
import dao.LectureDAO;
import dao.StudentDAO;
import dto.Lecture;
import dto.Student;
import dto.Time;
import exception.LectureDuplicationException;
import exception.TimeConflictException;
import utility.InputUtil;
import utility.OutputUtil;

import java.sql.SQLException;
import java.util.List;

import static service.LectureService.isLectureExist;

public class AdminService {
    // 강의 추가 ************************************************************************************************
    public static void addLecture(Lecture lecture) throws LectureDuplicationException {
        if(isLectureExist(lecture) == false){
            LectureDAO.writeLectureDB(lecture);
            try {
                LectureDAO.setLecList(LectureDAO.readLectureDB());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new LectureDuplicationException("이미 존재하는 수업 입니다.");
        }
    }
    // 강의 수정 ************************************************************************************************
    public static void updateLecture(Lecture lecture){
        if(isLectureExist(lecture) == true){
            System.out.println(lecture.toString());
            while (true) {
                int result = printUpdateMenu();

                switch (result) {
                    case 0: // 종료
                        return;
                    case 1: // 강의명 변경
                        lecture.setLecName(InputUtil.INSTANCE.inputStr("변경 >>"));
                        LectureDAO.updateLectureDB(lecture, "name");
                        break;
                    case 2: // 강의유형 변경
                        lecture.setLecType(InputUtil.INSTANCE.inputStr("변경 >>"));
                        LectureDAO.updateLectureDB(lecture, "type");
                        break;
                    case 3: // 강의시간 변경
                        List<Time> timeList = lecture.getTime();
                        int i = 1;
                        for(Time temp : timeList){ // 시간 리스트 출력
                            System.out.println(i + ". " + temp.toString());
                        }
                        int index = InputUtil.INSTANCE.inputInt("시간 선택") - 1; // 변경 시간 선택

                        Time temp = lecture.getTime().get(index); //변경 시간
                        Time update = InputUtil.INSTANCE.inputTime("변경 >>"); //변경 정보
                        LectureDAO.updateScheduleDB(lecture,temp , update); // db 업데이트
                        lecture.getTime().set(index,update); // 리스트 업데이트
                        break;
                    case 4: // 학점 변경
                        lecture.setLecCredit(InputUtil.INSTANCE.inputInt("변경 >>"));
                        LectureDAO.updateLectureDB(lecture, "credit");
                        break;
                    default:
                        OutputUtil.errorMessage("잘못된 입력입니다.");
                }
            }

        }
    }
    public static int printUpdateMenu(){
        System.out.println();
        System.out.println("                                 강의정보 변경" +
                "                                     ");
        System.out.println("─────────────────────────────────────────────" +
                "────────────────────────────────────");
        System.out.println("0. 종료  | 1. 강의명 변경  | 2. 강의유형 변경 " +
                "| 3. 강의시간 변경  | 4. 학점 변경  ");
        System.out.println("─────────────────────────────────────────────" +
                "────────────────────────────────────");
        return InputUtil.INSTANCE.inputInt(">> ");
    }

    // 강의 삭제 ************************************************************************************************
    public static void delLecture(Lecture lecture) {
        if(isLectureExist(lecture) == true){
            LectureDAO.getLecList().remove(lecture);
            LectureDAO.deleteLectureDB(lecture);
        }
    }

}
