package test;

import java.util.ArrayList;
import java.util.List;

public class Lecture {

    private String id; //강의코드
    private String type; //강의타입
    private String name; //강의이름
    private List<Ltime> time; //강의시간
    private int credit; //강의학점


    public Lecture(){}
    public Lecture(String id, String type, String name, List<Ltime> time, int credit) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.time = time;
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<Ltime> getTime() {
        return time;
    }

    public int getCredit() {
        return credit;
    }
}
