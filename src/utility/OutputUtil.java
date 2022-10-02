package utility;

import dto.Lecture;
import exception.OutOfWeekdayException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OutputUtil {

    public static void errorMessage(String msg){
        System.out.println(msg);
    }
    public static void successMessage(String msg){
        System.out.println(msg);
    }

    public static void printLectureList(List<Lecture> list){
        System.out.print(String.format("%-4s","번호"));
        System.out.print(String.format("%-8s","강의코드"));
        System.out.print(String.format("%-12s","강의구분"));
        System.out.print(String.format("%-10s","학점"));
        System.out.print(String.format("%-30s","강의명"));
        System.out.println(String.format("%-38s","강의시간"));
        System.out.println("───────────────────────────────────────────────────" +
                "─────────────────────────────────────────────────────────");

        int i = 1;
        for(Lecture lecTemp: list){
            System.out.print(String.format("%-5s",i++));
            System.out.println(lecTemp.toString());
        }
        System.out.println("───────────────────────────────────────────────────" +
                "─────────────────────────────────────────────────────────");
        System.out.println();
    }
}
