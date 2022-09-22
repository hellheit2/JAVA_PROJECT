package view;

import java.util.Scanner;

public class StudentView {
    Scanner sc = new Scanner(System.in);
    static boolean isRun = true;

    public void showMain() {
        int selectNo;
        while(isRun) {
            System.out.println("                             학생 메뉴                             ");
            System.out.println("==================================================================");
            System.out.println("0. 수강 내역  | 1. 수강 신청  | 2. 수강 철회 | 3. 시간표 확인   | 4. 종료");
            System.out.println("==================================================================");
            selectNo = sc.nextInt();


            switch(selectNo)
            {
                case 0 :
                    break;
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    break;
                default :
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
