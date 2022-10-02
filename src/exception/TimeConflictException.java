package exception;

public class TimeConflictException extends Exception {

    String msg;
    public TimeConflictException() {}

    public TimeConflictException(String msg) {
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
