package dto;

import service.IOUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dao {
    Connection con = null;
    PreparedStatement psmt =null;
    ResultSet rs = null;
    int cnt = 0;

    public void con(){
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "LECTURE_ADMIN";
        String password = "12345";

        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void close(){
        try{
            if (rs != null) rs.close();
            if (psmt != null) psmt.close();
            if ( con != null) con.close();
        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

    public int insertLectureDB(Lecture lecture){
        con();

        String query = "";
        for (Time timeTemp:lecture.getTime()) {
            try{
                query = "INSERT INTO LECTURE VALUES(?,?,?,?,?,?)";
                psmt = con.prepareStatement(query);
                psmt.setString(1,lecture.getId());
                psmt.setString(2,lecture.getType());
                psmt.setString(3,lecture.getName());
                psmt.setTimestamp(4,timeTemp.getTs());
                psmt.setInt(5,(timeTemp.getEndTime() - timeTemp.getStartTime()));
                psmt.setInt(6,lecture.getCredit());

                cnt = psmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                close();
            }
        }
        return cnt;
    }
}
