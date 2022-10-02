package view;

import dao.LectureDAO;
import dao.StudentDAO;
import utility.InputUtil;
import utility.OutputUtil;

public class AdminMenuView {

    static boolean isRun = true;

    public static void adminMenu(){
        System.out.println(StudentDAO.getSelectStu());
        while (isRun) {
            int result = printAdminMenu();
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
        return InputUtil.INSTANCE.inputMenu(">> ");
    }
}
