package utility;

import dto.Lecture;
import dto.Time;
import exception.LackOfElementException;
import exception.OutOfWeekdayException;
import exception.TimeConflictException;
import exception.TimeFormErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class InputUtil {
    public final static InputUtil INSTANCE = new InputUtil();
    public final static Map<String,Integer> WEEK = Map.of("월",0,"화",1,"수",2,"목",2,"금",4);
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


    public int inputInt(String msg){

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
    public Time inputTime(String msg) {

        System.out.print(msg);
        while(true) {
            try{
                Time time = strToTime(bf.readLine());
                return time;
            } catch(IOException e){
                OutputUtil.errorMessage("잘못된 입력입니다.");
                return null;
            } catch (OutOfWeekdayException e) {
                OutputUtil.errorMessage(e.getMsg());
            } catch (TimeFormErrorException e) {
                OutputUtil.errorMessage(e.getMsg());
            } catch (LackOfElementException e) {
                OutputUtil.errorMessage(e.getMsg());
            }
        }
    }

    public Lecture inputLectureInfo(){

        String lecId = inputStr("강의 코드 : ");
        String lecType = inputStr("강의 타입 : ");
        String lecName = inputStr("강의 제목 : ");
        List<Time> lecTime = new ArrayList<>();
        int cnt = inputInt("강의 횟수 : ");
        for (int i = 0; i < cnt; i++) {
            System.out.println("*** 형식에 맞게 입력해주세요(요일 시작시간 종료시간) ***");
            Time time = inputTime("시간" + (i+1) + " : ");
            lecTime.add(time);
        }
        int lecCredit = inputInt("학점 : ");

        Lecture lecture = new Lecture(lecId,lecType,lecName,lecTime,lecCredit);
        return lecture;
    }
    public Time strToTime(String timeStr) throws LackOfElementException, OutOfWeekdayException, TimeFormErrorException {
        StringTokenizer st = new StringTokenizer(timeStr, " ");
        if(st.countTokens() != 3){
            throw new LackOfElementException("필요한 정보를 모두 입력해주세요.(요일 시작시간 종료시간)");
        }else{
            String day = st.nextToken();
            String dayTime = "2022-09-" + String.format("%02d",dayOfWeekToInt(day) + 5) + " ";
            String startTime = dayTime + (timeFormCheck(st.nextToken()) + ":00.0");
            String endTime = dayTime + (timeFormCheck(st.nextToken())  + ":00.0");

            Time time = new Time(day, Timestamp.valueOf(startTime), Timestamp.valueOf(endTime));
            System.out.println("strToTime" + time.toString());
            System.out.println(timeStr);
            return time;
        }
    }
    public String timeFormCheck(String timeStr) throws TimeFormErrorException {
        StringTokenizer st = new StringTokenizer(timeStr, ":");

        if(st.countTokens() == 2){
            String hour = st.nextToken();
            String minute = st.nextToken();

            if(hour.length() == 2
                    && minute.length() ==2
                    && (Integer.parseInt(hour) < 24 && Integer.parseInt(hour) >= 0)
                    && (Integer.parseInt(minute) < 60 && Integer.parseInt(minute) >= 0)
            ){
                return timeStr;
            }else{
                throw new TimeFormErrorException("형식에 맞게 입력해주세요.(E HH:mm HH:mm)");
            }
        }else{
            throw new TimeFormErrorException("형식에 맞게 입력해주세요.(E HH:mm HH:mm)");
        }
    }

    public static int dayOfWeekToInt(String day) throws OutOfWeekdayException {
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

        time.setDay(conTimeFormat.format(start));
        time.setStartTime(start);
        time.setEndTime(end);

        return time;
    }
}
