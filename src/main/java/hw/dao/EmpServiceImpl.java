package hw.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmpServiceImpl implements EmpService{
    private EmpDao empDao;

    private Connection conn;
    public EmpServiceImpl(Connection conn){
        this.conn=conn;
        this.empDao=new EmpDaoImpl(conn);
    }


    //사원등록
    @Override
    public boolean register(EmpDto emp) throws SQLException, IllegalArgumentException {

        //사번
        if(emp.getEmpno()<=0) throw new IllegalArgumentException("사번을 입력하세요.");
        EmpDto existNo=empDao.findByEmpno(emp.getEmpno());
        if (existNo!=null) throw new IllegalArgumentException("이미 등록된 사번입니다.");

        //이름
        if(emp.getEname()==null || emp.getEname().isBlank()) throw new IllegalArgumentException("이름을 입력하세요.");

        //입사일
        if(emp.getHiredate()==null) throw new IllegalArgumentException("입사일을 입력하세요.");

        //급여
        if (emp.getSal()!=null && emp.getSal()<=0) throw new IllegalArgumentException("급여는 0보다 커야합니다.");


        int register=empDao.insert(emp); //마지막에..!

        return register==1;
    }

    @Override
    public boolean modify(EmpDto emp) throws SQLException, IllegalArgumentException {
        return false;
    }

    //사원삭제
    @Override
    public boolean remove(int empno) throws SQLException {

        return empDao.delete(empno)==1;
    }

    @Override
    public List<EmpDto> readAll() throws SQLException, IllegalArgumentException {
        return List.of();
    }

    @Override
    public List<EmpDto> readByEname(String ename) throws SQLException, IllegalArgumentException {
        return List.of();
    }

    @Override
    public EmpDto readOne(int empno) throws SQLException, IllegalArgumentException {
        return empDao.findByEmpno(empno);
    }
}
