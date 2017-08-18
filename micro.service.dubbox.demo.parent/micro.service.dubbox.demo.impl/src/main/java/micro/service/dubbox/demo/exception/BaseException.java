package micro.service.dubbox.demo.exception;


import java.io.Serializable;

public class BaseException implements Serializable {

    private int error;

    private String error_discription;

    public BaseException() {

    }

    public BaseException(Integer error, String error_discription) {
        this.error = error;
        this.error_discription = error_discription;
    }


    public static BaseException create(Integer error, String error_discription) {
        return new BaseException(error, error_discription);
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getError_discription() {
        return error_discription;
    }

    public void setError_discription(String error_discription) {
        this.error_discription = error_discription;
    }
}
