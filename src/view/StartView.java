package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import test.Ltime;
import test.StudentManager;
import test.Lecture;

public class StartView {
    static Scanner sc = new Scanner(System.in);
    static boolean isRun = true;
    public static void main(String[] args) {

        Lecture lec = new Lecture(
               "아이디",
               "전공",
               "이름",
                new ArrayList<>(
                        Arrays.asList(
                                new Ltime(1,1,1),
                                new Ltime(2,2,2)
                        )
                ),
                3
        );

//        String id, String type, String name, List< LecTime > time, int credit
        System.out.println(new StudentManager().stuList.get(0).getStuId());
//        showStartMenu();
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
