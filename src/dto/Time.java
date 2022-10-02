package dto;

import exception.OutOfWeekdayException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Time {
    public String day;
    public Timestamp startTime;
    public Timestamp endTime;

    public Time() {
    }
    public Time(String day, Timestamp startTime, Timestamp endTime) throws OutOfWeekdayException {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        SimpleDateFormat conTimeFormat = new SimpleDateFormat("HH:mm");
        String result = "";
        String start = conTimeFormat.format(startTime);
        String end = conTimeFormat.format(endTime);
        result = day + "(" + start + " ~ " + end + ")";
        return result;
    }
}
