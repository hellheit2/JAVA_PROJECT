package service;

import dto.Lecture;
import dto.Time;
import exception.OutOfWeekdayException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

public class IOUtil {
    public final static IOUtil INSTANCE = new IOUtil();
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public String inputMenu(String msg) throws IOException {
        System.out.print(msg);
        return bf.readLine();
    }


    public Time timeStampToTime(Timestamp ts, int duration){
        Time time = new Time();
        SimpleDateFormat conTimeFormat = new SimpleDateFormat("E H");
        StringTokenizer st = new StringTokenizer(conTimeFormat.format(ts), " ");

        time.ts = ts;
        time.day = st.nextToken();
        time.startTime = parseInt(st.nextToken());
        time.endTime = time.startTime + duration;

        return time;
    }
    //요일, 시작시간 -> 타임스탬프 형식으로 변환 (시간표에서는 요일, 시간밖에 안쓰므로 나머지는 고정)
    public Timestamp makeTimestamp(String day, int startTime) {
        // 2022-09-05 기준
        try {
            String dateTime = "2022-09-" + String.format("%02d", dyaOfWeekToInt(day) + 5) + " ";
            dateTime += String.format("%02d", startTime) + ":00:00";

            return Timestamp.valueOf(dateTime);

        }catch(OutOfWeekdayException e){
            System.out.println(e.getMsg());
        }
        return null;
    }
    //고정 날짜 기준으로 각 요일에 해당하는 일 반환
    public int dyaOfWeekToInt(String day) throws OutOfWeekdayException {
        final List<String> WEEK = new ArrayList<>(Arrays.asList("월","화","수","목","금"));

        // 9월 5일~9월 9일(월~금) 기준으로 일 리턴
        if(WEEK.contains(day)) {
            if (day.equals("월")) return 0;
            else if (day.equals("화")) return 1;
            else if (day.equals("수")) return 2;
            else if (day.equals("목")) return 3;
            else if (day.equals("금")) return 4;
        } else{
            throw new OutOfWeekdayException("올바른 요일을 입력하세요");
        }
        return -1;
    }

    public void printLectureList(List<Lecture> list){
        System.out.print(String.format("%-4s","번호"));
        System.out.print(String.format("%-8s","강의코드"));
        System.out.print(String.format("%-12s","강의구분"));
        System.out.print(String.format("%-30s","강의명"));
        System.out.print(String.format("%-38s","강의시간"));
        System.out.println(String.format("%-10s","학점"));
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
