package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Customer {
    private String id;
    private String name;
    private List<Booking> bookingList;
}
