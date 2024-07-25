package service.impl;

import error.OrderingSystemException;
import models.*;
import repositories.OrderRepository;
import repositories.RestaurantRepository;
import repositories.UserRepository;
import service.RestaurantService;
import utils.CommonUtil;
import utils.ErrorUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;
    private CommonUtil commonUtil;

    private OrderRepository orderRepository;
    private UserRepository userRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, CommonUtil commonUtil,
                                 UserRepository userRepository, OrderRepository orderRepository) {
        this.restaurantRepository = restaurantRepository;
        this.commonUtil = commonUtil;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }
    @Override
    public void addRestaurant(String name, String location) {
        if(restaurantRepository.isPresent(name)) {
            throw new OrderingSystemException(ErrorCode.RESTAURANT_ALREADY_EXISTS,
                    ErrorUtil.errorCodeMessageHashMap.get(ErrorCode.RESTAURANT_ALREADY_EXISTS));
        }
        Restaurant restaurant  = new Restaurant();
        restaurant.setLocation(location);
        restaurant.setName(name);
        Menu menu = new Menu();
        menu.setId(commonUtil.generateUUID());
        menu.setAvailableItems(new HashSet<>());
        menu.setUnavailableItems(new HashSet<>());
        restaurant.setMenu(menu);
        restaurantRepository.addRestaurant(name, restaurant);
    }

    @Override
    public void removeRestaurant(String name) {
        if(!restaurantRepository.isPresent(name)) {
            throw new OrderingSystemException(ErrorCode.RESTAURANT_NOT_FOUND,
                    ErrorUtil.errorCodeMessageHashMap.get(ErrorCode.RESTAURANT_NOT_FOUND));
        }
        restaurantRepository.removeRestaurant(name);
    }

    @Override
    public void addMenuItem(String restaurant_name, String itemName, double price) {
        if(!restaurantRepository.isPresent(restaurant_name)) {
            throw new OrderingSystemException(ErrorCode.RESTAURANT_NOT_FOUND,
                    ErrorUtil.errorCodeMessageHashMap.get(ErrorCode.RESTAURANT_NOT_FOUND));
        }
        //This method overrides the existing items price.
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurant_name);
        Menu menu = restaurant.getMenu();
        Item item = new Item(itemName, price);
        menu.getAvailableItems().add(item);
        restaurant.setMenu(menu);
        restaurantRepository.update(restaurant_name, restaurant);
    }

    @Override
    public void removeMenuItem(String restaurant_id, int item_id) {
        if(!restaurantRepository.isPresent(restaurant_id)) {
            throw new OrderingSystemException(ErrorCode.RESTAURANT_NOT_FOUND,
                    ErrorUtil.errorCodeMessageHashMap.get(ErrorCode.RESTAURANT_NOT_FOUND));
        }
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurant_id);
        Menu menu = restaurant.getMenu();
        HashSet<Item> availableItems = menu.getAvailableItems();
        for(Item item : availableItems) {
            if(item.getId() == item_id) {
                availableItems.remove(item);
                menu.getUnavailableItems().add(item);
            }
        }

        restaurant.setMenu(menu);
        restaurantRepository.update(restaurant_id, restaurant);
    }

    @Override
    public int placeOrder(String customerId, String restaurant_id, List<Integer> items) {
        if(!userRepository.isValidCustomer(customerId)) {
            throw new OrderingSystemException(ErrorCode.USER_DOES_NOT_EXIST,
                    ErrorUtil.errorCodeMessageHashMap.get(ErrorCode.USER_DOES_NOT_EXIST));
        }
        if(!restaurantRepository.isPresent(restaurant_id)) {
            throw new OrderingSystemException(ErrorCode.RESTAURANT_NOT_FOUND,
                    ErrorUtil.errorCodeMessageHashMap.get(ErrorCode.RESTAURANT_NOT_FOUND));
        }
        HashMap<Integer, Integer> orderMap = new HashMap<>();
        for (int itemId:  items) {
            orderMap.put(itemId, orderMap.getOrDefault(itemId, 0)+1);
        }
        List<OrderedItem> orderItems = new ArrayList<>();
        for(int key: orderMap.keySet()) {
            OrderedItem item = new OrderedItem();
            item.setRestaurantId(restaurant_id);
            item.setQuantity(orderMap.get(key));
            item.setItemId(key);
            orderItems.add(item);
        }

        Order order = new Order(restaurant_id, orderItems, OrderStatus.PLACED);
        orderRepository.addOrder(order.getId(), order);
        return order.getId();
    }


}
