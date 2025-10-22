package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class L11EmpValidBeanTest {
    public static void main(String[] args) {
        L11EmpValidBean empValidBean=new L11EmpValidBean();
//        empValidBean.setEname(""); //이름을 입력하세요.
//        empValidBean.setEname("제가지금그냥자고싶어요"); //이름은 10자 이하여야 합니다.

        empValidBean.setEmpno(5555);
        empValidBean.setEname("이주헌");
        empValidBean.setMgr(7788);
        empValidBean.setDeptno(20);
        empValidBean.setSal(1000.0);
        empValidBean.setComm(10.0);

        try (Connection conn=L03DBFactory.getConn()){
            L09EmpService empService=new L09EmpServiceImpl(conn);

            System.out.printf("삭제 성공: "+empService.remove(5555));

            System.out.printf("등록 성공: "+empService.register(empValidBean));
            //System.out.printf(empService.readOne(5555)); //왜 오류나는지..?
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
