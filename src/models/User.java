package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Setter@Getter
public class User {

    private static AtomicInteger base = new AtomicInteger(1);
    int id;
    String name;
    UserType userType;
    List<Reservation> reservationList;

    public User(String name, UserType userType) {
        this.id = base.getAndIncrement();
        this.name = name;
        this.userType = userType;
        this.reservationList = new ArrayList<>();
    }
}
