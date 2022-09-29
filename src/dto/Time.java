package dto;

import exception.LectureDuplicationException;
import exception.OutOfWeekdayException;
import service.IOUtil;

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

    public Time() {
    }
    public Time(String day, int startTime, int endTime) throws OutOfWeekdayException {
        this.ts = IOUtil.INSTANCE.makeTimestamp(day, startTime);
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

    @Override
    public String toString() {
        String result = "";
        result = day + "(" + startTime + ":00 ~ " + endTime + ":00)";
        return result;
    }
}
