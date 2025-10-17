package org.example;

import java.time.LocalDate;

public class L05EmpDTO { //①번
    //EMP 테이블
    private int empno;
    private String ename;
    private String job;
    private Integer mgr;
//  private java.util.Date.hiredate;
    private LocalDate hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
    //└─ getter, setter 캡슐화


    @Override
    public String toString() {
        return "L05EmpDTO{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                "}\n";
    }

    //setter 규칙. 타입은 void, 매개변수는 필드와 동일.
    //getter 규칙. 매개변수 필요없음

    //└─ 거의 대부분의 개발툴은 get, set 자동완성 제공(오른쪽 마우스 클릭 → 생성). 왜? 정형화 되어있어서~
    //lombok: 컴파일시 자동완성하는 라이브러리 ⇒ Spring 할 때 써볼게!


    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}
