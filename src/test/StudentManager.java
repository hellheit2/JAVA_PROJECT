package test;

import java.util.ArrayList;

public class StudentManager {

    public ArrayList<Student> stuList = new ArrayList<>();

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
