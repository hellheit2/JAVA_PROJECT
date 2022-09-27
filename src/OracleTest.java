import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class OracleTest {
    public static void main(String[] args) throws SQLException {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "LECTURE_ADMIN";
        String password = "12345";
        Connection con = null;
        try {
            Class.forName(driver);
            System.out.println("jdbc driver 로딩 성공");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("오라클 연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver 로딩 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("오라클 연결 실패");
        }

        String query = "select * from LECTURE";
        Statement stmt;
        ResultSet rs;
        //System.out.println(con);

        stmt = con.createStatement();
        rs = stmt.executeQuery(query);

        while(rs.next()){
            Timestamp ts = rs.getTimestamp(4);
            SimpleDateFormat conTimeFormat = new SimpleDateFormat("E H");
            System.out.println(conTimeFormat.format(ts));
            StringTokenizer st = new StringTokenizer(conTimeFormat.format(ts), " ");
            System.out.println(rs.getTimestamp(4));

            String a = st.nextToken();
            int b = Integer.parseInt(st.nextToken());

            System.out.println(a);
            System.out.println(b);

        }

        con.close();
    }
}
