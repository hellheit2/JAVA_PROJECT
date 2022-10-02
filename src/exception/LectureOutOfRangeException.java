package exception;

public class LectureOutOfRangeException extends Exception {

    String msg;
    public LectureOutOfRangeException() {}

    public LectureOutOfRangeException(String msg) {
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
