package view;

import controler.LectureController;
import controler.StudentController;
import dao.LectureDAO;
import dao.StudentDAO;
import dto.Student;
import service.StudentService;
import utility.InputUtil;
import utility.OutputUtil;

public class AdminMenuView {

    public static void adminMenu(){
        Student student = StudentDAO.getSelectStu();
        while (true) {
            int result = printAdminMenu();

            switch (result) {
                case 0: // 종료
                    System.out.println("종료");
                    StudentDAO.setSelectStu(null);
                    return;
                case 1: // 학생 목록
                    System.out.println("학생 목록");
                    OutputUtil.printStudentList(StudentDAO.getStuList());
                    System.out.println("학생 세부 조회");
                    break;
                case 2: // 강의 목록
                    System.out.println("강의 목록");
                    OutputUtil.printLectureList(LectureDAO.getLecList());
                    break;
                case 3: // 강의 등록
                    System.out.println("강의 등록");
                    LectureController.addLecture();
                    break;
                case 4: // 강의 변경
                    System.out.println("강의 변경");
                    OutputUtil.printLectureList(LectureDAO.getLecList());
                    LectureController.updateLecture();
                    break;
                case 5: // 강의 삭제
                    System.out.println("강의 삭제");
                    OutputUtil.printLectureList(LectureDAO.getLecList());
                    LectureController.delLecture();
                    break;
                default:
                    OutputUtil.errorMessage("잘못된 입력입니다.");
            }
        }

    }

    public static int printAdminMenu(){
        System.out.println();
        System.out.println("                                   관리자 메뉴" +
                "                                     ");
        System.out.println("─────────────────────────────────────────────" +
                "────────────────────────────────────");
        System.out.println("0. 종료  | 1. 학생 목록  | 2. 강의 목록 " +
                "| 3. 강의 등록   | 4. 강의 변경 | 5. 강의 삭제");
        System.out.println("─────────────────────────────────────────────" +
                "────────────────────────────────────");
        return InputUtil.INSTANCE.inputInt(">> ");
    }
}
