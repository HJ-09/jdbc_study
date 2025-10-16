package hw.memberDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBFactory {
    private static String url="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static String user="hyeji";
    private static String pw="hyeji1";

    public static Connection conn;

    static {
        try {
            conn= DriverManager.getConnection(url,user,pw);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws SQLException {
        if (conn==null || conn.isClosed()){
            conn=DriverManager.getConnection(url,user,pw);
        }
        return conn;
    }
}


class DBFactoryTest{
    public static void main(String[] args) throws SQLException {
        //과제 2. 접속 테스트하기

        Connection bean= hw.dao.DBFactory.getConn();

        if (bean != null){
            System.out.println("연결됨");
        }

        bean.close();
    }
}
