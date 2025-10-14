package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class L04DBFactoryTest {
    public static void main(String[] args) {
        String sql="SELECT * FROM DEPT"; //띄어쓰기 제대로 해야하ㄴㅔ..
        try (Connection conn=L03DBFactory.getConn();
             Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)){
            while (rs.next()){
                System.out.println(rs.getString("dname"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("////////////////close 이후 한 번 더 호출////////////////");

        try (Connection conn=L03DBFactory.getConn();
             Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)){
            while (rs.next()){
                System.out.println(rs.getString("dname"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
