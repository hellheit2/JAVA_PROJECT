package service;

import dto.Student;
import view.StudentView;

public class StudentMenuController {

    int result;
    static boolean isRun = true;
    public void showStudentMenu(Student student) {

        while (isRun) {
            result = StudentView.studentMenu();

            switch (result) {
                case 0:
                    System.out.println("종료");
                    isRun = false;
                    break;
                case 1:
                    System.out.println("강의 목록");
                    LectureManager.INSTANCE.printLecture();
                    break;
                case 2:
                    System.out.println("수강 신청");
                    break;
                case 3:
                    System.out.println("수강 철회");
                    break;
                case 4:
                    System.out.println("시간표 확인");
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
