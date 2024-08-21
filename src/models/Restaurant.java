package models;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter@Setter
public class Restaurant {
    private static AtomicInteger base = new AtomicInteger(1);
    int id;
    String name;
    String city;
    String area;
    int noOfTables;
    int noOfAvailableTables;

    RestaurantSchedule restaurantSchedule;

    public Restaurant(String name, String area, int noOfTables, int noOfAvailableTables,
                      int startTime, int endTime, List<DayOfWeek> closedDays, String city) {
        this.id = base.getAndIncrement();
        this.name = name;
        this.city = city;
        this.area = area;
        this.noOfTables = noOfTables;
        this.noOfAvailableTables = noOfAvailableTables;
        this.restaurantSchedule = new RestaurantSchedule(startTime, endTime, closedDays);
    }

}
