package utils;

import java.util.UUID;

public class RestaurantUtil {

    private static RestaurantUtil restaurantUtil = null;
    public static RestaurantUtil getInstance() {
        if(restaurantUtil == null) {
            return new RestaurantUtil();
        }
        return restaurantUtil;
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
