package repositories;

import model.Booking;

import java.util.HashMap;

public class BookingRepository {
    private BookingRepository bookingRepository = null;

    public BookingRepository getInstance() {
        if(bookingRepository == null) {
            bookingRepository = new BookingRepository();
        }
        return bookingRepository;
    }

    public HashMap<String, Booking> bookingHashMap = new HashMap<>();
    public HashMap<String, String> bookingToCustomerHashMap = new HashMap<>();

    public void addBookingToCustomerMap(String id, String customerId) {
        bookingToCustomerHashMap.put(id, customerId);
    }

    public void addBooking(String bookingId, Booking booking) {
        bookingHashMap.put(bookingId, booking);
    }

    public boolean isBookingValid(String bookingId) {
        if(bookingHashMap.containsKey(bookingId)) {
            return true;
        }
        return false;
    }

    public Booking getBooking(String bookingId) {
        return bookingHashMap.get(bookingId);
    }

    public void updateBooking(String bookingId, Booking booking) {
        bookingHashMap.put(bookingId, booking);
    }
}
