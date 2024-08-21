package models;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter@Setter
public class DiningSlot {
    private static AtomicInteger base = new AtomicInteger(1);
    int id;
    int restaurantId;
    int startTime;
    int endTime;

    public DiningSlot(int restaurantId, int startTime, int endTime) {
        this.id = base.getAndIncrement();
        this.restaurantId = restaurantId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
