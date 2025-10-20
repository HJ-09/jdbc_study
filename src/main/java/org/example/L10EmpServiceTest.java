package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class L10EmpServiceTest {
    public static void main(String[] args) {
        try(Connection conn=L03DBFactory.getConn();){
            L09EmpService empService=new L09EmpServiceImpl(conn);

            System.out.println("readAll Test ▼▼▼");
            System.out.println(empService.readAll());

            System.out.println("readByEname Test ▼▼▼");
            System.out.println(empService.readByEname("KING"));


            System.out.println(empService.remove(6666));

            System.out.println();
            System.out.println("register Test ▼▼▼");
            L05EmpDTO emp=new L05EmpDTO();
            emp.setEmpno(6666);
            emp.setEname("테스트");
            emp.setSal(500.0);
            emp.setComm(99.0);
            emp.setDeptno(90); //55번 ⇒ 없음. 참조의 무결성 위반(부모키가 없음)
            emp.setMgr(1111);
            System.out.println("등록성공 : "+empService.register(emp));


            System.out.println();
            System.out.println("remove Test ▼▼▼");



            System.out.println();
            System.out.println("modify Test ▼▼▼");
            L05EmpDTO emp2=new L05EmpDTO();
            emp2.setEname("devil");
            emp2.setEmpno(4444);
            emp2.setSal(800.0);
            emp2.setComm(0.0);
            emp2.setMgr(2222);
            emp2.setDeptno(90);
            System.out.println("수정성공: "+empService.modify(emp2));



            System.out.println();
            System.out.println("readOne Test ▼▼▼");
            System.out.println(empService.readOne(4444));


            System.out.println(empService.remove(6666));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
