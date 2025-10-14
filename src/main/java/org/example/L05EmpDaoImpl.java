package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class L05EmpDaoImpl implements L05EmpDAO{ //③번
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public L05EmpDaoImpl(Connection conn){
        this.conn=conn;
    }

    @Override
    public int insertOne(L05EmpDTO emp){
        return 0;
    }

    @Override
    public int updateOne(L05EmpDTO emp) throws SQLException {
        return 0;
    }

    @Override
    public int deleteOne(int emp) throws SQLException {
        return 0;
    }

    @Override
    public List<L05EmpDTO> findAll() throws SQLException {
        List<L05EmpDTO> empList=null;
        String sql="SELECT * FROM EMP";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();

        empList=new ArrayList<>();

        while (rs.next()){
            L05EmpDTO emp=new L05EmpDTO();
            emp.setEmpno(rs.getInt("empno"));
            emp.setEname(rs.getString("ename"));
            emp.setDeptno(rs.getInt("deptno"));
            empList.add(emp);
        }

        return empList;
    }

    @Override
    public List<L05EmpDTO> findByEname(String ename) throws SQLException {
        return List.of();
    }

    @Override
    public List<L05EmpDTO> findByEmpno(int empno) throws SQLException {
        return List.of();
    }
}
