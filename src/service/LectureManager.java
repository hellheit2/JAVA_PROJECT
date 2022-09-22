package service;

import dto.Lecture;
import dto.Time;
import exception.LectureDuplicationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LectureManager {
    public List<Lecture> lectureList = new ArrayList<>();

    public LectureManager(){
        //test용
        lectureList.add(new Lecture("0001", "전공필수", "JAVA 기초", new ArrayList<>(Arrays.asList(new Time("월",1,3),new Time("수",1,3))), 4));
        lectureList.add(new Lecture("0002", "전공필수", "Spring 실습", new ArrayList<>(Arrays.asList(new Time("월",3,5),new Time("화",2,4))), 4));
        lectureList.add(new Lecture("0003", "교양", "HTML 기초", new ArrayList<>(Arrays.asList(new Time("월",1,3))), 2));
        lectureList.add(new Lecture("0004", "교양", "CSS 응용", new ArrayList<>(Arrays.asList(new Time("월",6,7),new Time("목",1,2))), 2));
        lectureList.add(new Lecture("0005", "전공선택", "SQL 데이터베이스", new ArrayList<>(Arrays.asList(new Time("화",4,6),new Time("목",5,6))), 3));
        lectureList.add(new Lecture("0006", "전공선택", "JAVA 심화", new ArrayList<>(Arrays.asList(new Time("수",3,5),new Time("목",3,5))), 4));
        lectureList.add(new Lecture("0007", "전공필수", "프로젝트 설계 기초", new ArrayList<>(Arrays.asList(new Time("금",1,5))), 4));
        lectureList.add(new Lecture("0008", "전공선택", "E-R 다이어그램의 이해", new ArrayList<>(Arrays.asList(new Time("월",1,3),new Time("수",1,3))), 3));
        lectureList.add(new Lecture("0009", "교양", "프로그래밍의 역사", new ArrayList<>(Arrays.asList(new Time("월",7,8))), 1));
        lectureList.add(new Lecture("0010", "전공선택", "정보처리학개론", new ArrayList<>(Arrays.asList(new Time("월",1,3),new Time("수",1,3))), 3));
    
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
