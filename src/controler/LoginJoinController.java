package controler;

import dao.StudentDAO;
import dto.Student;
import exception.NotFoundException;
import exception.UserDuplicationException;
import service.LoginJoinService;
import service.StudentService;
import utility.OutputUtil;
import view.AdminMenuView;
import view.StudentMenuView;

public class LoginJoinController {
    static LoginJoinService loginjoinService = new LoginJoinService();

    public static void login(String stuId, String stuPwd){
        try{
            Student selectStu = loginjoinService.loginCheck(stuId, stuPwd);

            if(selectStu == null){
                AdminMenuView.adminMenu();
            }else{
                StudentMenuView.stuMenu();
            }
        }catch(NotFoundException e){
            OutputUtil.errorMessage(e.getMsg());
        }
    }

    public static boolean join(Student student){
        try {
            return loginjoinService.joinCheck(student);
        }catch(UserDuplicationException e){
            OutputUtil.errorMessage(e.getMsg());
        }
        return false;
    }
}
