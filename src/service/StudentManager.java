package service;

import dto.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentManager {

    final static StudentManager INSTANCE = new StudentManager();

    public List<Student> stuList = new ArrayList<>();

    StudentManager(){
        Student test = new Student();
        test.setId("test");
        test.setPwd("1234");
        test.setStuId(11111);
        test.setStuName("Kim");
        test.setStuMajor("컴퓨터공학과");

        stuList.add(test);

        printStudent(); // 저장된 학생 출력 테스트용
    }

    public void printStudent(){
        // 학생추가
        for(Student stuTemp: stuList){
            System.out.println(stuTemp.toString());
        }
    }

}
