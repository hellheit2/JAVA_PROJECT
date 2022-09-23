package service;

import dto.Student;

import java.util.Map;

public class StudentService extends StudentManager{

    public Student getStudentByLoginInfo(Map<String, String> id_pwd){
        String id = id_pwd.get("id");
        String pwd = id_pwd.get("pwd");

        for(Student temp : stuList){

            String chkID = temp.getId();
            String chkPwd = temp.getPwd();

            if (chkID.equals(id) && chkPwd.equals(pwd))
                return temp;
        }
        return null;
    }
}
