package service;

import dto.Student;

import java.util.Map;

// public class StudentService extends StudentManager{  //20220925 : 상속 생성자가 여러번 생성돼서
public class StudentService {
    public StudentService(){

    }

    public Student getStudentByLoginInfo(Map<String, String> id_pwd){
        String id = id_pwd.get("id");
        String pwd = id_pwd.get("pwd");


        //for(Student temp : stuList){  //20220925
        for(Student temp : StudentManager.INSTANCE.stuList){

            String chkID = temp.getId();
            String chkPwd = temp.getPwd();

            if (chkID.equals(id) && chkPwd.equals(pwd))
                return temp;
        }
        return null;
    }
}


