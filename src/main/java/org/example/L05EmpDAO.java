package org.example;

import java.sql.SQLException;
import java.util.List;

public interface L05EmpDAO { //②번
//    사원 관리자 페이지를 만들 예정인데 어떤 기능이 필요할까?
//    사원등록, 사원수정, 사원삭제, 사원전체조회, 사원이름조회, 사원상세(사원번호조회)


    //public int insertOne(int empno, String ename, ...); //일일이 타자칠 수 없잖아 DTO 만들어뒀으니까~
    public int insertOne(L05EmpDTO emp) throws SQLException;
    public int updateOne(L05EmpDTO emp) throws SQLException;
    public int deleteOne(int empno) throws SQLException;

    //SELECT * FROM EMP;
    public List<L05EmpDTO> findAll() throws SQLException;
    public List<L05EmpDTO> findByEname(String ename) throws SQLException;
    public L05EmpDTO findByEmpno(int empno) throws SQLException; //findByOne, findByPk,.. 뭐로 하든 상관없음

    //구현해보자


}
