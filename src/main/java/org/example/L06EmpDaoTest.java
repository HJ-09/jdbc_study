package org.example;

import java.sql.SQLException;
import java.time.LocalDate;

public class L06EmpDaoTest {
    public static void main(String[] args) throws SQLException {
        //사원전체를 조회해보자
        L05EmpDAO empDAO=new L05EmpDaoImpl(L03DBFactory.getConn());

        System.out.println("삭제할게");
        int del=empDAO.deleteOne(7777);
        if (del==1) { //del>0 이라고 해도 댐
            System.out.println("삭제성공");
        }else {
            System.out.println("대상없음");
        }

        System.out.println();
        L05EmpDTO emp=new L05EmpDTO();
        emp.setEmpno(7777);
        emp.setEname("손흥민");
        emp.setJob("PLAYER");
        emp.setDeptno(30); //만약 없는 부서번호를 참조하면 참조의 무결성 때문에 오류가 날거임
        emp.setMgr(null); //null이면 안 써도 댐.
        emp.setComm(2000.0);
        emp.setSal(3000.0);
        emp.setHiredate(LocalDate.of(2022,7,10));
        empDAO.insertOne(emp);
        System.out.println("등록할게");
        System.out.println();
        System.out.println("전체조회");
        System.out.println(empDAO.findAll());

        System.out.println("이름조회");
        System.out.println(empDAO.findByEname("KING"));

        System.out.println();
        System.out.println("사번조회");
        System.out.println(empDAO.findByEmpno(7777));

        System.out.println();
        System.out.println("수정할게");
        emp.setEname("손흥만");
        emp.setJob("DEVELOPER");
        emp.setDeptno(20); //만약 없는 부서번호를 참조하면 참조의 무결성 때문에 오류가 날거임
        emp.setMgr(7900); //null이면 안 써도 댐.
        emp.setComm(0.0);
        emp.setSal(4000.0);
        emp.setHiredate(LocalDate.of(2024,6,25));
        int update=empDAO.updateOne(emp);
        if (update>0){
            System.out.println("수정성공");
        }else {
            System.out.println("수정실패");
        }
        System.out.println(empDAO.findByEmpno(7777));


        System.out.println();
        del=empDAO.deleteOne(7777);
        if (del==1) { //del>0 이라고 해도 댐
            System.out.println("삭제성공");
        }else {
            System.out.println("대상없음");
        }

    }
}
