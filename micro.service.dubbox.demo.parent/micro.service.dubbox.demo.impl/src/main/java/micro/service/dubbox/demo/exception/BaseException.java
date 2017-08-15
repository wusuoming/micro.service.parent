package micro.service.dubbox.demo.exception;


import java.io.Serializable;

public class BaseException implements Serializable {

    private int code;

    private String message;

    public BaseException() {

    }

    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public static BaseException create(Integer code, String message) {
        return new BaseException(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
