package org.example;

//두번째

import java.sql.SQLException;
import java.util.List;

public interface L07DeptDAO {
    //부서전체조회(SELECT * FROM DEPT;),
    //부서상세조회(SELECT * FROM DEPT WHERE DEPTNO=?;),
    //부서등록(INSERT INTO (deptno, dname, loc) VALUES (?,?,?);),
    //부서수정(UPDATE DEPT SET dname=?, loc=? WHERE DEPTNO=?;) //deptno는 왜 SET 안 하느냐 PK이니께.
        //부서이름수정(UPDATE DEPT SET dname=? WHERE DEPTNO=?;)
        //부서위치수정(UPDATE DEPT SET loc=? WHERE DEPTNO=?;)
    //부서삭제(DELETE FROM DEPT WHERE DEPTNO=?;)

    //설계 레츠고
    List<L07DeptDTO> findAll() throws SQLException;
    L07DeptDTO findByDeptDTO(int deptno) throws SQLException;
    int insertOne(L07DeptDTO deptDTO) throws SQLException;
    int updateOne(L07DeptDTO deptDTO) throws SQLException;
    int deleteOne(int deptno) throws SQLException;

}




//과거 수업 내용 다시 한 번 설명
@FunctionalInterface //람다식으로 익명클래스를 대체함 뀨
//interface: 객체가 될 수 없음
interface B{
    //interface는 완전 미완성. 가장 추상화된 설계도!
    //필드도 만들 수 업뜸!!
    int a=10; //클래스상수만 정의가능
//    public static final int a=10; 이처럼 앞에 public static final이 자동으로 포함되어 잇슴
//    public void a(){}; //함수도 안댐!!
    //interface 내부에서는 ''무조건'' public!
    //모든 함수는 자동으로 public(오픈된 기능), abstract(추상화) 포함!
    void e(); //안 보이지만 앞에 abstract public이 자동으로 붙어잇슴

}

//abstract class: 객체가 될 수 없음
abstract class Aable implements B{
    //abstract class는 interface보다 덜 추상화 되어서 정의할 수는 잇슴
    int t=20;
    //추상화: 기능(함수)을 추상화 → 재사용 빈도를 높인다.
    abstract public void c(); //{}바디를 생략하는 것 ⇒ 추상화
}

//class: 객체가 될 수 있는 유일한 설계도
class A extends Aable{ //
    int a=10; //필드 ⇒ 데이터
    public void b(){} //함수 ⇒ 기능

    @Override
    public void c() { //추상메서드 상속받아야 해서 메서드 구현

    }

    @Override
    public void e() { //추상메서드가 추상함수를 포함했기 때문에 메서드 구현

    }
}


class InstanceTest{
    public static void main(String[] args) {
        //Type: class, abstract class, interface
        //객체지향 문법의 추상화 ⇒ 타입을 재사용 ☆
        A a=new A(); //class만 유일하게 객체만들기 가능 >,<
//        Aable aable=new Aable(){}; //추상함수를 포함하는 미완성 설계도는 객체가 될 수 업슴!

//        B b=new B(); //interface는 객체 x
        B b=new B() {
            @Override
            public void e() {

            }
        }; //interface는 익명클래스가 있ㅇㅓ야 객체 만들 수 있음!
        //근데 매번 익명클래스 만들 수 없으니까 람다식 렡츠고
        B b2=()->{};
    }
}
