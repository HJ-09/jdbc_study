package org.example;

//네번째

import java.sql.Connection;
import java.util.List;

public class L08DeptDaoTest {
    public static void main(String[] args) {
        try (Connection conn=L03DBFactory.getConn()){ //auto-close를 위해서 소괄호에..
            L07DeptDAO deptDAO=new L07DeptDaoImpl(conn);

            //삭제
            int delete=deptDAO.deleteOne(50);
            String delMsg=(delete>0)?"삭제성공":"삭제실패";
            System.out.println(delMsg);


            //등록
            L07DeptDTO insertDept=new L07DeptDTO(50,"백엔드","가산동");
            int insert=deptDAO.insertOne(insertDept);
            String msg=(insert>0)?"등록성공":"등록실패";


            //전체조회
            List<L07DeptDTO> depts=deptDAO.findAll();
            System.out.println(depts);
            System.out.println(msg);


            //수정
            L07DeptDTO updateDept=new L07DeptDTO(50,"프론트","판교동");
            int update=deptDAO.updateOne(updateDept);
            String msg2=(update>0)?"수정성공":"수정실패";
            System.out.println(msg2);


            //상세조회
            L07DeptDTO dept=deptDAO.findByDeptDTO(50);
            System.out.println(dept);


            //삭제
            delete=deptDAO.deleteOne(50);
            delMsg=(delete>0)?"삭제성공":"삭제실패";
            System.out.println(delMsg);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
