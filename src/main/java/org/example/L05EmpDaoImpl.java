package org.example;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class L05EmpDaoImpl implements L05EmpDAO{ //③번
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public L05EmpDaoImpl(Connection conn){
        this.conn=conn;
    }


    @Override
    public int insertOne(L05EmpDTO emp) throws SQLException {
        int insertOne=0;
        String sql="INSERT INTO EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,emp.getEmpno());
            ps.setString(2,emp.getEname());
            ps.setString(3,emp.getJob());
            ps.setObject(4,emp.getMgr()); //mgr 값이 null일 수도 있으니 setInt아니고 setObject.
            //ps.setDate(5,emp.getHiredate());
            //java.sql.Date → LocalDate
            //LocalDate → java.sql.Date
            //ps.setString(5,emp.getHiredate().toString()); //오라클은 .. 뭐 어쩌고 쨌든 ㅎㅎ 이렇게 해도 됨
            java.sql.Date hiredate=null;
            if (emp.getHiredate()!=null) hiredate=java.sql.Date.valueOf(emp.getHiredate());
            ps.setDate(5,hiredate);
            ps.setObject(6,emp.getSal());
            ps.setObject(7,emp.getComm());
            ps.setObject(8,emp.getDeptno());
            insertOne=ps.executeUpdate();
        }

        return insertOne;
    }

    @Override
    public int updateOne(L05EmpDTO emp) throws SQLException {
        int updateOne=0;
        String sql="UPDATE EMP SET ENAME=?, JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=? WHERE EMPNO=?"; //※※empno는 pk라서 고쳐쓰는거 아님 절대!!!※※
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,emp.getEname());
            ps.setString(2,emp.getJob());
            ps.setObject(3,emp.getMgr());
            ps.setString(4,(emp.getHiredate()!=null) ? emp.getHiredate().toString() : null); //삼항연산자 물음표 앞에 소괄호 생략해도 됨
            ps.setObject(5,emp.getSal());
            ps.setObject(6,emp.getComm());
            ps.setObject(7,emp.getDeptno());
            ps.setInt(8,emp.getEmpno());
            updateOne=ps.executeUpdate();
        }
        return updateOne;
    }

    @Override
    public int deleteOne(int empno) throws SQLException {
        int deleteOne=0;
        String sql="DELETE FROM EMP WHERE empno=?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,empno);
            deleteOne=ps.executeUpdate();
        }
        return deleteOne;
    }

    @Override
    public List<L05EmpDTO> findAll() throws SQLException {
        List<L05EmpDTO> empList=null;
        String sql="SELECT * FROM EMP";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();

        empList=new ArrayList<>();

        while (rs.next()){
            L05EmpDTO emp=new L05EmpDTO();
            /*emp.setEmpno(rs.getInt("empno"));
            emp.setEname(rs.getString("ename"));
            emp.setDeptno(rs.getInt("deptno"));*/

            int empno=rs.getInt("empno");
            String ename=rs.getString("ename");
            //자바는 기본형이 null을 참조하는 것이 불가능함. 기본형의 없음은 0임.
            //기본형타입의 자료형 랩퍼클래스(Byte,Short,Integer,Long,Float,Double)를 사용해야 0이 아니라 null을 쓸 수 잇듬.
            //int deptno=rs.getInt("deptno");
            //int mgr=rs.getInt("mgr");
            String job=rs.getString("job");

            //Integer deptno=(Integer) rs.getObject("deptno"); //이렇게 써야지 null을 받을 수 잇듬.
            BigDecimal deptnoDec=rs.getBigDecimal("deptno");
            Integer deptno=(deptnoDec!=null)?deptnoDec.intValue():null;

            //Integer mgr=(Integer) rs.getObject("mgr"); //Integer을 받으려면 getInteger()없어서 getObject() 써야함. 근데 Integer보다 Object가 더 커서 강제 형변환 (Integer) 해야함.
            BigDecimal mgrDec=rs.getBigDecimal("mgr");
            Integer mgr=(mgrDec!=null)? mgrDec.intValue():null;

            //Double sal=(Double) rs.getObject("sal");
            BigDecimal salDec=rs.getBigDecimal("sal");
            Double sal=(salDec!=null) ? salDec.doubleValue():null;

            //Double comm=(Double) rs.getObject("comm");
            BigDecimal commDec=rs.getBigDecimal("comm");
            Double comm=(commDec!=null)?commDec.doubleValue():null;
            //값이 0이 아니라 null 일 수 있는 기본형 데이터 필드는 Object로 가져와서 랩퍼클래스로 변환해줘야 값이 0이 아니라 null로 나올 수 잇음!!

            //ResultSet은 DB에서 가져온 Date를 java.sql.Date 또는 java.util.Date로 반환함.
            //그런데 요즘 자바에서는 LocalDate 시리즈를 많이 사용함 !~!
            java.sql.Date hiredate=rs.getDate("hiredate");
            LocalDate hiredateLocal=(hiredate!=null) ? hiredate.toLocalDate():null; //와... 머찐코드다...
            //LocalDate.parse("2025-09-25)" 이렇게 해도 파싱됨.. 이해한게 맞나?ㅋㅋ
            emp.setEmpno(empno);
            emp.setEname(ename);
            emp.setJob(job);
            emp.setMgr(mgr);
            emp.setComm(comm);
            emp.setSal(sal);
            emp.setDeptno(deptno);
            emp.setHiredate(hiredateLocal);
            empList.add(emp);
        }

        return empList;
    }

    //findByLikeEname
    //String sql="SELECT * FROM EMP WHERE ename LIKE %k%"; //k를 포함한 사람 찾기
    //String sql="SELECT * FROM EMP WHERE ename LIKE '%k%'"; //k를 포함한 사람 찾기
    //String sql="SELECT * FROM EMP WHERE ename LIKE %'k'%"; //오류
    //String sql="SELECT * FROM EMP WHERE ename LIKE %?%"; //오류
    //String sql="SELECT * FROM EMP WHERE ename LIKE %||?||%"; //오라클에서 문자열 더하기

    @Override
    public List<L05EmpDTO> findByEname(String ename) throws SQLException {
        List<L05EmpDTO> emps=null;
        String sql="SELECT * FROM EMP WHERE ename=?";
        //String sql="SELECT * FROM EMP WHERE UPPER(enam)e=UPPER(?)"; //UPPDER자리에 LOWER 넣어도 됨. 대소문자 구분 없이 결과값 내기 위한 작업임 뀨
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,ename);
            try (ResultSet rs=ps.executeQuery()){
                emps=new ArrayList<>();
                while (rs.next()){
                    L05EmpDTO emp=new L05EmpDTO();
                    emp.setEmpno(rs.getInt("empno"));
                    emp.setEname(rs.getString("ename"));
                    emp.setJob(rs.getString("job"));
                    if (rs.getDate("hiredate")!=null) emp.setHiredate(rs.getDate("hiredate").toLocalDate());
                    if (rs.getBigDecimal("comm")!=null) emp.setComm(rs.getDouble("comm"));
                    if (rs.getObject("sal")!=null) emp.setSal(rs.getDouble("sal"));
                    if (rs.getObject("mgr")!=null) emp.setMgr(rs.getInt("mgr"));
                    if (rs.getObject("deptno")!=null) emp.setDeptno(rs.getInt("deptno"));
                    emps.add(emp);
                }
            }
        }
        return emps;
    }


    //mapRow로 작업해보기 레츠꼬
    @Override
    public L05EmpDTO findByEmpno(int empno) throws SQLException {
        L05EmpDTO emp=null;
        String sql="SELECT * FROM EMP WHERE empno=?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,empno);
            try (ResultSet rs=ps.executeQuery()){
                while (rs.next()){
                    emp=mapRow(rs);

                }
            }
        }
        return emp;
    }

    public L05EmpDTO mapRow(ResultSet rs) throws SQLException{
        //L05EmpDTO emp=null; //⇒ 어차피 rs는 next()가 있을 때만 쓸거니까 이렇게 안해도 됨.
        L05EmpDTO emp=new L05EmpDTO();
        emp.setEmpno(rs.getInt("empno"));
        emp.setEname(rs.getString("ename"));
        emp.setJob(rs.getString("job"));
        if (rs.getDate("hiredate")!=null) emp.setHiredate(rs.getDate("hiredate").toLocalDate());
        if (rs.getBigDecimal("comm")!=null) emp.setComm(rs.getDouble("comm"));
        if (rs.getObject("sal")!=null) emp.setSal(rs.getDouble("sal"));
        if (rs.getObject("mgr")!=null) emp.setMgr(rs.getInt("mgr"));
        if (rs.getObject("deptno")!=null) emp.setDeptno(rs.getInt("deptno"));


        return emp;
    }
}
