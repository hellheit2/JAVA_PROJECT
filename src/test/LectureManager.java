package test;

import exception.LectureDuplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LectureManager {
    public List<Lecture> lectureList = new ArrayList<>();

    public LectureManager(){

    }

    //-----get--------------------------------------------------------------------

    public List<Lecture> getLectureList() {
        return lectureList;
    }

    //관리자 기능
    //----add---------------------------------------------------------------------

    public void addLecture(Lecture lecture) throws LectureDuplicationException {
        for (Lecture l : lectureList) {
            if (l.getId().equals(lecture.getId())) {
                throw new LectureDuplicationException("이미 존재하는 수업 입니다.");
            }
        }

        lectureList.add(lecture);
    }

    //----update------------------------------------------------------------------


    //----delete------------------------------------------------------------------
    public boolean delLecture(String lectureId){
        Lecture index = getLectureIndexById(lectureId);
        if(index != null) {
            lectureList.remove(index);
            return true;
        }
        return false;
    }

    public Lecture getLectureIndexById(String lectureId){
        for(Lecture l : lectureList){
            if(l.getId().equals(lectureId)){
                return l;
            }
        }
        return null;
    }

    public void showLecture(){

    }
    public List<List<Integer>> parseLectureTime(String lectureTime){
        List<List<Integer>> ltime = new ArrayList<>();
        //String 으로 들어오는지는 모르겠음
        //ex)
        //강의코드  강의타입  강의이름  요일  시작시간  마치는시간  강의학점
        //0001      전공      A      0      1         2        3
        //0001      전공      A      3      2         4        3
        //
        // 중복되는 내용은 쿼리문 하나로 해서 Lecture 클래스에 넣고
        // 강의시간은 여러개 있을 수 있으니까 쿼리를 한번 더 써서 (요일, 시작시간, 마치는시간 묶어서)
        // 강의이름이 A인 강의 시간들을 합쳐서 lectureTime 으로 받아와서 쪼갠다.

        return ltime;
    }
}
