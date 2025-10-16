package hw.dao;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpDaoImpl implements EmpDao{

    private final Connection conn;
    public EmpDaoImpl(Connection conn) {
        this.conn=conn;
        try {
            System.out.println(conn.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override //사원등록
    public int insert(EmpDto dto) throws SQLException {
        int insert=0;
        String sql="INSERT INTO EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES(?,?,?,?,?,?,?,?)"; //적어야하는 내용들 다 적어야 함. 그게 전부(ALL)여도!
        //INSERT INTO SCOTT.EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
        // VALUES (7133, 'KIM', 'CLERK', null, DATE '2025-01-01', 100.00, null, 20)
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,dto.getEmpno());
            ps.setString(2,dto.getEname());
            ps.setString(3,dto.getJob());
            ps.setObject(4,dto.getMgr());
            if(dto.getHiredate()!=null){
//                LocalDate hiredate=dto.getHiredate();
//                java.sql.Date hiredateDate=java.sql.Date.valueOf(hiredate);
//                ps.setDate(5,hiredateDate);
                ps.setDate(5, Date.valueOf(dto.getHiredate()));
            }else{
                ps.setObject(5,null);
            }
            //setDate=> java.sql.Date
            //LocalDate =>"2025-09-25"
            ps.setDouble(6,dto.getSal());
            ps.setObject(7,dto.getComm());
            ps.setObject(8,dto.getDeptno());
            insert=ps.executeUpdate();
        }
        return insert;
    }

    @Override //수정
    public int update(EmpDto dto) throws SQLException {
        int update=0;
        //[2025-10-16 16:00:46] SCOTT> UPDATE EMP
        // SET ENAME = '장동윤', JOB = 'SALESMAN', MGR = 7369, HIREDATE = DATE '2025-10-15', SAL = 2000.00, COMM = 100.00, DEPTNO = 10
        // WHERE EMPNO = 1225
        String sql="UPDATE EMP SET ENAME = ?, JOB = ?, MGR = ?, HIREDATE = ?, SAL = ?, COMM = ?, DEPTNO = ? WHERE EMPNO = ?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,dto.getEname());
            ps.setString(2,dto.getJob());
            ps.setObject(3,dto.getMgr());
            ps.setDate(4,Date.valueOf(dto.getHiredate()));
            ps.setDouble(5,dto.getSal());
            ps.setObject(6,dto.getComm());
            ps.setObject(7,dto.getDeptno());
            ps.setInt(8,dto.getEmpno());
            System.out.println("실행중");

            update=ps.executeUpdate();
            System.out.println("실행완료");
        }
        return update;
    }

    @Override //삭제
    public int delete(int empno) throws SQLException {
        int delete=0;
        String sql="DELETE FROM EMP WHERE EMPNO=?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,empno);
            delete=ps.executeUpdate();
        }
        return delete;
    }

    @Override //사원번호로 조회..?
    public EmpDto findByEmpno(int empno) throws SQLException {
        EmpDto findByEmpno=null;
        String sql="SELECT * FROM EMP WHERE EMPNO=?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,empno);
            try (ResultSet rs= ps.executeQuery()){
                if(rs.next()){
                    int empno1=rs.getInt("empno");
                    String ename=rs.getString("ename");
                    String job=rs.getString("job");
                    Integer mgr=rs.getInt("mgr");
                    Date hiredate=rs.getDate("hiredate");
                    Double sal=rs.getDouble("sal");
                    Double comm=rs.getDouble("comm");
                    Integer deptno=rs.getInt("deptno");
                    findByEmpno=new EmpDto();
                    findByEmpno.setEmpno(empno1);
                    findByEmpno.setEname(ename);
                    findByEmpno.setJob(job);
                    findByEmpno.setMgr(mgr);
                    if(hiredate!=null) findByEmpno.setHiredate(hiredate.toLocalDate());
                    findByEmpno.setSal(sal);
                    findByEmpno.setComm(comm);
                    findByEmpno.setDeptno(deptno);
                }
            }
        }
        return findByEmpno;
    }

    @Override //직책으로 조회
    public List<EmpDto> findByJob(String job) throws SQLException {
        List<EmpDto> findByJob=null;
        String sql="SELECT * FROM EMP WHERE JOB=?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,job);
            try (ResultSet rs=ps.executeQuery()){
                    findByJob=new ArrayList<>();
                while (rs.next()){
                    int empno=rs.getInt("empno");
                    String ename=rs.getString("ename");
                    Integer deptno=rs.getInt("deptno");
                    EmpDto empDto=new EmpDto();
                    empDto.setEmpno(empno);
                    empDto.setEname(ename);
                    empDto.setDeptno(deptno);
                    findByJob.add(empDto);
                }
            }
        }
        return findByJob;
    }

    @Override //부서번호로 조회
    public List<EmpDto> findByDeptno(int deptno) throws SQLException {
        List<EmpDto> findByDeptno=null;
        String sql="SELECT * FROM EMP WHERE DEPTNO=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,deptno);
            try (ResultSet rs=ps.executeQuery()){
                findByDeptno=new ArrayList<>();
                while (rs.next()){
                    EmpDto empDto1=mapRow(rs);
                    findByDeptno.add(empDto1);
                }
            }
        }
        return findByDeptno;
    }



    public EmpDto mapRow(ResultSet rs) throws SQLException {
        EmpDto emp=new EmpDto();

        int empno1=rs.getInt("empno");
        String ename=rs.getString("ename");
        String job=rs.getString("job");
        Integer mgr=rs.getInt("mgr");
        Date hiredate=rs.getDate("hiredate");
        Double sal=rs.getDouble("sal");
        Double comm=rs.getDouble("comm");
        Integer deptno=rs.getInt("deptno");
        emp=new EmpDto();
        emp.setEmpno(empno1);
        emp.setEname(ename);
        emp.setJob(job);
        emp.setMgr(mgr);
        if(hiredate!=null) emp.setHiredate(hiredate.toLocalDate());
        emp.setSal(sal);
        emp.setComm(comm);
        emp.setDeptno(deptno);


        return emp;
    }
}


