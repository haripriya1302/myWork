import model.Booking;
import model.ClassType;
import repositories.BookingRepository;
import repositories.CustomerRepository;
import repositories.GymClassRepository;
import repositories.GymRepository;
import service.CustomerService;
import service.GymClassService;
import service.GymService;
import utils.GymUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GymRepository gymRepository = new GymRepository().getInstance();
        GymClassRepository gymClassRepository = new GymClassRepository().getInstance();
        CustomerRepository customerRepository = new CustomerRepository().getInstance();
        BookingRepository bookingRepository = new BookingRepository().getInstance();
        GymUtil gymUtil = new GymUtil();


        GymClassService gymClassService = new GymClassService(gymUtil, gymRepository,
                gymClassRepository, customerRepository, bookingRepository);
        GymService gymService = new GymService(gymUtil, gymRepository, gymClassService);

        String gym1 = gymService.addGym("Gym1", "Indira Nagar", 200);
        String gym2 = gymService.addGym("Gym3", "Indira Nagar", 100);

        String classId1 = gymClassService.addClass(gym1, ClassType.CARDIO, 20, 6, 7);

        CustomerService customerService = new CustomerService(gymUtil, customerRepository);
        String customerId1 = customerService.createCustomer("Lata");
        String bookingId = gymClassService.bookClass(customerId1, gym1, classId1);

        List<Booking> bookings = gymClassService.getAllBookings(customerId1);
        System.out.println(bookings);

        gymClassService.cancelBooking(bookingId);
        gymClassService.removeClass(gym1, classId1);
        gymService.removeGym(gym1); // Get back for class logic
    }
}