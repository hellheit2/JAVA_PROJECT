package service;

import dto.Lecture;
import dto.Time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class IOUtil {
    public final static IOUtil INSTANCE = new IOUtil();
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public String inputMenu(String msg) throws IOException {
        System.out.print(msg);
        return bf.readLine();
    }

    public String textAlign(String text, int length){
        int padFront = (length - text.length())/2;
        int padEnd = length - text.length() - padFront;

        String result = "";
        for (int i = 0; i < padFront; i++) {
            result += " ";
        }
        result +=text;
        for (int i = 0; i < padEnd; i++) {
            result += " ";
        }
        return result;
    }

    public Time timeStampToTime(Timestamp ts, int duration){
        Time time = new Time();
        SimpleDateFormat conTimeFormat = new SimpleDateFormat("E H");
        StringTokenizer st = new StringTokenizer(conTimeFormat.format(ts), " ");

        time.day = st.nextToken();
        time.startTime = parseInt(st.nextToken());
        time.endTime = time.startTime + duration;

        return time;
    }

    public static void printLectureList(List<Lecture> list){
        System.out.print("  번호  ");
        System.out.print("  강의코드  ");
        System.out.print("  강의구분  ");
        System.out.print("               강의명               ");
        System.out.print("                강의시간                ");
        System.out.println("  학점  ");
        System.out.println("───────────────────────────────────────────────────" +
                "─────────────────────────────────────────────────────────");

        int i = 1;
        for(Lecture lecTemp: list){
            System.out.print(IOUtil.INSTANCE.textAlign(String.format("%s",i++), 5));
            System.out.println(lecTemp.toString());
        }
        System.out.println("───────────────────────────────────────────────────" +
                "─────────────────────────────────────────────────────────");
        System.out.println();
    }

}
