package utils;

import models.ErrorCode;

import java.util.HashMap;

public class ErrorUtil {

    public static HashMap<ErrorCode, String> errorCodeMessageHashMap = new HashMap<>();

    static {
        errorCodeMessageHashMap.put(ErrorCode.ITEM_NOT_FOUND, "Item not available in the Restaurant");
        errorCodeMessageHashMap.put(ErrorCode.ITEM_UNAVAILABLE, "Item not currently in the Menu");
        errorCodeMessageHashMap.put(ErrorCode.RESTAURANT_CLOSED, "Restaurant is closed");
        errorCodeMessageHashMap.put(ErrorCode.RESTAURANT_NOT_FOUND, "Restaurant is not found");
        errorCodeMessageHashMap.put(ErrorCode.USER_DOES_NOT_EXIST, "User not found");
        errorCodeMessageHashMap.put(ErrorCode.ORDER_NOT_FOUND, "No Order Exists");
        errorCodeMessageHashMap.put(ErrorCode.RESTAURANT_ALREADY_EXISTS, "Restaurant Already Exists, Cannot create new one");
    }
}
