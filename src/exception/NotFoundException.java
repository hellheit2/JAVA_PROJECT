package exception;

public class NotFoundException extends Exception {

    String msg;

    public NotFoundException() {}

    public NotFoundException(String msg) {
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
