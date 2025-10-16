package hw.dao;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl implements DeptDao {
    //과제 5. DeptDao 구현하기

    private final Connection conn;
    public DeptDaoImpl(Connection conn) {
        this.conn=conn;
    }


    @Override //부서등록
    public int insert(DeptDto dto) throws SQLException {
        int insert=0;
        String sql="INSERT INTO DEPT (DEPTNO, DNAME, LOC) VALUES (?,?,?)";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,dto.getDeptno());
            ps.setString(2,dto.getDname());
            ps.setString(3,dto.getLoc());
            insert=ps.executeUpdate();
        }
        return insert;
    }

    @Override //부서위치수정
    public int update(DeptDto dto) throws SQLException {
        int update=0;
        String sql="UPDATE DEPT SET LOC=? WHERE DEPTNO=?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,dto.getLoc());
            ps.setInt(2,dto.getDeptno());
            update=ps.executeUpdate();
        }
        return update;
    }

    @Override //부서조회
    public DeptDto findByNum(int deptno) throws SQLException {
        DeptDto findNum=null;
        String sql="SELECT * FROM DEPT WHERE DEPTNO=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,deptno);
            try (ResultSet rs=ps.executeQuery()){
                if (rs.next()){
                    int deptno1=rs.getInt("deptno");
                    String dname=rs.getString("dname");
                    String loc=rs.getString("loc");
                    findNum=new DeptDto();
                    findNum.setDeptno(deptno1);
                    findNum.setDname(dname);
                    findNum.setLoc(loc);
                }
            }
        }
        return findNum;
    }

    @Override //부서전체조회
    public List<DeptDto> findAll() throws SQLException {
        List<DeptDto> findAll=null;
        String sql="SELECT * FROM DEPT";
        try(PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery()){
            findAll=new ArrayList<>();
            while (rs.next()){
                int deptno=rs.getInt("deptno");
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");
                DeptDto dept=new DeptDto(deptno,dname,loc);
                findAll.add(dept);
            }
        }
        return findAll;
    }

    @Override //부서위치조회
    public List<DeptDto> findByLoc(String loc) throws SQLException {
        List<DeptDto> findByLoc=null;
        String sql="SELECT * FROM DEPT WHERE LOC=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,loc);
            try (ResultSet rs=ps.executeQuery()){
                findByLoc=new ArrayList<>();
                while(rs.next()){
                    int deptno=rs.getInt("deptno");
                    String dname=rs.getString("dname");
                    String loc1=rs.getString("loc");
                    DeptDto dept=new DeptDto(deptno,dname,loc1);
                    findByLoc.add(dept);
                }
            }
        }
        return findByLoc;
    }


    @Override //부서삭제
    public int delete(int deptno) throws SQLException {
        int delete=0;
        String sql="DELETE FROM DEPT WHERE DEPTNO=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,deptno);
            delete=ps.executeUpdate();
        }
        return delete;
    }

}




class DeptDaoImplTest{
    public static void main(String[] args) {
        //과제 6. 테스트 시나리오 ((삭제→)등록→조회→수정→상세조회→삭제)

        try (Connection conn=DBFactory.getConn()){
            DeptDao deptDao=new DeptDaoImpl(conn);

            //(삭제) //테스트용이니까 등록이 계속 되지 않게 맨 위에도 삭제
            int delNo=90;
            deptDao.delete(delNo);


            //① 등록
            DeptDto insertTest=new DeptDto(90,"백엔드","판교동");
            int insert2= deptDao.insert(insertTest);
            String msg=(insert2>0) ? "등록성공":"등록실패";
            System.out.println(msg);


            //② 전체조회
            System.out.println(deptDao.findAll());

            //위치조회
            System.out.println(deptDao.findByLoc("NEW YORK"));


            //③ 수정
            DeptDto updateTest=new DeptDto(90,"","가산동");
            int update2= deptDao.update(updateTest);
            String msg2=(update2>0)?"수정완료":"수정실패";
            System.out.println(msg2);

            //④ 상세조회
            DeptDto dept=deptDao.findByNum(90);
            System.out.println(dept);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}