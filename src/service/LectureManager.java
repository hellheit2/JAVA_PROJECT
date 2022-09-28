package service;

import dto.Lecture;
import dto.Student;
import dto.Time;
import exception.LectureDuplicationException;

import view.MainView;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


public class LectureManager{

    final static LectureManager INSTANCE = new LectureManager();
    public List<Lecture> lectureList = new ArrayList<>();

    public LectureManager(){

        readDatabase();
        //test용
//        lectureList.add(new Lecture("0001", "전공필수", "JAVA 기초", new ArrayList<>(Arrays.asList(new Time("월",1,3),new Time("수",1,3))), 4));
//        lectureList.add(new Lecture("0002", "전공필수", "Spring 실습", new ArrayList<>(Arrays.asList(new Time("월",3,5),new Time("화",2,4))), 4));
//        lectureList.add(new Lecture("0003", "교양", "HTML 기초", new ArrayList<>(Arrays.asList(new Time("월",1,3))), 2));
//        lectureList.add(new Lecture("0004", "교양", "CSS 응용", new ArrayList<>(Arrays.asList(new Time("월",6,7),new Time("목",1,2))), 2));
//        lectureList.add(new Lecture("0005", "전공선택", "SQL 데이터베이스", new ArrayList<>(Arrays.asList(new Time("화",4,6),new Time("목",5,6))), 3));
//        lectureList.add(new Lecture("0006", "전공선택", "JAVA 심화", new ArrayList<>(Arrays.asList(new Time("수",3,5),new Time("목",3,5))), 4));
//        lectureList.add(new Lecture("0007", "전공필수", "프로젝트 설계 기초", new ArrayList<>(Arrays.asList(new Time("금",1,5))), 4));
//        lectureList.add(new Lecture("0008", "전공선택", "E-R 다이어그램의 이해", new ArrayList<>(Arrays.asList(new Time("월",1,3),new Time("수",1,3))), 3));
//        lectureList.add(new Lecture("0009", "교양", "프로그래밍의 역사", new ArrayList<>(Arrays.asList(new Time("월",7,8))), 1));
//        lectureList.add(new Lecture("0010", "전공선택", "정보처리학개론", new ArrayList<>(Arrays.asList(new Time("월",1,3),new Time("수",1,3))), 3));
    }


    public void printLecture(){
        IOUtil.INSTANCE.printLectureList(lectureList);
    }

    //-----get--------------------------------------------------------------------

    public List<Lecture> getLectureList() {
        return lectureList;
    }

    //관리자 기능
    //----add---------------------------------------------------------------------

    public void addLecture(Lecture lecture) throws LectureDuplicationException {

        if(getLectureIndexById(lecture.getId()) == null){
            throw new LectureDuplicationException("이미 존재하는 수업 입니다.");
        }

        lectureList.add(lecture);
    }

    //----update------------------------------------------------------------------


    //----delete------------------------------------------------------------------
    public boolean delLecture(String lecId){
        Lecture index = getLectureIndexById(lecId);
        if(index != null) {
            lectureList.remove(index);
            return true;
        }
        return false;
    }


    //-----------------------------------------------------------------------------
    public boolean isRangeOfIndex(int index){
        if(index > lectureList.size())
            return false;
        return true;
    }
    public boolean isRangeOfIndex(int index, List<Lecture> lecture){
        if(index > lecture.size())
            return false;
        return true;
    }
    public boolean isLectureExist(Lecture lecture){
        for(Lecture lecTemp : lectureList){
            if(lecTemp.getId().equals(lecture.getId()))
                return true;
        }
        return false;
    }
    public Lecture getLectureByIndex(int index){
        if(isRangeOfIndex(index))
            return lectureList.get(index-1);
        return null;
    }
    public Lecture getLectureByIndex(int index, List<Lecture> lecture){
        if(isRangeOfIndex(index,lecture))
            return lecture.get(index-1);
        return null;
    }
    public Lecture getLectureIndexById(String lecId){
        for(Lecture tempLec : lectureList){
            if(tempLec.getId().equals(lecId))
                return tempLec;
        }
        return null;
    }


    //----db connect------------------------------------------------------------------
    public void readDatabase(){
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "LECTURE_ADMIN";
        String password = "12345";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            //System.out.println("jdbc driver 로딩 성공");
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("오라클 연결 성공");

            String query = "SELECT * FROM LECTURE";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()){

                String lecId = rs.getString(1);
                String lecType = rs.getString(2);
                String lecName = rs.getString(3);

                List<Time> lecTime = new ArrayList<>();

                Timestamp ts = rs.getTimestamp(4);
                int lecDuration = rs.getInt(5);
                Time time = IOUtil.INSTANCE.timeStampToTime(ts,lecDuration);

                int lecCredit = rs.getInt(6);

                Lecture isLec = getLectureIndexById(lecId);
                if(isLec != null) {
                    isLec.getTime().add(time);
                }else{
                    lecTime.add(time);
                    Lecture lecture = new Lecture(lecId,lecType,lecName,lecTime,lecCredit);
                    lectureList.add(lecture);
                }

            }

        } catch (ClassNotFoundException e) {
            //System.out.println("jdbc driver 로딩 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            //System.out.println("오라클 연결 실패");
            e.printStackTrace();
        }finally{
            try{
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if ( con != null) con.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
    }

}
