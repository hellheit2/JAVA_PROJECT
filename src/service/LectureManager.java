package service;

import dto.Lecture;
import dto.Student;
import dto.Time;
import exception.LectureDuplicationException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class LectureManager {

    final static LectureManager INSTANCE = new LectureManager();
    public List<Lecture> lectureList = new ArrayList<>();

    public LectureManager(){

        connectDatabase();
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

    public void connectDatabase(){
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
                Time time = timeStampToTime(ts,lecDuration);

                int lecCredit = rs.getInt(6);

                Lecture isLec = isLectureExist(lecId);
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
    public Lecture isLectureExist(String lecId){
        for(Lecture lecTemp : lectureList){
            if(lecTemp.getId().equals(lecId)){
                return lecTemp;
            }
        }
        return null;
    }
    public void printLecture(){
        // 학생추가
        for(Lecture lecTemp: lectureList){
            System.out.println(lecTemp.toString());
        }
    }
    public Time timeStampToTime(Timestamp ts, int duration){
        Time time = new Time();
        SimpleDateFormat conTimeFormat = new SimpleDateFormat("E H");
        StringTokenizer st = new StringTokenizer(conTimeFormat.format(ts), " ");

        time.day = st.nextToken();
        time.startTime = Integer.parseInt(st.nextToken());
        time.endTime = time.startTime + duration;

        return time;
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

}
