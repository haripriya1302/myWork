package service;

import java.util.List;

public interface RestaurantService {
     void addRestaurant(String name, String location);
     void removeRestaurant(String name);

     void addMenuItem(String restaurant_name, String itemName, double price);

     void removeMenuItem(String restaurant_id, int item_id);

     int placeOrder(String customerId, String restaurant_id, List<Integer> items);
}
