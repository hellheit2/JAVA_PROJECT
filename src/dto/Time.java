package dto;

import exception.LectureDuplicationException;
import exception.OutOfWeekdayException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Time {
    public Timestamp ts;
    public String day;
    public int startTime;
    public int endTime;
    final static List<String> WEEK = new ArrayList<>(Arrays.asList("월","화","수","목","금"));



    public Time() {
    }
    public Time(String day, int startTime, int endTime) throws OutOfWeekdayException {
        this.ts = makeTimestamp(day, startTime);
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Timestamp makeTimestamp(String day, int startTime) throws OutOfWeekdayException {
        // 2022-09-05 기준
        String dateTime = "2022-09-" + dyaOfWeekToStr(day) + " ";
        dateTime += String.format("%02d",startTime) + ":00:00";

        return Timestamp.valueOf(dateTime);
    }
    public String dyaOfWeekToStr(String day) throws OutOfWeekdayException {
        // 9월 5일~9월 9일(월~금) 기준으로 일 리턴
        if(WEEK.contains(day)) {
            if (day.equals("월")) return "05";
            else if (day.equals("화")) return "06";
            else if (day.equals("수")) return "07";
            else if (day.equals("목")) return "08";
            else if (day.equals("금")) return "09";
        } else{
            throw new OutOfWeekdayException("올바른 요일을 입력하세요");
        }
        return null;
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
    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    //확인하고자 하는 시간이 해당 수업 시간과 겹치는지 확인
    public boolean timeDuplicateCheck(Time time){
        return (startTime <= time.startTime) && (endTime >= time.startTime);
    }

    @Override
    public String toString() {
        String result = "";
        result = day + "(" + startTime + ":00 ~ " + endTime + ":00)";
        return result;
    }
}
