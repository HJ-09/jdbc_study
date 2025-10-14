package org.example;

import java.sql.SQLException;

public class L06EmpDaoTest {
    public static void main(String[] args) throws SQLException {
        //사원전체를 조회해보자
        L05EmpDAO empDAO=new L05EmpDaoImpl(L03DBFactory.getConn());
        System.out.println(empDAO.findAll());
    }
}
