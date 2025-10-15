package hw.dao;

public class DeptDto {
    //과제 3. DEPT 테이블의 Dto 클래스 구현하기

    private int deptno;
    private String dname;
    private String loc;

    public DeptDto(){} //기본생성자 만들어두고

    public DeptDto(int deptno, String dname, String loc){
        this.deptno=deptno;
        this.dname=dname;
        this.loc=loc;
    }

    public void setDeptno(int deptno){
        this.deptno=deptno;
    }
    public int getDeptno(){
        return deptno;
    }

    public void setDname(String dname){
        this.dname=dname;
    }
    public String getDname(){
        return dname;
    }

    public void setLoc(String loc){
        this.loc=loc;
    }
    public String getLoc(){
        return loc;
    }

    /*public static void main(String[] args) {
        DeptDto deptDto=new DeptDto(10, "ACCOUNTING","NEW YORK");
        System.out.println(deptDto.deptno);
    }*/ //테스트용임. 굳이 DTO에는 main 안 써도 됌.
}
