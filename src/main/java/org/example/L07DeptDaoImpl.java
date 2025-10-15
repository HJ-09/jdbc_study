package org.example;

//세번째

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class L07DeptDaoImpl implements L07DeptDAO{
    //DAO를 만들려면? 접속부터 레츠꼬
    //근데 우리는 미리 커넥션 만들어뒀으니 그걸로 접속 레츠고
    private final Connection conn;

    public L07DeptDaoImpl(Connection conn){
        this.conn=conn;
    }


    //밑에 오버라이드들 있는 이유는 클래스(L07DeptDaoImpl)에서는 추상메서드 포함할 수 없으니까 강제로 메서드 구현한거임
    @Override
    public List<L07DeptDTO> findAll() throws SQLException{
        List<L07DeptDTO> depts=null;
        String sql="SELECT * FROM DEPT";
        try (Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql);
            ){
            depts=new ArrayList<>();
            while (rs.next()){
                int deptno=rs.getInt("deptno");
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");
                L07DeptDTO deptDTO=new L07DeptDTO(deptno, dname, loc);
                depts.add(deptDTO);
            }
        }
        return depts;
    }

    @Override
    public L07DeptDTO findByDeptDTO(int deptno) throws SQLException {
        L07DeptDTO detp=null;
        String sql="SELECT * FROM DEPT WHERE DEPTNO=?";
        try (PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,deptno);
            try (ResultSet rs=pstmt.executeQuery()){
                while (rs.next()){ //결과가 List(복수)가 아니고 1개이기 때문에 while말고 if 써도 됌!!
                    int deptno2=rs.getInt("deptno");
                    String dname=rs.getString("dname");
                    String loc=rs.getString("loc");
                    detp=new L07DeptDTO(deptno2,dname,loc);
                }
            }

        }
        return detp;
    }

    @Override
    public int insertOne(L07DeptDTO deptDTO) throws SQLException {
        int insertOne=0;
        String sql="INSERT INTO DEPT (DEPTNO, DNAME, LOC) VALUES (?,?,?)";
        try (PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,deptDTO.getDeptno());
            pstmt.setString(2, deptDTO.getDname());
            pstmt.setString(3, deptDTO.getLoc());
            insertOne=pstmt.executeUpdate(); //dml 실행.. long 또는 int가 나온다...?
        }
        return insertOne;
    }

    @Override
    public int updateOne(L07DeptDTO deptDTO) throws SQLException {
        int updateOne=0;
        String sql="UPDATE DEPT SET DNAME=?, LOC=? WHERE DEPTNO=?";
        try (PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setString(1, deptDTO.getDname());
            pstmt.setString(2, deptDTO.getLoc());
            pstmt.setInt(3,deptDTO.getDeptno());
            updateOne=pstmt.executeUpdate();
        }

        return updateOne;
    }

    @Override
    public int deleteOne(int deptno) throws SQLException {
        int deleteOne=0;
        String sql="DELETE FROM DEPT WHERE DEPTNO=?";
        try (PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,deptno);
            deleteOne=pstmt.executeUpdate();
        }
        return deleteOne;
    }


}
