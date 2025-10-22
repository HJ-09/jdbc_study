package org.example;

import java.time.LocalDate;

public class L11EmpValidBean {
    //dto: 순수 캡슐화 객체(getter/setter만 존재), 이미 검증된 데이터를 가져오기도 하고, 직렬화에 유리하기 위해서.
    //Bead: dto+검증 (직렬화 하면 안댐)
    //Entity: 데이터베이스 전용 dto여서 직렬화하면 오류.(db자동맵핑 기술이 포함되어 있어서)
    //EmpDto와 EmpBean 필드는 같을 수도 있고, 다를 수도 있음.

    private int empno;
    private String ename;
    private String job;
    private LocalDate hiredate=LocalDate.now(); //기본값 설정(Bean 전용)
    private Double sal;
    private Double comm;
    private Integer mgr; //EMP.empno FK (생성불가)
    private Integer deptno; //DEPT.deptno FK(생성불가)


    //유효성 검사는 입력한거를 DTO에다가 저장(set)할 때 !
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        if (empno<=0) throw new IllegalArgumentException("사번을 정확히 입력하세요.");
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
//        if(ename==null) return;
        if (ename==null || ename.isBlank()) throw new IllegalArgumentException("이름을 입력하세요.");
        //길이가 10이하인 경우
        if (ename.trim().length()>10) throw new IllegalArgumentException("이름은 10자 이하여야 합니다.");
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
        //null이 0보다 작은건 오류이기 때문에 null이 아닌 경우를 앞에 놔줘야 뒤에 비교연산이 가능해짐.
        if (sal!=null && sal<0) throw new IllegalArgumentException("급여는 0보다 커야합니다.");
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        if (comm!=null){
            if (comm<=0) throw new IllegalArgumentException("커미션은 0보다 작을 수 없습니다.");
        } //if(comm!=null && comm<=0) 이거랑 같은것!
        this.comm = comm;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}
