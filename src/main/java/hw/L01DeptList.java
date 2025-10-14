package hw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class L01DeptList {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String user="scott";
        String pw="tiger";
        String sql="SELECT * FROM DEPT";

        //try절에 소괄호하면 자동으로 finally 지점에 close 함. ⇒ auto close
        try (
                Connection conn= DriverManager.getConnection(url,user,pw);
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery(sql);
            ){
            while (rs.next()){
                int no=rs.getInt("DEPTNO");
                String name=rs.getString("DNAME");
                String loc=rs.getString("loc");
                System.out.println(no+" | "+name+" | "+loc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
