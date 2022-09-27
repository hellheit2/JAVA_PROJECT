package view;

import java.util.Scanner;

public class StudentView {
    static Scanner sc = new Scanner(System.in);

    public static int studentMenu(){
        System.out.println();
        System.out.println("                                     학생 메뉴" +
                            "                                     ");
        System.out.println("─────────────────────────────────────────────" +
                "────────────────────────────────────");
        System.out.println("0. 종료  | 1. 강의 목록  | 2. 수강 내역 " +
                "| 3. 수강 신청   | 4. 수강 철회 | 5. 시간표 확인");
        System.out.println("─────────────────────────────────────────────" +
                "────────────────────────────────────");
        System.out.print(">> ");
        return sc.nextInt();
    }
}
