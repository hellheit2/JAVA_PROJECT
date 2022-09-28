package service;

import dto.Student;
import view.StudentView;

import java.io.IOException;

public class StudentController {

    int result;
    static boolean isRun = true;
    public void showStudentMenu(Student student) throws IOException {
        while (isRun) {
            int index = -1;
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
                    System.out.println("수강 내역");
                    StudentService.INSTANCE.printStudentLecture(student.getMyLecture());
                    break;
                case 3:
                    System.out.println("수강 신청");

                    LectureManager.INSTANCE.printLecture();
                    index = Integer.parseInt(IOUtil.INSTANCE.inputMenu("강의 번호 : "));

                    if(LectureManager.INSTANCE.isRangeOfIndex(index)){
                        StudentService.INSTANCE.addStudentLecture(student, index);
                    }else{
                        System.out.println("올바른 강의 번호를 입력해주세요.");
                    }
                    break;
                case 4:
                    System.out.println("수강 철회");

                    LectureManager.INSTANCE.printLecture();
                    index = Integer.parseInt(IOUtil.INSTANCE.inputMenu("강의 번호 : "));

                    if(LectureManager.INSTANCE.isRangeOfIndex(index)){
                        StudentService.INSTANCE.delStudentLecture(student, index);
                    }else{
                        System.out.println("올바른 강의 번호를 입력해주세요.");
                    }
                    break;
                case 5:
                    System.out.println("시간표 확인");
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
