package controler;

import dao.LectureDAO;
import dao.StudentDAO;
import dto.Lecture;
import dto.Student;
import exception.LectureDuplicationException;
import exception.NotFoundException;
import exception.LectureOutOfRangeException;
import exception.TimeConflictException;
import service.LoginService;
import service.StudentService;
import utility.InputUtil;
import utility.OutputUtil;
import view.StudentMenuView;

public class StudentController {

    static LoginService loginService = new LoginService();

    static StudentDAO studentDAO;

    public static void login(String stuId, String stuPwd){
        try{
            studentDAO = loginService.loginCheck(stuId, stuPwd);
            if(studentDAO == null){
                //관리자
            }else{
                StudentMenuView.stuMenu();
            }
        }catch(NotFoundException e){
            OutputUtil.errorMessage(e.getMsg());
        }
    }

    public static void addStudentLecture(Student student){
        try{
            int index = InputUtil.INSTANCE.inputMenu(">> ");
            Lecture lecture = LectureDAO.getLectureByIndex(index);

            StudentService.addStudentLecture(student,lecture);

        }catch(LectureOutOfRangeException e) { //잘못된 강의번호
            OutputUtil.errorMessage(e.getMsg());
        }catch(TimeConflictException e) { //시간 겹침
            OutputUtil.errorMessage(e.getMsg());
        }catch (LectureDuplicationException e) { // 강의 중복
            OutputUtil.errorMessage(e.getMsg());
        }

    }
    public static void delStudentLecture(Student student){
        try{
            int index = InputUtil.INSTANCE.inputMenu(">> ");
            Lecture lecture = StudentDAO.getLectureByIndex(index);

            StudentService.delStudentLecture(student,lecture);

        }catch (LectureOutOfRangeException e) { // 잘못된 강의 번호
            OutputUtil.errorMessage(e.getMsg());
        }
    }

    public static void showSchedule(Student student){
        StudentService.showSchedule(student.getMyLecture());
    }
}
