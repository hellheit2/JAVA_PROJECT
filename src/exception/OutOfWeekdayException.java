package exception;

public class OutOfWeekdayException extends Exception {

    String msg;
    public OutOfWeekdayException() {}

    public OutOfWeekdayException(String msg) {
        setMsg(msg);
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
