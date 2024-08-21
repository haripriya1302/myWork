package models;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;
@Getter@Setter
public class RestaurantSchedule {
    int startTime;
    int endTime;
    List<DayOfWeek> closedDays;

    public RestaurantSchedule(int startTime, int endTime, List<DayOfWeek> closedDays) {
        this.closedDays = closedDays;
        this.endTime = endTime;
        this.startTime = startTime;
    }
}
