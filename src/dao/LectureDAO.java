package dao;

import dto.Lecture;
import dto.Time;
import exception.LectureDuplicationException;
import utility.InputUtil;

import exception.LectureOutOfRangeException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LectureDAO {

    public static List<Lecture> lecList;

    static{
        lecList = readLectureDBTest();
    }

    public LectureDAO(){

    }
    public static Lecture getLectureByIndex(int index) throws LectureOutOfRangeException {

        if(isRangeOfIndex(index)){
            return lecList.get(index - 1);
        }else{
            throw new LectureOutOfRangeException("올바른 강의 번호를 입력해주세요.");
        }
    }
    public static boolean isRangeOfIndex(int index){
        if(index > lecList.size())
            return false;
        return true;
    }

    //관리자 기능
    //----add---------------------------------------------------------------------

    public void addLecture(Lecture lecture) throws LectureDuplicationException {

        if(isLectureExist(lecture)){
            throw new LectureDuplicationException("이미 존재하는 수업 입니다.");
        }
        lecList.add(lecture);
    }
    public boolean isLectureExist(Lecture lecture){
//        if(lecList.contains(lecture)){
//            return true;
//        } //object equals가 먹히는지
        for(Lecture lecTemp : lecList){
            if(lecTemp.getId().equals(lecture.getId()))
                return true;
        }
        return false;
    }

    //----update------------------------------------------------------------------


    //----delete------------------------------------------------------------------
    public boolean delLecture(Lecture lecture){
        if(isLectureExist(lecture)){
            lecList.remove(lecture);
            return true;
        }
        return false;
    }


    //-----------------------------------------------------------------------------


    public static List<Lecture> readLectureDBTest(){
        Timestamp t = Timestamp.valueOf("2022-09-05 09:00:00.0");

        try {
            List<Lecture> test = new ArrayList<>(Arrays.asList(
                    new Lecture("0001", "전공필수", "JAVA 기초", new ArrayList<>(Arrays.asList(new Time("월",Timestamp.valueOf("2022-09-05 09:00:00.0"),Timestamp.valueOf("2022-09-05 11:00:00.0")),new Time("수",Timestamp.valueOf("2022-09-07 09:00:00.0"),Timestamp.valueOf("2022-09-07 11:00:00.0")))), 4),
                    new Lecture("0002", "전공필수", "Spring 실습", new ArrayList<>(Arrays.asList(new Time("월",Timestamp.valueOf("2022-09-05 11:00:00.0"),Timestamp.valueOf("2022-09-05 13:00:00.0")), new Time("목",Timestamp.valueOf("2022-09-08 10:00:00.0"),Timestamp.valueOf("2022-09-08 12:00:00.0")))), 4),
                    new Lecture("0003", "교양", "HTML 기초", new ArrayList<>(Arrays.asList(new Time("월",Timestamp.valueOf("2022-09-05 09:00:00.0"),Timestamp.valueOf("2022-09-05 11:00:00.0")))), 2),
                    new Lecture("0004", "교양", "CSS 응용", new ArrayList<>(Arrays.asList(new Time("월",Timestamp.valueOf("2022-09-05 14:00:00.0"),Timestamp.valueOf("2022-09-05 16:00:00.0")), new Time("목",Timestamp.valueOf("2022-09-08 09:00:00.0"),Timestamp.valueOf("2022-09-08 10:00:00.0")))), 4),
                    new Lecture("0005", "전공선택", "SQL 데이터베이스", new ArrayList<>(Arrays.asList(new Time("화",Timestamp.valueOf("2022-09-06 12:00:00.0"),Timestamp.valueOf("2022-09-06 14:00:00.0")), new Time("목",Timestamp.valueOf("2022-09-08 13:00:00.0"),Timestamp.valueOf("2022-09-08 15:00:00.0")))), 3),
                    new Lecture("0006", "전공선택", "JAVA 심화", new ArrayList<>(Arrays.asList(new Time("수",Timestamp.valueOf("2022-09-07 11:00:00.0"),Timestamp.valueOf("2022-09-07 14:00:00.0")), new Time("목",Timestamp.valueOf("2022-09-08 09:00:00.0"),Timestamp.valueOf("2022-09-08 13:00:00.0")))), 4),
                    new Lecture("0007", "전공필수", "프로젝트 설계 기초", new ArrayList<>(Arrays.asList(new Time("금",Timestamp.valueOf("2022-09-09 09:00:00.0"),Timestamp.valueOf("2022-09-09 13:00:00.0")))), 4),
                    new Lecture("0008", "전공선택", "ERD 다이어그램의 이해", new ArrayList<>(Arrays.asList(new Time("월",Timestamp.valueOf("2022-09-05 09:00:00.0"),Timestamp.valueOf("2022-09-05 11:00:00.0")), new Time("수",Timestamp.valueOf("2022-09-07 09:00:00.0"),Timestamp.valueOf("2022-09-07 11:00:00.0")))), 3),
                    new Lecture("0009", "교양", "프로그래밍의 역사", new ArrayList<>(Arrays.asList(new Time("월",Timestamp.valueOf("2022-09-05 16:00:00.0"),Timestamp.valueOf("2022-09-05 17:00:00.0")))), 1),
                    new Lecture("0010", "전공선택", "정보처리학개론", new ArrayList<>(Arrays.asList(new Time("월",Timestamp.valueOf("2022-09-05 09:00:00.0"),Timestamp.valueOf("2022-09-05 11:00:00.0")), new Time("수",Timestamp.valueOf("2022-09-07 09:00:00.0"),Timestamp.valueOf("2022-09-07 11:00:00.0")))), 3)
            ));
            return test;
        } catch(Exception e){
            System.out.println("입력 실패");
        }
        return null;
    }

    public List<Lecture> readLectureDB() throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Lecture> tempLecList = new ArrayList<Lecture>();

        String query = "select * " +
                "from lecture natural join schedule;";

        try {
            con = DAO.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while(rs.next()){
                String lecId = rs.getString(1);
                String lecType = rs.getString(2);
                String lecName = rs.getString(3);
                int lecCredit = rs.getInt(4);


                List<Time> lecTime = new ArrayList<>();


                //rs 속에서 rs2를 부르는게 가능하면 시간 빼고 부르고 / id 기준으로 시간 정보만 가져와서 저장되나?
                Connection con2 = null;
                PreparedStatement ps2 = null;
                ResultSet rs2 = null;
                String query2 = "select * " +
                        "from schedule where lec_id = " + lecId +";";
                try{
                    con2 = DAO.getConnection();
                    ps2 = con.prepareStatement(query);
                    rs2 = ps.executeQuery(query);

                    while(rs2.next()){
                        Timestamp start = rs.getTimestamp(1);
                        Timestamp end = rs.getTimestamp(2);

                        Time time = InputUtil.INSTANCE.timestampToTime(start,end);
                        lecTime.add(time);
                    }
                }finally{
                    DAO.close(con2,ps2,rs2);
                }

                Lecture lecture = new Lecture(lecId,lecType,lecName,lecTime,lecCredit);
                tempLecList.add(lecture);

            }
        } finally{
            DAO.close(con,ps,rs);
        }
        return tempLecList;
    }



}
