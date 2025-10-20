package hw.dao;

import java.sql.SQLException;
import java.util.List;

public interface EmpService {
    //구현할 기능이 뭐가 있을까 ~,~
    //사원등록 register(EmpDto emp) boolean or EmpDto 반환
    //사원수정 modify(EmpDto emp) boolean or EmpDto 반환
    //사원삭제 remove(empno) boolean
    //사원전체조회 readAll() List<EmpDto>
    //사원이름조회 readByEname List<EmpDto>
    //상세조회 readOne EmpDto


    //사원등록
    boolean register(EmpDto emp) throws SQLException, IllegalArgumentException;
    //사원수정
    boolean modify(EmpDto emp) throws SQLException, IllegalArgumentException;
    //사원삭제
    boolean remove(int empno) throws SQLException, IllegalArgumentException;

    //사원전체조회
    List<EmpDto> readAll() throws SQLException, IllegalArgumentException;
    //사원이름조회
    List<EmpDto> readByEname(String ename) throws SQLException, IllegalArgumentException;
    //상세조회
    EmpDto readOne(int empno) throws SQLException, IllegalArgumentException;


}
