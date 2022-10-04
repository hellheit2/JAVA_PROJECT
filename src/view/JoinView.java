package view;

import controler.LoginJoinController;
import dto.Student;
import utility.InputUtil;
import utility.OutputUtil;

public class JoinView {

    public static void printJoin() {
        String inputId = null;
        String inputPw = null;
        String inputName = null;
        String inputNo = null;
        String inputMajor = null;
        System.out.println("───────────────회원가입───────────────");
        System.out.println("정보를 입력하세요.");
        try {
            inputId = InputUtil.INSTANCE.inputStr("아이디를 입력하세요 : ");
            inputPw = InputUtil.INSTANCE.inputStr("비밀번호를 입력하세요 : ");
            inputName = InputUtil.INSTANCE.inputStr("이름을 입력하세요 : ");
            inputNo = InputUtil.INSTANCE.inputStr("학번을 입력하세요 : ");
            inputMajor = InputUtil.INSTANCE.inputStr("학과를 입력하세요 : ");
        }catch (NumberFormatException e) {
            OutputUtil.errorMessage("잘못된 입력값입니다.");
            return;
        }
        Student student = new Student(inputId, inputPw, inputNo, inputName, inputMajor, null);
        LoginJoinController.join(student);
    }
}
