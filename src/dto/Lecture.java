package dto;

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

    public void setLecType(String lecType) {
        this.lecType = lecType;
    }

    public void setLecName(String lecName) {
        this.lecName = lecName;
    }

    public void setLecTime(List<Time> lecTime) {
        this.lecTime = lecTime;
    }

    public void setLecCredit(int lecCredit) {
        this.lecCredit = lecCredit;
    }

    @Override
    public boolean equals(Object obj) {
        Lecture lecture = (Lecture) obj;

        if(lecture.getId() == this.getId()
                && lecture.getTime() == this.getTime())
            return true;
        return false;
    }

    @Override
    public String toString() {
        String time = "";
        for(Time tempTime : lecTime){
            time += tempTime.toString() + " ";
        }

        char c = 0x20;
        String result = "";
        result += String.format("%-8s",lecId).replace(" "," ");
        result += String.format("%-10s",lecType).replace(" "," ");
        result += String.format("%-5d",lecCredit).replace(" "," ");
        result += String.format("%-18s",lecName).replace(" "," ");
        result += String.format("%-35s",time).replace(" "," ");;


        return result;
    }
}
