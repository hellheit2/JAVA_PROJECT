package service;

import dao.LectureDAO;
import dto.Lecture;
import dto.Student;
import exception.LectureDuplicationException;
import exception.TimeConflictException;
import utility.OutputUtil;

import java.sql.SQLException;

import static service.LectureService.isLectureExist;

public class AdminService {
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

    public static void updateLecture(Lecture lecture){

    }

    public static void printUpdateMenu(){

    }

    public static void delLecture(Lecture lecture) {
        if(isLectureExist(lecture) == true)
            LectureDAO.getLecList().remove(lecture);

    }

}
