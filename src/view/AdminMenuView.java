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

    static boolean isRun = true;

    public static void adminMenu(){
        Student student = StudentDAO.getSelectStu();
        while (isRun) {
            int result = printAdminMenu();

            switch (result) {
                case 0: // 종료
                    System.out.println("종료");
                    isRun = false;
                    StudentDAO.setSelectStu(null);
                    break;
                case 1: // 강의 목록
                    System.out.println("학생 목록");
                    OutputUtil.prinStudentList(StudentDAO.getStuList());
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
                    LectureController.updateLecture();

                    StudentController.delStudentLecture(student);
                    break;
                case 5: // 강의 삭제
                    System.out.println("강의 삭제");
                    StudentController.showSchedule(student);
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
        System.out.println("0. 종료  | 1. 학생 목록  | 2. 수강 목록 " +
                "| 3. 강의 등록   | 4. 강의 변경 | 5. 강의 삭제");
        System.out.println("─────────────────────────────────────────────" +
                "────────────────────────────────────");
        return InputUtil.INSTANCE.inputInt(">> ");
    }
}
