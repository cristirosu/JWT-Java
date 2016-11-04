package exceptions;

/**
 * Created by User on 11/2/2016.
 */
public class GenericException {

    private int status;
    private String errorMessage;

    public GenericException() {
    }

    public GenericException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public GenericException(int status, String errorMessage) {
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
        return "GenericException{" +
                "status=" + status +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
