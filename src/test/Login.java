package test;

import test.StudentManager;

public class Login extends StudentManager {

    private static final String ADMIN_ID = "admin"; //관리자 아이디
    private static final String ADMIN_PW = "1234"; //관리자 비번


    // 관리자인지 체크
    public boolean userCheck(String id, String pw) {
        if(id.equals(ADMIN_ID) && pw.equals(ADMIN_PW))
            return true;

        return false;
    }
}
