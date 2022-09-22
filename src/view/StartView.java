package view;

import java.util.Scanner;

public class StartView {
    static Scanner sc = new Scanner(System.in);
    static boolean isRun = true;
    public static void main(String[] args) {

        showStartMenu();


    }







    public static void showStartMenu() {
        while (isRun) {
            System.out.println("────────────수강신청 프로그램───────────");
            System.out.println("       0.종료  1.로그인  2.회원가입      ");
            System.out.println("─────────────────────────────────────");
            System.out.print(">> ");
            int i = sc.nextInt();

            switch (i) {
                case 0:
                    // 종료
                    System.out.println("프로그램을 종료 합니다.");
                    isRun = false;
                    break;
                case 1:
                    // 로그인
                    System.out.println("로그인");
                    break;
                case 2:
                    //회원가입
                    System.out.println("회원가입");
                    break;
                default:
                    System.out.println("다시 입력해주세요.");
            }
        }
    }
}
