package error;

import models.ErrorCode;

public class OrderingSystemException extends RuntimeException{
    private ErrorCode errorCode;
    private String errorString;

    public OrderingSystemException(ErrorCode errorCode, String errorString) {
        super(errorString);
        this.errorCode = errorCode;
        this.errorString = errorString;
    }
}
