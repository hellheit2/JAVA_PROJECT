package service;

import view.MainView;

import java.io.IOException;
import java.util.Scanner;

public class MainMenuController {
    static Scanner sc = new Scanner(System.in);
    static boolean isRun = true;
    static LoginController loginController;
    int result;

    public MainMenuController() throws IOException {
        showMainMenu();
    }

    public void showMainMenu() throws IOException {
        while (isRun) {

            result = MainView.mainMenu();

            switch (result) {
                case 0:
                    // 종료
                    System.out.println("프로그램을 종료 합니다.");
                    isRun = false;
                    break;
                case 1:
                    // 로그인
                    loginController = new LoginController();
                    loginController.showLoginMenu();
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
