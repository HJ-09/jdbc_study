package org.example;

//첫번째

public class L07DeptDTO {
    /*
    DEPTNO NUMBER(2) → (java) → byte, short, int, long,...
    DNAME VARCHAR2(14) → (java) → String, char[14],...
    LOC  VARCHAR2(13) → (java) → String, char[13],...
    */

    //변수는 소문자로! 대문자 써도 작동 되지만, 대문자는 final 정의라서 개발자 사이의 약속에 어긋남 ^^!
    private int deptno;
    public void setDeptno(int deptno){
        this.deptno=deptno;
    }
    public int getDeptno(){
        return this.deptno;
    }

    private String dname;
    public void setDname(String dname){
        this.dname=dname;
    }
    public String getDname(){
        return this.dname;
    }

    private String loc;
    public void setLoc(String loc){
        this.loc=loc;
    }
    public String getLoc(){
        return this.loc;
    }

    //dto는 데이터를 저장(set) 및 전송(get)함.

    //생성자: 타입이 객체가 될 때 초기값(default)을 정의
    public L07DeptDTO(){} //생성자를 밑에처럼 정의하면 기본 생성자가 사라지기 때문에 이렇게 해도 됨. 이게 바로 오버로드(똑같은 생성자가 매개변수 다르게 존재)
    public L07DeptDTO(int deptno, String dname, String loc){
        this.deptno=deptno;
        this.dname=dname;
        this.loc=loc;
    }
    //생성자를 정의해두면 default가 사라지기 때문에 객체를 만들 때 강제정의를 해야함. 따라서 위에 기본생성자 만들어둬도 됨.

    //이름은 한 개인데 역할이 여러개 ⇒ 다형성(객체지향문법)
    //└─ 오버로드, 오버라이드, 타입의 다형성 ..


    /*@Override
    public String toString(){
//        String str=null; //웬만하면 null 말고 기본값을 주는게 좋음.
        String str="";
        str+="deptno: "+deptno+", ";
        str+="dname: "+dname+", ";
        str+="loc: "+loc;
        return str;
    }*/

    //자동완성
    @Override
    public String toString() {
        return "{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                "}\n";
    }
}

class T{
    public static void main(String[] args) {
        L07DeptDTO deptDTO=new L07DeptDTO();
        deptDTO.setDeptno(10);
        deptDTO.setDname("리서치");
        deptDTO.setLoc("서울");

        System.out.println(deptDTO.getDeptno()); //10
        System.out.println(deptDTO.getDname()); //리서치
        System.out.println(deptDTO.getLoc()); //서울

        System.out.println(deptDTO);

        L07DeptDTO deptDTO2=new L07DeptDTO(20,"개발","부산");
        System.out.println(deptDTO2);
    }

}