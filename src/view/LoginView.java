package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginView {
    static Scanner sc = new Scanner(System.in);
    public static Map<String,String> login(){
        System.out.println("─────────────── 로그인 ───────────────");
        System.out.print("아이디: ");
        String id = sc.next();
        System.out.print("비밀번호: ");
        String pwd = sc.next();

        Map<String,String> id_pwd = new HashMap<>();
        id_pwd.put("id",id);
        id_pwd.put("pwd",pwd);


        return id_pwd;

    }


}
