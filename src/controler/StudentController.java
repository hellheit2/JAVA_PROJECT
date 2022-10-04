package controler;

import dao.LectureDAO;
import dao.StudentDAO;
import dto.Lecture;
import dto.Student;
import exception.LectureDuplicationException;
import exception.LectureOutOfRangeException;
import exception.TimeConflictException;
import service.StudentService;
import utility.InputUtil;
import utility.OutputUtil;

public class StudentController {


    public static void addStudentLecture(Student student){
        try{
            int index = InputUtil.INSTANCE.inputInt(">> ");
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
            int index = InputUtil.INSTANCE.inputInt(">> ");
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
