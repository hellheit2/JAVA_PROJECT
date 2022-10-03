package exception;

public class LackOfElementException extends Exception {

    String msg;
    public LackOfElementException() {}

    public LackOfElementException(String msg) {
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
