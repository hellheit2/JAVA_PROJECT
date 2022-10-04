package exception;

public class UserDuplicationException extends Exception {

    String msg;
    public UserDuplicationException() {}

    public UserDuplicationException(String msg) {
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
