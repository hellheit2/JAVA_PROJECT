package exception;

public class TimeFormErrorException extends Exception {

    String msg;
    public TimeFormErrorException() {}

    public TimeFormErrorException(String msg) {
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
