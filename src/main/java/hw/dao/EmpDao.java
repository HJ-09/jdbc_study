package hw.dao;

import java.sql.SQLException;
import java.util.List;

public interface EmpDao {
    //사원등록, 사원수정, 사원삭제, 사원조회,...

    int insert(EmpDto dto) throws SQLException; //사원등록
    int update(EmpDto dto) throws SQLException; //사원수정
    int delete(int empno) throws SQLException; //사원삭제
    EmpDto findByEmpno(int empno) throws SQLException; //사원번호로 조회
    List<EmpDto> findByJob(String job) throws SQLException; //직책으로 조회
    List<EmpDto> findByDeptno(int deptno) throws SQLException; //부서번호로 조회

}
