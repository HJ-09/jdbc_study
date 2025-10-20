package org.example;

import hw.dao.EmpDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//service 구현
public class L09EmpServiceImpl implements L09EmpService{
    //private L05EmpDaoImpl empDaoImpl; //x
    private L05EmpDAO empDAO; //의존성 역전의 원칙으로 인해서 EmpDaoImpl이 아니라 EmpDao를 받는 것.
    //DIP: 의존성 역전의 원칙 → 서비스가 다른 dao 구현체를 사용할 수 있는 유연성 ⇒ 재사용 多
    private L07DeptDAO deptDAO; //부서번호 검색 레츠고


    private Connection conn; //사용자가 생성해서 전달
    public L09EmpServiceImpl(Connection conn){
        this.conn=conn;
        this.empDAO=new L05EmpDaoImpl(conn); //connection이 무조건 필요해ㅐㅐㅐ
        this.deptDAO=new L07DeptDaoImpl(conn);
    }


    //트랜잭션: 데이터 수정이 2번 이상 일어날 때 문제가 생기면 세이브 포인트로 되돌림.
    //register은 2번 이상 수정(DML insert, update, delete, ...)이 발생하지 않기 때문에 transaction이 필요하지 않음. 뀨
    //하쥐만 수업이니까 transaction 해볼게 ~ ~ ~ ~ ~


    @Override
    public boolean register(L05EmpDTO emp) throws SQLException, IllegalArgumentException {

        //트랜잭션 레츠고
        try{
            //JDBC conn의 설정 -> autoCommit(true)
            conn.setAutoCommit(false); //AutoCommit은 commit 지점을 자동으로 다 만들어둠. 그래서 꺼야혀~
            conn.commit(); //세이브포인트 ! ! !

            //▼▼ 유효성검사 ▼▼
            //사번, 이름이 있는지 검사 레츠고
            //이름은 글자수가 10 이하인지 검사 레츠고
            //급여, 커미션 있으면 0보다 작지 않은지 검사 레츠고

            //사번이 이미 있는지 검사 레츠고(pk검사)
            //부서번호가 있다면 실제 존재하는 부서번호인지 검사 레츠고(참조의 무결성)
            //상사번호가 없으면 오류(참조의 무결성)
            //검사 끝났다면 등록 성공~

            if (emp.getEmpno()<=0) throw new IllegalArgumentException("사번을 입력하세요.");
            //String.isBlank() ⇒ "","   "
            if (emp.getEname()==null || emp.getEname().isBlank()) throw new IllegalArgumentException("이름을 입력하세요.");

            if (emp.getSal()!=null && emp.getSal()<=0) throw new IllegalArgumentException("급여는 0보다 커야합니다.");
            if (emp.getComm()!=null && emp.getComm()<=0) throw new IllegalArgumentException("커미션은 0보다 커야합니다.");


            L05EmpDTO existEmp=empDAO.findByEmpno(emp.getEmpno()); //등록하기 전에 등록되어 있는지 물어보는 것
            if (existEmp!=null) throw new IllegalArgumentException("이미 등록된 사번입니다.");

            if(emp.getDeptno()!=null){
                L07DeptDTO existDept=deptDAO.findByDeptDTO(emp.getDeptno()); //부서가 있는지?
                List<L07DeptDTO> depts=deptDAO.findAll();
                List<Integer> deptno=new ArrayList<>();
                for (L07DeptDTO dept:depts){
                    deptno.add(dept.getDeptno());
                }
                if (existDept==null) throw new IllegalArgumentException("존재하는 부서번호를 입력하세요."+deptno);
            }


            //if (emp.getMgr()==null) throw new IllegalArgumentException("상사번호를 입력하세요."); //상사번호는 null을 허용하니까 굳이 안 물어봐도 되고
            if (emp.getMgr()!=null){ //null이 아니면
                L05EmpDTO existMgr=empDAO.findByEmpno(emp.getMgr()); //상사번호가 존재하는 번호인지 확인하고,
                if (existMgr==null) throw new IllegalArgumentException("존재하지 않는 상사의 사번입니다."); //만약에 존재하지 않는 번호라면 메세지 출력
            }

            int insert=empDAO.insertOne(emp);

            return insert==1;

        } catch (Exception e){
            conn.rollback(); //오류 뜨면 commit으로 rollback 레츠고
            throw e; //어떤 오류가 뜨던 오류가 뜨면 오류를 바로 위임하겠다!
            //꼭!! 다 작업하고서 throw 해야함. 안 그러면 작업 마무리까지 못하고 오류 위임 하게됨..~
        }


    }

    @Override
    public boolean modify(L05EmpDTO emp) throws SQLException, IllegalArgumentException {
        //이름 썼나?
        if (emp.getEname()==null || emp.getEname().isBlank()) throw new IllegalArgumentException("이름을 입력하세요.");

        //사번은 썼나?
        if (emp.getEmpno()<=0) throw new IllegalArgumentException("수정할 사번을 입력하세요.");

        //없는 사번이면 수정 안되게!
        L05EmpDTO existNo=empDAO.findByEmpno(emp.getEmpno());
        if (existNo==null) throw new IllegalArgumentException("존재하지 않는 사번입니다.");

        //급여는 썼나?
        if (emp.getSal()<=0 || emp.getSal()==null) throw new IllegalArgumentException("급여는 0보다 커야합니다.");

        //커미션은?
        if (emp.getComm()<0 || emp.getComm()==null) throw new IllegalArgumentException("커미션은 0보다 커야합니다.");

        //유효성 검사(Valid, Validation)

        //상사번호는?
        if (emp.getMgr()!=null){ //null이 아니면
            L05EmpDTO existMgr=empDAO.findByEmpno(emp.getMgr()); //상사번호가 존재하는 번호인지 확인하고,
            if (existMgr==null) throw new IllegalArgumentException("존재하지 않는 상사의 사번입니다."); //만약에 존재하지 않는 번호라면 메세지 출력
        }

        //부서번호는?
        if(emp.getDeptno()!=null){
            L07DeptDTO existDept=deptDAO.findByDeptDTO(emp.getDeptno());
            if (existDept==null) throw new IllegalArgumentException("없는 부서번호 입니다. 번호를 확인하세요.");
        }


        /*int modify=empDAO.updateOne(emp);
        return modify==1;*/
        return empDAO.updateOne(emp)==1;
    }


    //▼▼▼ service와 dao가 1대 1로 대칭하는 경우(서비스가 단순한 경우) ▼▼▼
    @Override
    public boolean remove(int empno) throws SQLException {

        return (empDAO.deleteOne(empno)==1);
    }

    @Override
    public List<L05EmpDTO> readAll() throws SQLException {
        return empDAO.findAll();
    }

    @Override
    public List<L05EmpDTO> readByEname(String ename) throws SQLException {
        return empDAO.findByEname(ename);
    }

    @Override
    public L05EmpDTO readOne(int empno) throws SQLException {


        return empDAO.findByEmpno(empno);
    }
}
