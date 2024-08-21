package service.impl;

import exceptions.RestaurantServiceException;
import models.ErrorCode;
import models.Reservation;
import models.Restaurant;
import repositories.ReservationRepository;
import repositories.RestaurantRepository;
import service.RestaurantService;
import utils.ErrorUtil;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantServiceImpl implements RestaurantService {
    private UserServiceImpl userService;
    private RestaurantRepository restaurantRepository;

    private ReservationRepository reservationRepository;

    private static RestaurantServiceImpl restaurantService = null;

    public static RestaurantServiceImpl getInstance(UserServiceImpl userService) {
        if(restaurantService == null) {
            return new RestaurantServiceImpl(userService);
        }
        return restaurantService;
    }

    public RestaurantServiceImpl(UserServiceImpl userService) {
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.userService = userService;
        this.reservationRepository = ReservationRepository.getInstance();
    }
    @Override
    public synchronized int addRestaurant(int userId, String name, String area, int noOfTables,
                                           int noOfAvailableTables, int startTime, int endTime, List<DayOfWeek> closedDays, String city) {
        if(!userService.isUserAdmin(userId)) {
            throw new RestaurantServiceException(ErrorCode.UNAUTHORIZED, ErrorUtil.errorCodeHashMap.get(ErrorCode.UNAUTHORIZED));
        }
        Restaurant restaurant = new Restaurant(name, area, noOfTables, noOfAvailableTables, startTime, endTime, closedDays, city);
        restaurantRepository.addRestaurant(restaurant.getId(), restaurant);
        return restaurant.getId();
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        Set<Integer> restaurantIds = restaurantRepository.getAllRestaurantIds();
        List<Restaurant> restaurants = new ArrayList<>();
        for(int restaurantId: restaurantIds) {
            restaurants.add(restaurantRepository.getRestaurant(restaurantId));
        }
        return restaurants;
    }

    @Override
    public List<Restaurant> getRestaurantsByCity(String city) {
        List<Restaurant> filtered = new ArrayList<>();
        List<Restaurant> allRestaurants = getAllRestaurants();
        for(Restaurant restaurant : allRestaurants) {
            if((restaurant.getCity()).equals(city)) {
                filtered.add(restaurant);
            }
        }
        return filtered;
    }

    @Override
    public synchronized void makeReservation(int restaurantId, int noOfTables, int start, int end, int userId) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantId);

        //Validation
        Date date = new Date();
        if(noOfTables <= restaurant.getNoOfTables() &&
                isReservationPossible(restaurantId, date, start, restaurant.getNoOfTables(), noOfTables)) {
            Reservation reservation = new Reservation(restaurantId, userId, noOfTables, date, start, end);
            reservationRepository.addReservation(reservation.getId(), reservation);
            restaurantRepository.addReservationToRestaurant(restaurantId, reservation.getId());
        } else {
            throw new RestaurantServiceException(ErrorCode.NO_AVAILABLE_TABLES,
                    ErrorUtil.errorCodeHashMap.get(ErrorCode.NO_AVAILABLE_TABLES));
        }
    }

    @Override
    public List<Restaurant> searchRestaurants(String name, int fromTime, int endTime) {
        List<Restaurant> restaurants = getAllRestaurants();
        return restaurants.stream().filter( rt -> rt.getName().contains(name))
                .filter(st -> st.getRestaurantSchedule().getStartTime() <= fromTime)
                .filter(st -> st.getRestaurantSchedule().getEndTime() >= endTime).collect(Collectors.toList());

    }

    private boolean isReservationPossible(int restaurantId, Date date, int start, int capacity, int noOfTables) {
        HashSet<Integer> bookedReservationIds = restaurantRepository.getReservationsForRestaurant(restaurantId);
        int count = 0;
        if(bookedReservationIds.size() == 0) return true;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for(Integer reservationId : bookedReservationIds) {
            Reservation reservation = reservationRepository.getReservation(reservationId);
            if(formatter.format(date).equals(formatter.format(reservation.getDate())) && start == reservation.getDiningSlot().getStartTime()) {
                count += reservation.getNoOfTables();
            }
        }
        count += noOfTables;
        System.out.println(count);
        if(count < capacity) {
            return true;
        }
        return false;
    }




}
