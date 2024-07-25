package repositories;

import error.OrderingSystemException;
import models.ErrorCode;
import models.Restaurant;
import utils.ErrorUtil;

import java.util.HashMap;

public class RestaurantRepository {

    private RestaurantRepository restaurantRepository = null;

    public RestaurantRepository getInstance() {
        if(restaurantRepository == null) {
            restaurantRepository = new RestaurantRepository();
        }
        return restaurantRepository;
    }
    public HashMap<String, Restaurant> restaurantHashMap = new HashMap<>();

    public void addRestaurant(String id, Restaurant restaurant){
        restaurantHashMap.put(id, restaurant);
    }

    public void removeRestaurant(String id) {
        restaurantHashMap.remove(id);
    }

    public Restaurant getRestaurant(String id) {
        if(restaurantHashMap.containsKey(id)) {
            return restaurantHashMap.get(id);
        }
        throw new OrderingSystemException(ErrorCode.RESTAURANT_NOT_FOUND,
                ErrorUtil.errorCodeMessageHashMap.get(ErrorCode.RESTAURANT_NOT_FOUND));
    }

    public boolean isPresent(String id) {
        return restaurantHashMap.containsKey(id)? true : false;
    }

    public void update(String restaurant_name, Restaurant restaurant) {
        restaurantHashMap.put(restaurant_name, restaurant);
    }
}
