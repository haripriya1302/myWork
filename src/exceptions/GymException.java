package exceptions;

public class GymException extends RuntimeException{

    private ErrorCodes errorCode;
    private String errorMessage;



    public GymException(ErrorCodes errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
