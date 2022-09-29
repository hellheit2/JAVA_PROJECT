package service;

import dto.Lecture;
import dto.Time;
import exception.LectureDuplicationException;
import exception.OutOfWeekdayException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;


public class LectureManager{

    final static LectureManager INSTANCE = new LectureManager();
    public List<Lecture> lectureList = new ArrayList<>();

    public LectureManager() {

        readDatabase();
        //test용
        try {
            List<Lecture> test = new ArrayList<>(Arrays.asList(
                    new Lecture("0002", "전공필수", "Spring 실습", new ArrayList<>(Arrays.asList(new Time("월", 11, 13), new Time("화", 10, 12))), 4),
                    new Lecture("0003", "교양", "HTML 기초", new ArrayList<>(Arrays.asList(new Time("월", 9, 11))), 2),
                    new Lecture("0004", "교양", "CSS 응용", new ArrayList<>(Arrays.asList(new Time("월", 14, 16), new Time("목", 9, 10))), 2),
                    new Lecture("0005", "전공선택", "SQL 데이터베이스", new ArrayList<>(Arrays.asList(new Time("화", 12, 14), new Time("목", 13, 15))), 3),
                    new Lecture("0006", "전공선택", "JAVA 심화", new ArrayList<>(Arrays.asList(new Time("수", 11, 14), new Time("목", 9, 13))), 4),
                    new Lecture("0007", "전공필수", "프로젝트 설계 기초", new ArrayList<>(Arrays.asList(new Time("금", 9, 13))), 4),
                    new Lecture("0008", "전공선택", "ERD 다이어그램의 이해", new ArrayList<>(Arrays.asList(new Time("월", 9, 11), new Time("수", 9, 11))), 3),
                    new Lecture("0009", "교양", "프로그래밍의 역사", new ArrayList<>(Arrays.asList(new Time("월", 16, 17))), 1),
                    new Lecture("0010", "전공선택", "정보처리학개론", new ArrayList<>(Arrays.asList(new Time("월", 9, 11), new Time("수", 9, 11))), 3)
            ));
            for (Lecture l : test) {
                try {
                    addLecture(l);
                } catch(LectureDuplicationException e){
                    System.out.println("중복 입력");
                }
            }
        } catch(OutOfWeekdayException e){
            System.out.println("요일 입력 실패");
        }



//            addLecture(new Lecture("0001", "전공필수", "JAVA 기초", new ArrayList<>(Arrays.asList(new Time("월",9,11),new Time("수",9,11))), 4));
//            addLecture(new Lecture("0002", "전공필수", "Spring 실습", new ArrayList<>(Arrays.asList(new Time("월",11,13),new Time("화",10,12))), 4));
//            addLecture(new Lecture("0003", "교양", "HTML 기초", new ArrayList<>(Arrays.asList(new Time("월",9,11))), 2));
//            addLecture(new Lecture("0004", "교양", "CSS 응용", new ArrayList<>(Arrays.asList(new Time("월",14,16),new Time("목",9,10))), 2));
//            addLecture(new Lecture("0005", "전공선택", "SQL 데이터베이스", new ArrayList<>(Arrays.asList(new Time("화",12,14),new Time("목",13,15))), 3));
//            addLecture(new Lecture("0006", "전공선택", "JAVA 심화", new ArrayList<>(Arrays.asList(new Time("수",11,14),new Time("목",9,13))), 4));
//            addLecture(new Lecture("0007", "전공필수", "프로젝트 설계 기초", new ArrayList<>(Arrays.asList(new Time("금",9,13))), 4));
//            addLecture(new Lecture("0008", "전공선택", "E-R 다이어그램의 이해", new ArrayList<>(Arrays.asList(new Time("월",9,11),new Time("수",9,11))), 3));
//            addLecture(new Lecture("0009", "교양", "프로그래밍의 역사", new ArrayList<>(Arrays.asList(new Time("월",16,17))), 1));
//            addLecture(new Lecture("0010", "전공선택", "정보처리학개론", new ArrayList<>(Arrays.asList(new Time("월",9,11),new Time("수",9,11))), 3));


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

        if(isLectureExist(lecture)){
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
