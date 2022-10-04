package controler;

import dao.StudentDAO;
import exception.NotFoundException;
import service.LoginService;
import utility.OutputUtil;
import view.AdminMenuView;
import view.StudentMenuView;

public class LoginController {
    static LoginService loginService = new LoginService();
    static StudentDAO studentDAO;

    public static void login(String stuId, String stuPwd){
        try{
            studentDAO = null;
            studentDAO = loginService.loginCheck(stuId, stuPwd);

            if(studentDAO == null){
                AdminMenuView.adminMenu();
            }else{
                StudentMenuView.stuMenu();
            }
        }catch(NotFoundException e){
            OutputUtil.errorMessage(e.getMsg());
        }
    }
}
