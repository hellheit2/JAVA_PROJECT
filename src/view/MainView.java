package view;

import java.util.Scanner;

public class MainView {
    static Scanner sc = new Scanner(System.in);
    public static int mainMenu(){
        System.out.println("────────────수강신청 프로그램───────────");
        System.out.println("       0.종료  1.로그인  2.회원가입      ");
        System.out.println("─────────────────────────────────────");
        System.out.print(">> ");
        return sc.nextInt();
    }
}
