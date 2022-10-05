package view;

import utility.InputUtil;
import utility.OutputUtil;

import java.io.IOException;

public class MainView {

    public static boolean isRun = true;

    public static void showMainMenu() throws IOException {

        while (isRun) {
            int result = printMain();
            switch (result) {
                case 0: // 종료
                    System.out.println("프로그램을 종료 합니다.");
                    InputUtil.INSTANCE.close();
                    isRun = false;
                    break;
                case 1: // 로그인
                    LoginView.printLogin();
                    break;
                case 2: //회원가입
                    JoinView.printJoin();
                    break;
                default:
                    OutputUtil.errorMessage("다시 입력해주세요.");
            }
        }
    }
    public static int printMain() {

        System.out.println("────────────수강신청 프로그램───────────");
        System.out.println("       0.종료  1.로그인  2.회원가입      ");
        System.out.println("─────────────────────────────────────");

        return InputUtil.INSTANCE.inputInt(">> ");


    }
}
