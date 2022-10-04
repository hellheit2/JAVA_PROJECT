package view;

import controler.LoginJoinController;
import utility.InputUtil;


public class LoginView {

    public static void printLogin(){
        System.out.println("─────────────── 로그인 ───────────────");
        String id = InputUtil.INSTANCE.inputStr("아이디: ");
        String pwd = InputUtil.INSTANCE.inputStr("비밀번호: ");

        LoginJoinController.login(id, pwd);
    }
}
