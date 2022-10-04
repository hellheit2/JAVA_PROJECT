package view;

import controler.StudentController;
import dao.LectureDAO;
import dao.StudentDAO;
import dto.Student;
import service.StudentService;
import utility.InputUtil;
import utility.OutputUtil;

public class StudentMenuView {
    public static void stuMenu(){
        Student student = StudentDAO.getSelectStu();
        while (true) {
            int result = printStudentMenu();
            switch (result) {
                case 0: // 종료
                    System.out.println("종료");
                    StudentDAO.setSelectStu(null);
                    return;
                case 1: // 강의 목록
                    System.out.println("강의 목록");
                    OutputUtil.printLectureList(LectureDAO.getLecList());
                    break;
                case 2: // 수강 내역
                    System.out.println("수강 내역");
                    StudentService.printStudentLecture(student.getMyLecture());
                    break;
                case 3: // 수강 신청
                    System.out.println("수강 신청");
                    OutputUtil.printLectureList(LectureDAO.getLecList());

                    StudentController.addStudentLecture(student);
                    break;
                case 4: // 수강 철회
                    System.out.println("수강 철회");
                    StudentService.printStudentLecture(student.getMyLecture());

                    StudentController.delStudentLecture(student);
                    break;
                case 5: // 시간표 확인
                    System.out.println("시간표 확인");
                    StudentController.showSchedule(student);
                    break;
                default:
                    OutputUtil.errorMessage("잘못된 입력입니다.");
            }
        }

    }
    public static int printStudentMenu(){
        System.out.println();
        System.out.println("                                     학생 메뉴" +
                "                                     ");
        System.out.println("─────────────────────────────────────────────" +
                "────────────────────────────────────");
        System.out.println("0. 종료  | 1. 강의 목록  | 2. 수강 내역 " +
                "| 3. 수강 신청   | 4. 수강 철회 | 5. 시간표 확인");
        System.out.println("─────────────────────────────────────────────" +
                "────────────────────────────────────");
        return InputUtil.INSTANCE.inputInt(">> ");
    }
}
