package hw.dao;

import java.sql.Connection;
import java.time.LocalDate;

public class EmpServiceTest {
    static void main(String[] args) {

        try (Connection conn=DBFactory.getConn()){
            EmpService empService=new EmpServiceImpl(conn);

            System.out.println("7777 사원 삭제"+empService.remove(7777));

            System.out.println(empService.readOne(7777));

            System.out.println("register Test ▼▼▼");
            EmpDto emp=new EmpDto();
            emp.setEname("CEW"); //이름
            emp.setEmpno(7777); //사번
            emp.setHiredate(LocalDate.of(2023,12,5));
            emp.setSal(0.0);
            System.out.println(empService.register(emp));
            //empService.register(emp) 이게 있어야 IllegalArgumentException 오류가 잡힘


            System.out.println("7777 사원 삭제"+empService.remove(7777));

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
