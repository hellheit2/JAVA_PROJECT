package dto;

import service.IOUtil;

import java.util.List;

// Lecture Class
// 강의 정보 저장
public class Lecture {

    private String lecId; //강의코드
    private String lecType; //강의타입
    private String lecName; //강의이름
    private List<Time> lecTime; //강의시간
    private int lecCredit; //강의학점


    public Lecture(){

    }
    public Lecture(String lecId, String lecType, String lecName, List<Time> lecTime, int lecCredit) {
        this.lecId = lecId;
        this.lecType = lecType;
        this.lecName = lecName;
        this.lecTime = lecTime;
        this.lecCredit = lecCredit;
    }

    public String getId() {
        return lecId;
    }

    public String getType() {
        return lecType;
    }

    public String getName() {
        return lecName;
    }

    public List<Time> getTime() {
        return lecTime;
    }

    public int getCredit() {
        return lecCredit;
    }

    @Override
    public String toString() {
        String time = "";
        for(Time tempTime : lecTime){
            time += tempTime.toString() + " ";
        }

        String result = "";
        result += IOUtil.INSTANCE.textAlign(lecId, 8);
        result += IOUtil.INSTANCE.textAlign(lecType, 6);
        result += IOUtil.INSTANCE.textAlign(lecName, 25);
        result += IOUtil.INSTANCE.textAlign(time, 30);
        result += IOUtil.INSTANCE.textAlign(String.format("%d",lecCredit), 10);

        return result;
    }
}
