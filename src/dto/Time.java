package dto;

public class Time {
    public String day;
    int startTime;
    int endTime;

    public Time() {
    }

    public Time(String day, int startTime, int endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //확인하고자 하는 시간이 해당 수업 시간과 겹치는지 확인
    public boolean timeDuplicateCheck(Time time){
        return (startTime <= time.startTime) && (endTime >= time.startTime);
    }
}
