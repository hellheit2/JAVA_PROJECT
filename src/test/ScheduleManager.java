package test;

import java.util.Scanner;

public class ScheduleManager {

    static boolean isRun = true;
    Scanner sc = new Scanner(System.in);

    public ScheduleManager(){

    }

    public boolean Login() {

        String id;
        String pw;

        System.out.print("아이디를 입력하세요.");
        id = sc.next();
        System.out.print("비번을 입력하세요.");
        pw = sc.next();


        return true;

    }



}
