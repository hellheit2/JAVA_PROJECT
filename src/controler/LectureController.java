package controler;

import dao.LectureDAO;
import dto.Lecture;
import dto.Student;
import exception.LectureDuplicationException;
import exception.LectureOutOfRangeException;
import exception.TimeConflictException;
import service.AdminService;
import service.StudentService;
import utility.InputUtil;
import utility.OutputUtil;

public class LectureController {

    public static void addLecture(){
        Lecture newLecture = InputUtil.INSTANCE.inputLectureInfo();
        try{
            AdminService.addLecture(newLecture);
        } catch (LectureDuplicationException e) {
            OutputUtil.errorMessage(e.getMsg());
        }
    }

    public static void updateLecture(){
        try{
            int index = InputUtil.INSTANCE.inputInt(">> ");
            Lecture lecture = LectureDAO.getLectureByIndex(index);

            AdminService.updateLecture(lecture);

        }catch(LectureOutOfRangeException e) { //잘못된 강의번호
            OutputUtil.errorMessage(e.getMsg());
        }
    }

    public static void delLecture(){
        try{
            int index = InputUtil.INSTANCE.inputInt(">> ");
            Lecture lecture = LectureDAO.getLectureByIndex(index);

            AdminService.delLecture(lecture);
        } catch (LectureOutOfRangeException e) {
            OutputUtil.errorMessage(e.getMsg());
        }
    }
}
