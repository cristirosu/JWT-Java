package exceptions;

/**
 * Created by User on 11/2/2016.
 */
public class RPGException extends Exception {

    private int status = 500;
    private String errorMessage;

    public RPGException() {

    }

    public RPGException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RPGException(int status, String errorMessage){
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "RPGException{" +
                "status=" + status +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
