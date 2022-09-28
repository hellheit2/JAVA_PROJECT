package dto;

import java.sql.Timestamp;

public class Time {
    public Timestamp ts;
    public String day;
    public int startTime;
    public int endTime;

    public Time() {
    }

    public Time(Timestamp ts, String day, int startTime, int endTime) {
        this.ts = ts;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
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
