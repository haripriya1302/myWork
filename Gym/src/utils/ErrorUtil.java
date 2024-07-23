package utils;

import exceptions.ErrorCodes;

import java.util.HashMap;

public class ErrorUtil {

    public static HashMap<ErrorCodes, String> errorCodesToMessageMap = new HashMap<>();
    static {
        errorCodesToMessageMap.put(ErrorCodes.CLASS_NOT_FOUND, "Gym Class Not Found");
        errorCodesToMessageMap.put(ErrorCodes.GYM_NOT_FOUND, "Gym Not Found");
        errorCodesToMessageMap.put(ErrorCodes.GYM_CAPACITY_REACHED, "Gym Capacity Reached");
        errorCodesToMessageMap.put(ErrorCodes.CLASS_CAPACITY_REACHED, "Class Capacity Reached");
    }
}
