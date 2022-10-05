package service;

import dao.StudentDAO;
import dto.Student;
import exception.NotFoundException;
import exception.UserDuplicationException;
import utility.OutputUtil;

public class LoginJoinService {
    private static final String ADMIN_ID = "admin"; //관리자 아이디
    private static final String ADMIN_PWD = "admin"; //관리자 비번

    public LoginJoinService(){

    }

    public Student loginCheck(String stuId, String stuPwd) throws NotFoundException {

        if(isAdmin(stuId,stuPwd)) {
            OutputUtil.successMessage("───────────── 로그인 성공 ─────────────");
            OutputUtil.successMessage("      관리자님 로그인에 성공했습니다.");
            OutputUtil.successMessage("─────────────────────────────────────");
            return null;
        }

        Student student = StudentDAO.selectUserInfo(stuId);

        if(student == null){
            throw new NotFoundException("존재하지 않는 아이디입니다.");
        }
        if(!student.getStuPwd().equals(stuPwd)){
            throw new NotFoundException("비밀번호가 일치하지 않습니다.");
        }else{
            OutputUtil.successMessage("───────────── 로그인 성공 ─────────────");
            OutputUtil.successMessage("   " + stuId + "님 로그인에 성공했습니다.");
            OutputUtil.successMessage("─────────────────────────────────────");
            StudentDAO.setSelectStu(student);
        }
        return student;
    }
    public boolean joinCheck(Student student) throws UserDuplicationException{
        for(Student temp : StudentDAO.getStuList()){
            if(temp.getStuId().equals(student.getStuId()))
                throw new UserDuplicationException("이미 등록된 사용자입니다.");
        }
        StudentDAO.getStuList().add(student);
        OutputUtil.successMessage("** 회원가입에 성공했습니다. 다시 로그인해주세요.");

        return true;
    }
    // 관리자인지 체크
    public static boolean isAdmin(String stuId, String stuPwd){
        return stuId.equals(ADMIN_ID)
                && stuPwd.equals(ADMIN_PWD);
    }
}
