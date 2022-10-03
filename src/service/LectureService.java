package service;

import dao.LectureDAO;
import dto.Lecture;
import dto.Student;
import dto.Time;

import java.util.List;

public class LectureService {

    public static boolean isLectureExist(Lecture lecture){
        List<Lecture> lectureList = LectureDAO.getLecList();
        for (Lecture tempLec: lectureList) {
            if(tempLec.getId().equals(lecture.getId()))
                return true;
        }
        return false;
    }

}
