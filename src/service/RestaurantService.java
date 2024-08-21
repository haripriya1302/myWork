package service;

import models.Restaurant;

import java.time.DayOfWeek;
import java.util.List;

public interface RestaurantService {
    int addRestaurant(int userId, String name, String area, int noOfTables,
                      int noOfAvailableTables, int startTime, int endTime, List<DayOfWeek> exceptions, String city);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getRestaurantsByCity(String city);

    void makeReservation(int restaurantId, int noOfTables, int start, int end, int userId);

    List<Restaurant> searchRestaurants(String name, final int fromTime, final int endTime);
}