package repositories;

import exceptions.RestaurantServiceException;
import models.ErrorCode;
import models.Reservation;
import models.Restaurant;
import utils.ErrorUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestaurantRepository {

    private HashMap<Integer, Restaurant> restaurants = new HashMap<>();

    private HashMap<Integer, HashSet<Integer>> reservations = new HashMap<>();

    private static RestaurantRepository restaurantRepository = null;

    public static RestaurantRepository getInstance(){
        if(restaurantRepository == null) {
            return new RestaurantRepository();
        }
        return restaurantRepository;
    }



    public void addRestaurant(int id, Restaurant restaurant){
        restaurants.put(id, restaurant);
    }

    public void updateRestaurant(int id, Restaurant restaurant) {
        if(isvalidRestaurant(id)) {
            restaurants.put(id, restaurant);
        } else {
            new RestaurantServiceException(ErrorCode.RESTAURANT_DOESNT_EXIST,
                    ErrorUtil.errorCodeHashMap.get(ErrorCode.RESTAURANT_DOESNT_EXIST));
        }
    }

    private boolean isvalidRestaurant(int id) {
        return restaurants.keySet().contains(id);
    }

    public Set<Integer> getAllRestaurantIds() {
        return restaurants.keySet();
    }

    public Restaurant getRestaurant(int restaurantId) {
        if(isvalidRestaurant(restaurantId)) {
             return restaurants.get(restaurantId);
        }

        throw new RestaurantServiceException(ErrorCode.RESTAURANT_DOESNT_EXIST,
                    ErrorUtil.errorCodeHashMap.get(ErrorCode.RESTAURANT_DOESNT_EXIST));
    }

    public HashSet<Integer> getReservationsForRestaurant(int restaurantId) {
        return reservations.get(restaurantId) == null? new HashSet<>() : reservations.get(restaurantId);
    }

    public void addReservationToRestaurant(int restaurantId, int id) {
        reservations.computeIfAbsent(restaurantId, k-> new HashSet<>()).add(id);
    }
}
