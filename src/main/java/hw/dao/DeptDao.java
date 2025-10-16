package hw.dao;

import java.sql.SQLException;
import java.util.List;

public interface DeptDao {
    //과제 4. 부서관리 앱에서 사용될 CRUD 설계하기
    //부서등록, 부서수정, 부서조회, 부서위치조회, 부서삭제

    int insert(DeptDto dto) throws SQLException; //부서등록
    int update(DeptDto dto) throws SQLException; //부서수정
    DeptDto findByNum(int deptno) throws SQLException; //부서조회
    List<DeptDto> findAll() throws SQLException; //부서전체조회
    List<DeptDto> findByLoc(String loc) throws SQLException; //부서위치조회
    int delete(int deptno) throws SQLException; //부서삭제

}
