package service;

import dto.Student;
import view.LoginView;

import java.util.Map;
import java.util.Scanner;

public class LoginController {

    static Scanner sc = new Scanner(System.in);
    static StudentMenuController studentMenuController;
    static StudentService studentController;
    public LoginService loginService = new LoginService();

    static boolean isRun = true;
    Map<String,String> id_pwd;
    Student student;
    int userType;

    public LoginController(){

    }

    public void showLoginMenu(){

        while(isRun) {
            id_pwd = LoginView.login();

            if (loginService.isAdmin(id_pwd)){
                userType = 0;
                isRun = false;
            } else if (loginService.isCorrectUser(id_pwd)) {
                studentController = new StudentService();
                userType = 1;
                isRun = false;
                student = studentController.getStudentByLoginInfo(id_pwd);
            } else {
                System.out.println("!!아이디나 비밀번호를 다시 확인해주세요.!!");
                System.out.println("─────────────────────────────────────");
                return;
            }
        }

        switch (userType) {
            case 0:
                // 관리자 모드
                System.out.println("      ** 관리자로 로그인했습니다 **      ");
                System.out.println("─────────────────────────────────────");
                break;
            case 1:
                // 학생 모드
                System.out.println("          ** 로그인했습니다 **          ");
                System.out.println("─────────────────────────────────────");
                studentMenuController = new StudentMenuController();
                studentMenuController.showStudentMenu(student);
                break;
        }

    }






}
