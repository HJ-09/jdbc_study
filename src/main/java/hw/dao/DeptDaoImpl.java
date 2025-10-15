package hw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    @Override //부서수정
    public int update(DeptDto dto) throws SQLException {
        return 0;
    }

    @Override //부서조회
    public String findByNum(int deptno) throws SQLException {
        return "";
    }

    @Override //부서위치조회
    public List<DeptDto> findAll() throws SQLException {
        return List.of();
    }

}




class DeptDaoImplTest{
    public static void main(String[] args) {
        //과제 6. 테스트 시나리오 (등록→조회→수정→상세조회→삭제)

        try (Connection conn=DBFactory.getConn()){
            DeptDao deptDao=new DeptDaoImpl(conn);

            //등록
            DeptDto insertTest=new DeptDto(90,"백엔드","판교");
            int insert2= deptDao.insert(insertTest);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}