package service;

import dto.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//public class StudentManager { //20220925
public class StudentManager {

    final static StudentManager INSTANCE = new StudentManager();
    // enum으로 하고 INSTANCE = new StudentManager(); 해도 동일

    // 싱글톤으로 해야하나? Login할떄 StudentManager를 상속했음
    // 로그아웃했다 다시 로그인을 한다면 부모(SM)가 다시 생성될 것이다.
    // 그 전의 변경했던 정보가 있는 부모와 다른 새로운 부모가 생긴다.
    // 이전 변경이 유지가 안된다
    // 근데 DB 연결하면 클래스로 유지할 필요 없을것 같다
    public List<Student> stuList = new ArrayList<>();

    //public StudentManager(){ //20220925
    StudentManager(){
        Student test = new Student();
        test.setId("test");
        test.setPwd("1234");
        test.setStuId(11111);
        test.setStuName("Kim");
        test.setStuMajor("컴퓨터공학과");

        stuList.add(test);

        printStudent(test); // 저장된 학생 출력 테스트용
    }

    public void printStudent(Student student){
        // 학생추가
        for(Student stuTemp: stuList){
            System.out.println(stuTemp.toString());
        }
    }

}
