package org.example;

import java.sql.SQLException;
import java.util.List;


//service 설계
public interface L09EmpService {
    //먼저 구현할 기능을 생각해보자
    //사원등록 register(EmpDto emp) boolean or EmpDto 반환
    //사원수정 modify(EmpDto emp) boolean or EmpDto 반환
    //사원삭제 remove(empno) boolean
    //사원전체조회 readAll() List<EmpDto>
    //사원이름조회 readByEname List<EmpDto>
    //상세조회 readOne EmpDto

    //서비스가 간단할수록 DAO와 service가 거의 유사
    //작은기업은 service를 구현하지 않는 경우도 잇음


    boolean register(L05EmpDTO emp) throws SQLException, IllegalArgumentException; //IllegalArgumentException ⇒ 입력오류. 사원의 이름이 없거나 글자수가 초과되거나 급여가 잘못 입력되거나 등의 오류를 잡아라!
    boolean modify(L05EmpDTO emp) throws SQLException, IllegalArgumentException;
    boolean remove(int empno) throws SQLException;

    List<L05EmpDTO> readAll() throws SQLException;
    List<L05EmpDTO> readByEname(String ename) throws SQLException;
    L05EmpDTO readOne(int empno) throws SQLException; //하나를 반환하는건 무조건 PK나 UK로!



}
