package dao;

import dto.Lecture;
import dto.Time;

import java.sql.*;

public class DAO {

    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/lecture_db";
    public static String user = "root";
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

    public static void close(Connection con, Statement psmt, ResultSet rs){
        try{
            if (rs != null) rs.close();
            if (psmt != null) psmt.close();
            if ( con != null) con.close();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
