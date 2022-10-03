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

    private static List<Lecture> lecList;

    static{
        try {
            lecList = readLectureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LectureDAO(){

    }

    public static List<Lecture> getLecList() {
        return lecList;
    }

    public static void setLecList(List<Lecture> lecList) {
        LectureDAO.lecList = lecList;
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

    public static List<Lecture> readLectureDB() throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Lecture> tempLecList = new ArrayList<>();

        String query = "select * from lecture;";

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
                    ps2 = con2.prepareStatement(query2);
                    rs2 = ps2.executeQuery(query2);

                    while(rs2.next()){
                        Timestamp start = rs2.getTimestamp(2);
                        Timestamp end = rs2.getTimestamp(3);
                        Time time = InputUtil.INSTANCE.timestampToTime(start,end);
                        lecTime.add(time);
                    }
                }finally{
                    DAO.close(con2,ps2,rs2);
                }

                Lecture lecture = new Lecture(lecId,lecType,lecName,lecTime,lecCredit);
                tempLecList.add(lecture);

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            DAO.close(con,ps,rs);
        }
        return tempLecList;
    }
    public static int writeLectureDB(Lecture lecture) {
        Connection con = null;
        PreparedStatement ps = null;
        int cnt = 0;
        int cnt2 = 0;

        try{
            con = DAO.getConnection();
            String query = "insert into lecture values(?,?,?,?)";

            ps = con.prepareStatement(query);
            ps.setString(1,lecture.getId());
            ps.setString(2,lecture.getType());
            ps.setString(3,lecture.getName());
            ps.setInt(4,lecture.getCredit());

            cnt = ps.executeUpdate();
            query = "insert into schedule values(?,?,?)";

            for (Time timeTemp:lecture.getTime()) {

                ps = con.prepareStatement(query);
                ps.setString(1, lecture.getId());
                ps.setTimestamp(2, timeTemp.getStartTime());
                ps.setTimestamp(3, timeTemp.getEndTime());
                cnt = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DAO.close(con,ps,null);
        }

        return cnt;
    }

}
