package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {
    private String bookingId;
    private String customerId;
    private String gymId;
    private String classId;
    private BookingStatus bookingStatus;
}
