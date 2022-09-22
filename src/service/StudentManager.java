package service;

import dto.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    public List<Student> stuList = new ArrayList<>();

    public StudentManager(){
        Student test = new Student();
        test.setId("test");
        test.setPwd(1234);
        test.setStuId(11111);
        test.setStuName("Kim");
        test.setStuMajor("JAVA");

        stuList.add(test);
    }

    public boolean addStudent(){
        // 학생추가

        return true;
    }
}
