package service;

import dto.Student;

import java.util.Map;

public class LoginService extends StudentManager {
    private static final String ADMIN_ID = "admin"; //관리자 아이디
    private static final String ADMIN_PWD = "admin"; //관리자 비번

    public LoginService(){

    }
    public boolean isCorrectUser(Map<String, String> id_pw) {
        String id = id_pw.get("id");
        String pwd = id_pw.get("pwd");

        for(Student temp : stuList){

            String chkID = temp.getId();
            String chkPwd = temp.getPwd();

            if (chkID.equals(id) && chkPwd.equals(pwd))
                return true;
        }
        return false;
    }

    // 관리자인지 체크
    public static boolean isAdmin(Map<String, String> id_pwd){
        return id_pwd.get("id").equals(ADMIN_ID)
                && id_pwd.get("pwd").equals(ADMIN_PWD);
    }
}
