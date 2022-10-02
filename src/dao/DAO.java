package dao;

import dto.Lecture;
import dto.Time;

import java.sql.*;

public class DAO {

    public static String driver = "oracle.jdbc.driver.OracleDriver";
    public static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    public static String user = "LECTURE_ADMIN";
    public static String password = "12345";

    static{
        try{
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    public static void close(Connection con, Statement stmt, ResultSet rs){
        try{
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if ( con != null) con.close();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }


    public int insertLectureDB(Lecture lecture){

        Connection con = null;
        PreparedStatement psmt = null;
        int cnt = 0;

        String query = "";
        for (Time timeTemp:lecture.getTime()) {
            try{
                query = "INSERT INTO LECTURE VALUES(?,?,?,?)";
                psmt = con.prepareStatement(query);
                psmt.setString(1,lecture.getId());
                psmt.setString(2,lecture.getType());
                psmt.setString(3,lecture.getName());
                psmt.setInt(4,lecture.getCredit());

                cnt = psmt.executeUpdate();

                query = "insert into schedule values(?,?,?)";
                psmt = con.prepareStatement(query);
                psmt.setString(1,lecture.getId());
                psmt.setTimestamp(2, timeTemp.getStartTime());
                psmt.setTimestamp(3, timeTemp.getEndTime());
                cnt = psmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                DAO.close(con,psmt,null);
            }
        }
        return cnt;
    }

}
