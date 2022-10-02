package utility;

import dto.Time;
import exception.OutOfWeekdayException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class InputUtil {
    public final static InputUtil INSTANCE = new InputUtil();
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public int inputMenu(String msg){

        System.out.print(msg);
        try{
            int menu = Integer.parseInt(bf.readLine());
            return menu;
        } catch(NumberFormatException e){
            OutputUtil.errorMessage("올바른 숫자를 입력해주세요");
            return -1;
        } catch(IOException e){
            OutputUtil.errorMessage("잘못된 입력입니다.");
            return -1;
        }
    }
    public String inputStr(String msg){

        System.out.print(msg);
        try{
            return bf.readLine();
        } catch(IOException e){
            OutputUtil.errorMessage("잘못된 입력입니다.");
            return null;
        }
    }
    public final static Map<String,Integer> WEEK = Map.of("월",0,"화",1,"수",2,"목",2,"금",4);


    //고정 날짜 기준으로 각 요일에 해당하는 일 반환
    public static int dayOfWeekToInt(String day) throws OutOfWeekdayException {
        // 9월 5일~9월 9일(월~금) 기준으로 일 리턴
        if(WEEK.containsKey(day)) {
            return WEEK.get(day);
        } else{
            throw new OutOfWeekdayException("올바른 요일을 입력하세요");
        }
    }
    public static int timestampToHour(Timestamp ts) {
        SimpleDateFormat conTimeFormat = new SimpleDateFormat("H");
        return Integer.parseInt(conTimeFormat.format(ts));
    }

    public Time timestampToTime(Timestamp start, Timestamp end){
        Time time = new Time();
        SimpleDateFormat conTimeFormat = new SimpleDateFormat("E");

        time.day = conTimeFormat.format(start);
        time.startTime = start;
        time.endTime = end;

        return time;
    }
}
