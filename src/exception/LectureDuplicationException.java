package exception;

public class LectureDuplicationException extends Exception {

    String msg;
    public LectureDuplicationException() {}

    public LectureDuplicationException(String msg) {
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
