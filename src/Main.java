import exceptions.RestaurantServiceException;
import models.Restaurant;
import models.UserType;
import service.impl.RestaurantServiceImpl;
import service.impl.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Services

        UserServiceImpl userService = UserServiceImpl.getInstance();

        RestaurantServiceImpl restaurantService = RestaurantServiceImpl.getInstance(userService);


        int userId1 = userService.createUser("Lata", UserType.ADMIN);
        int userId2 = userService.createUser("Hari", UserType.CUSTOMER);

        int restId1 = restaurantService.addRestaurant(userId1, "Fatty Bao", "Indiranagar", 5,
                5, 9, 20, List.of(), "Bengaluru");
        int restId3 = restaurantService.addRestaurant(userId1, "Fatty Bao2", "Indiranagar", 5,
                5, 9, 20, List.of(), "Bengaluru");
        try {
            int restId2 = restaurantService.addRestaurant(userId2, "Fatty Bao", "Indiranagar", 5,
                    5, 9, 20, List.of(), "Hyderabad");
        } catch (RestaurantServiceException restaurantServiceException) {
            System.out.println(restaurantServiceException.getMessage());
        }
        System.out.println(restaurantService.getRestaurantsByCity("Hyderabad").size());


        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        restaurantService.makeReservation(restId1, 2, 6, 7, userId1);
        restaurantService.makeReservation(restId1, 2, 6, 7, userId1);
        try {
            restaurantService.makeReservation(restId1, 2, 6, 7, userId1);
        } catch(RestaurantServiceException exception) {
            System.out.println(exception.getMessage());
        }

        List<Restaurant> restaurants1 = restaurantService.searchRestaurants("Fatty", 11, 12);
        System.out.println(restaurants1.size());
        for(Restaurant rest : restaurants1) {
            System.out.println(rest.getName());
        }


    }
}