class EmpDaoImplTest{
    public static void main(String[] args) {
        try (Connection conn=DBFactory.getConn()){
            EmpDao empDao=new EmpDaoImpl(conn);

            int delEmpno=empDao.delete(1225);
            empDao.delete(delEmpno);

            //등록
            EmpDto insertTest=new EmpDto();
            insertTest.setEmpno(1225);
            insertTest.setEname("김혜지");
            insertTest.setJob("CLERK");
            insertTest.setMgr(null);
            insertTest.setHiredate(LocalDate.parse("2024-03-23"));
            insertTest.setSal(10000);
            insertTest.setComm(null);
            insertTest.setDeptno(90);
            int insert=empDao.insert(insertTest);


            //수정
            //EmpDto updateTest=new EmpDto();
            EmpDto updateTest=new EmpDto();
            updateTest.setEmpno(1225);
            updateTest.setEname("혜지");
            updateTest.setJob("CLERK2");
            updateTest.setMgr(7788);
            updateTest.setHiredate(LocalDate.parse("2024-03-03"));
            updateTest.setSal(22222);
            updateTest.setComm(2222.0);
            updateTest.setDeptno(10);
            System.out.println("수정중 :"+updateTest);
            int update=empDao.update(updateTest);
            System.out.println(update);


            //사원번호로 조회
            EmpDto empDto=empDao.findByEmpno(1225);
            System.out.println(empDto);


            //직책으로 조회
            System.out.println(empDao.findByJob("SALESMAN"));



            //부서번호로 조회
            System.out.println("---");
            List<EmpDto> empDto1=empDao.findByDeptno(20);
            System.out.println(empDto1);


            //삭제
            delEmpno=1225;
            empDao.delete(delEmpno);



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}