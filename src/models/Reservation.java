package models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Getter@Setter
public class Reservation {
    private static AtomicInteger base = new AtomicInteger(1);
    int id;
    int restaurantId;
    int userId;
    int noOfTables;
    Date date;
    DiningSlot diningSlot;
    public Reservation(int restaurantId, int userId, int noOfTables, Date date, int startTime, int endTime) {
        this.id = base.getAndIncrement();
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.noOfTables = noOfTables;
        this.date = date;
        this.diningSlot = new DiningSlot(restaurantId, startTime, endTime);
    }

}
