package utils;

import models.ErrorCode;

import java.util.HashMap;

public class ErrorUtil {
    public static HashMap<ErrorCode, String> errorCodeHashMap = new HashMap<>();
    static {
            errorCodeHashMap.put(ErrorCode.INVALID_RESTAURANT, "Restaurant Doesn't Exist");
            errorCodeHashMap.put(ErrorCode.NO_AVAILABLE_TABLES, "Restaurant Capacity Reached");
            errorCodeHashMap.put(ErrorCode.UNAUTHORIZED, "Unauthorized to add a restaurant");
            errorCodeHashMap.put(ErrorCode.USER_DOESNT_EXIST, "User Doesn't exist");
            errorCodeHashMap.put(ErrorCode.RESTAURANT_DOESNT_EXIST, "Restaurant Doesn't exist");
            errorCodeHashMap.put(ErrorCode.RESERVATION_DOES_NOT_EXIST, "Reservation Doesn't exist");
    }
}
