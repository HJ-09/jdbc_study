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

    public L05EmpDTO(){}; //기본생성자도 만들어둘게~

    //Builder.builder() 내부클래스만 호출하는 생성자 만들게
    private L05EmpDTO(Builder builder){
        this.ename=builder.ename;
        this.empno=builder.empno;
        this.job=builder.job;
        this.sal=builder.sal;
        this.comm=builder.comm;
        this.mgr=builder.mgr;
        this.hiredate=builder.hiredate;
        this.deptno=builder.deptno;
    }

    //내부클래스: 외부클래스가 객체일 때만 생성가능
    //public class Builder{}
    //static 내부클래스: 외부클래스가 객체가 아니더라도 생성가능~!
    public static class Builder{
        private int empno;
        private String ename;
        private String job;
        private Integer mgr;
        private LocalDate hiredate;
        private Double sal;
        private Double comm;
        private Integer deptno;


        public Builder(int empno, String ename) { //고정값 만들기 위한 생성자 생성
            this.empno = empno;
            this.ename = ename;
        }


        public Builder empno(int empno){
            this.empno=empno;
            return this; //나 자신으로 return하면 함수를 체이닝해서 실행할 수 잇슴
        }
        public Builder ename(String ename){
            this.ename=ename;
            return this;
        }

        public Builder job(String job) {
            this.job = job;
            return this;
        }

        public Builder mgr(Integer mgr) {
            this.mgr = mgr;
            return this;
        }

        public Builder hiredate(LocalDate hiredate) {
            this.hiredate = hiredate;
            return this;
        }

        public Builder sal(Double sal) {
            this.sal = sal;
            return this;
        }

        public Builder comm(Double comm) {
            this.comm = comm;
            return this;
        }

        public Builder deptno(Integer deptno) {
            this.deptno = deptno;
            return this;
        }

        public L05EmpDTO builder(){
            return new L05EmpDTO(this);
        }
    }
}

class BuilderTest{
    static void main(String[] args) {
        //L05EmpDTO.Builder builder=new L05EmpDTO().new Builder();
        L05EmpDTO.Builder builder=new L05EmpDTO.Builder(1111,"흥민")
                //.empno(111).ename("흥민")
                .deptno(222)
                .sal(33.0)
                .comm(0.0)
                .job("개발자")
                .mgr(7788)
                .hiredate(LocalDate.now());

        L05EmpDTO emp=new L05EmpDTO.Builder(111,"준호")
                //.empno(111).ename("준호")
                .deptno(222)
                .sal(33.0)
                .comm(0.0)
                .job("개발자")
                .mgr(7788)
                .hiredate(LocalDate.now())
                .builder();

        System.out.println(emp);
    }
}
