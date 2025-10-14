package self;

import java.sql.*;

public class H02 {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String user="scott";
        String pw="tiger";

        try (Connection conn= DriverManager.getConnection(url,user,pw)){
            try (Statement stmt=conn.createStatement()){
                String sql="INSERT INTO EMP (empno,ename,job,sal,deptno) VALUES (9001,'ALLEN2','SALESMAN',1600.00,30)";
                int insert=stmt.executeUpdate(sql);
                if (insert==1){
                    System.out.println("ALLEN2 등록 성공");
                }
        } //Statement는 일회용임. 1쿼리 1스테이트먼트 (...?)
            try (Statement stmt=conn.createStatement()){
                ResultSet rs=stmt.executeQuery("SELECT * FROM EMP");
                //String str="";
                StringBuilder sb=new StringBuilder();
                while (rs.next()){
                    int empno=rs.getInt("empno");
                    String name=rs.getString("ename");
                    String job=rs.getString("job");
                    double sal=rs.getDouble("sal");
                    int deptno=rs.getInt("deptno");
                    //str+=empno+" | "+name+" | "+job+" | "+sal+" | "+deptno; ⇒ 문자열 더하기는 객체를 많이 생성하기 때문에 메모리를 많이 사용함. 좋지 못한 방법.
                    sb.append(empno);
                    sb.append(" | ");
                    sb.append(name);
                    sb.append(" | ");
                    sb.append(job);
                    sb.append(" | ");
                    sb.append(sal);
                    sb.append(" | ");
                    sb.append(deptno);
                    sb.append(" | ");
                }
                    //System.out.println(str);
                System.out.println(sb.toString());
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
