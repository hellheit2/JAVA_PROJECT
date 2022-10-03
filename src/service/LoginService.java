package service;

import dao.StudentDAO;
import dto.Student;
import exception.NotFoundException;
import utility.OutputUtil;

import java.util.Map;

public class LoginService {
    private static final String ADMIN_ID = "admin"; //관리자 아이디
    private static final String ADMIN_PWD = "admin"; //관리자 비번
    StudentDAO studentDAO = new StudentDAO();

    public LoginService(){

    }

    public StudentDAO loginCheck(String stuId, String stuPwd) throws NotFoundException {

        if(isAdmin(stuId,stuPwd)) {
            OutputUtil.successMessage("───────────── 로그인 성공 ─────────────");
            OutputUtil.successMessage("      관리자님 로그인에 성공했습니다.");
            OutputUtil.successMessage("─────────────────────────────────────");
            return null;
        }

        Student student = studentDAO.selectUserInfo(stuId);

        if(student == null){
            throw new NotFoundException("존재하지 않는 아이디입니다.");
        }
        if(!student.getPwd().equals(stuPwd)){
            throw new NotFoundException("비밀번호가 일치하지 않습니다.");
        }else{
            OutputUtil.successMessage("───────────── 로그인 성공 ─────────────");
            OutputUtil.successMessage("   " + stuId + "님 로그인에 성공했습니다.");
            OutputUtil.successMessage("─────────────────────────────────────");
            studentDAO.setSelectStu(student);
        }
        return studentDAO;
    }

    // 관리자인지 체크
    public static boolean isAdmin(String stuId, String stuPwd){
        return stuId.equals(ADMIN_ID)
                && stuPwd.equals(ADMIN_PWD);
    }
}
