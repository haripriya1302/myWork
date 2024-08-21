package exceptions;

import models.ErrorCode;

public class RestaurantServiceException extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;
    public RestaurantServiceException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
