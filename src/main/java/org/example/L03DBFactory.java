package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class L03DBFactory {
    //┌ 접근지정자
    //public(모두) [default](같은 패키지) protected(상속) private(class/자기자신)
    public int b;
    int a = 100;
    protected int c;
    private int d;
    //class 전역에 선언된 변수들 ⇒ 필드
    //필드들의 가치는 언제~? 객체로 만들어야지 데이터로 존재함. 그 전까지는 그냥 설계도일 뿐..!

    //static : 클래스 변수
    static private int e = 2000; //클래스와 별개로 ''독립적으로'' 데이터로 존재! 근데 왜 클래스에 잇느냐, 그냥 어디에 속해있으려고~



    //--
    private static String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static String user = "scott";
    private static String pw = "tiger";

    //    public static Connection conn=DriverManager.getConnection(url,user,pw); //접속은 오류를 동반해서 try-catch를 써야하지만, 여기서는 쓸 방법이 일절 업슴
    public static Connection conn = null;

    static { //JVM이 static을 메소드 영역에 저장할 때 실행되는 블럭(초기에 실행)
        try {
            conn = DriverManager.getConnection(url, user, pw); //이제 try-catch 쓸 수 잇슴. but, 예외위임은 안댐. 뭐땜시..?
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //└─ 일반적으로 사용되는 한 개의 객체를 자원으로 공유하는 방법. → 문제를 컨트롤 하기 힘듦.



    //┌─ 싱글톤패턴을 사용해서 한 개의 객체를 자원으로 "안전하게" 공유하는 방법
    private static Connection beanConn; //1. private로 바로 접근하지 못하게 하기!
    public static Connection getConn() throws SQLException{ //2. public으로 안전하게 접근할 함수를 정의하기!
        //return beanConn; //얘까지 2번인데 코드 순서 때문에 밑에 보내둠.
        //3. 안전하게 반환하도록 검사를 진행
        if (beanConn==null || beanConn.isClosed()){
            beanConn=DriverManager.getConnection(url,user,pw);
        }
            return beanConn;
    }


    public static void main(String[] args) throws SQLException {
//        System.out.println(a); ⇒ 이런식으로는 a에 접근불가. a가 아직 객체가 아니어서 ^^
        L03DBFactory dbFactory = new L03DBFactory(); //필드가 데이터로 존재하려면 꼭 객체가 되어야 함!
        System.out.println(dbFactory.a); //이렇게 접근해야함.

        System.out.println(e); //객체로 만들지 않아도 static이기 때문에 데이터로 존재함. 와ㅏ우

        conn.close();
        System.out.println(conn);
        System.out.println(conn.isClosed()); //⇒true


        getConn().close();
        System.out.println(getConn()); //close 되어있으면 '무조건' 새로 만들기 때문에 위험하지 않아욥
        System.out.println(getConn().isClosed()); //⇒false
    }
}
