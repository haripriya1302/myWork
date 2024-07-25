import error.OrderingSystemException;
import models.User;
import models.UserType;
import repositories.OrderRepository;
import repositories.RestaurantRepository;
import repositories.UserRepository;
import service.RestaurantService;
import service.impl.RestaurantServiceImpl;
import service.impl.UserServiceImpl;
import utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * Admin Flows:
     *
     * 1. addRestaurant(name, location)
     *
     * Admin will have permissions to create a restaurant. Each restaurant will have a unique name and location.
     *
     * 2. removeRestaurant(restaurant_id)
     *
     * Admin can remove a restaurant which will also cancel all pending orders for that restaurant.
     *
     * 3. addMenuItem(restaurant_id, item_name, price)
     *
     * Admin will have permissions to add a menu item to a restaurant. Each menu item will have a unique name and a price.
     *
     * 4. removeMenuItem(restaurant_id, item_id)
     *
     * Admin will have permissions to remove a menu item from a restaurant.
     *
     *
     * Customer Flows:
     *
     * 1. placeOrder(customer_id, restaurant_id, List<item_id>)
     *
     * Customers can place an order from a restaurant if the restaurant and menu items are available. An order will include a list of item IDs.
     *
     * 2. getOrderStatus(order_id)
     *
     * Customers can view the status of their order (e.g., placed, preparing, out for delivery, delivered).
     *
     * 3. cancelOrder(order_id)
     *
     * Customers can cancel an order if it has not been delivered yet.
     *
     *
     * Few Examples:
     *
     * addRestaurant("Restaurant1", "MG Road") Success -> returns restaurant1_id
     *
     * addMenuItem("restaurant1_id", "Pizza", 250) Success -> returns item1_id
     *
     * placeOrder("customer1", "restaurant1_id", ["item1_id", "item2_id"]) Success -> returns order1_id
     * @param args
     */
    public static void main(String[] args) {
        RestaurantRepository restaurantRepository = new RestaurantRepository().getInstance();
        UserRepository userRepository = new UserRepository().getInstance();
        OrderRepository orderRepository = new OrderRepository().getInstance();
        CommonUtil commonUtil = new CommonUtil();
        RestaurantServiceImpl restaurantService = new RestaurantServiceImpl(restaurantRepository, commonUtil,
                userRepository, orderRepository);
        UserServiceImpl userService = new UserServiceImpl(commonUtil, userRepository);
        String adminId = userService.createUser("Dreamer", UserType.ADMIN); // Will have to send the user to methods to validate permissions.
        String customerId = userService.createUser("Dreamer", UserType.CUSTOMER);
        try {

            restaurantService.addRestaurant("Restaurant1", "MG Road");

            // restaurantService.addRestaurant("Restaurant1", "MG Road");
            // restaurantService.removeRestaurant("Restaurant2");

            restaurantService.addMenuItem("Restaurant1", "Samosa", 5.00);

            restaurantService.removeMenuItem("Restaurant1", 1);



            List<Integer> itemsToOrder = new ArrayList<>();
            itemsToOrder.add(1);itemsToOrder.add(1);itemsToOrder.add(1);itemsToOrder.add(1);
            restaurantService.placeOrder(customerId, "Restaurant1", itemsToOrder);
            restaurantService.removeRestaurant("Restaurant1");
            System.out.println("Moved");

        } catch (OrderingSystemException exception) {
            System.out.println(exception.getMessage());
        }

    }
}