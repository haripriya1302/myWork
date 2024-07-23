package service;

import exceptions.ErrorCodes;
import exceptions.GymException;
import model.*;
import repositories.BookingRepository;
import repositories.CustomerRepository;
import repositories.GymClassRepository;
import repositories.GymRepository;
import utils.ErrorUtil;
import utils.GymUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GymClassService {

    private GymUtil gymUtil;

    private GymClassRepository gymClassRepository;

    private GymRepository gymRepository;

    private CustomerRepository customerRepository;

    private BookingRepository bookingRepository;
    private ErrorUtil errorUtil;

    public GymClassService(GymUtil gymUtil, GymRepository gymRepository,
                           GymClassRepository gymClassRepository,
                           CustomerRepository customerRepository,
                           BookingRepository bookingRepository) {
        this.gymRepository = gymRepository;
        this.gymClassRepository = gymClassRepository;
        this.gymUtil = gymUtil;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

    public String addClass(String gymId, ClassType classType, int maxLimit, int startTime, int endTime){
        if(!gymRepository.isGymValid(gymId)) {
            throw new RuntimeException("GymId Doesn't exist");
        }
        Gym gym = gymRepository.getGym(gymId);
        int curroccupiedcapacity = 0;
        for(String classId : gym.getClasses()){
            GymClass fetchedClass = gymClassRepository.getGymClass(classId);
            if(fetchedClass.getStartTime() == startTime || fetchedClass.getEndTime() == endTime) {
                curroccupiedcapacity += fetchedClass.getMaxLimit();
            }
        }
        if(curroccupiedcapacity+maxLimit > gym.getMaxAccommodation()) {
            throw new GymException(ErrorCodes.GYM_CAPACITY_REACHED, ErrorUtil.errorCodesToMessageMap.get(ErrorCodes.GYM_CAPACITY_REACHED));
        }

        String classId = gymUtil.generateUUID();
        GymClass gymClass = new GymClass();
        while(gymUtil.checkIdExistsInMap(classId, gymClassRepository.classes.keySet())) {
            classId = gymUtil.generateUUID();
        }
        gymClass.setClassId(classId);
        gymClass.setClassType(classType);
        gymClass.setGymId(gymId);
        gymClass.setMaxLimit(maxLimit);
        gymClass.setEndTime(endTime);
        gymClass.setStartTime(startTime);
        gymClass.setBookingIds(new ArrayList<>());
        gymClass.setClassStatus(ClassStatus.AVAILABLE);
        gymClassRepository.addClass(classId, gymClass);
        gymRepository.addClassToGym(classId, gymId);
        return classId;
    }

    public void removeClass(String gymId, String classId) {
        if(!gymRepository.isGymValid(gymId)) {
            throw new RuntimeException("GymId Doesn't exist");
        }
        Gym gym = gymRepository.getGym(gymId);
        List<String> classes = gym.getClasses();
        List<String> modifiedList = new ArrayList<>();
        for(String gymClass : classes) {
            if(gymClass != classId) {
                modifiedList.add(gymClass);
            }
        }
        gym.setClasses(modifiedList);
        gymRepository.updateGym(gymId, gym);
        gymClassRepository.removeClass(classId);
    }

    public String bookClass(String customerId, String gymId, String classId) {
        if(!customerRepository.isValidCustomer(customerId)) {
            throw new RuntimeException("Not a Valid Customer");
        }
        if(!gymRepository.isGymValid(gymId)) {
            throw new RuntimeException("Not a Valid Gym");
        }
        if(!gymClassRepository.isValidClass(classId)) {
            throw new RuntimeException("Not a Valid Class");
        }
        String id = gymUtil.generateUUID();
        while(gymUtil.checkIdExistsInMap(id, bookingRepository.bookingToCustomerHashMap.keySet() )) {
            id = gymUtil.generateUUID();
        }
        Booking booking = new Booking();
        booking.setClassId(classId);
        booking.setGymId(gymId);
        booking.setCustomerId(customerId);
        booking.setBookingId(id);
        booking.setBookingStatus(BookingStatus.BOOKED);
        Customer customer = customerRepository.getCustomer(customerId);
        customer.getBookingList().add(booking);
        customerRepository.updateCustomer(customerId, customer);
        bookingRepository.addBookingToCustomerMap(id, customerId);
        bookingRepository.addBooking(id, booking);
        GymClass gymClass = gymClassRepository.getGymClass(classId);
        gymClass.getBookingIds().add(id);

        return id;
    }

    public List<Booking> getAllBookings(String customerId) {
        if(!customerRepository.isValidCustomer(customerId)) {
            throw new RuntimeException("Not a Valid Customer");
        }
        Customer customer = customerRepository.getCustomer(customerId);
        return customer.getBookingList();
    }

    public void cancelBooking(String bookingId) {
        if(!bookingRepository.isBookingValid(bookingId)) {
            throw new RuntimeException("Not a Valid Booking");
        }
        Booking booking = bookingRepository.getBooking(bookingId);
        booking.setBookingStatus(BookingStatus.CANCELED);
        String customerId = booking.getCustomerId();
        Customer customer = customerRepository.getCustomer(customerId);
        List<Booking> bookingList = customer.getBookingList();
        for(Booking booking1 : bookingList){
            if(booking1.getBookingId() == bookingId) {
                bookingList.remove(booking1);
                bookingList.add(booking);
            }
        }
        customerRepository.updateCustomer(customerId, customer);
    }

    public void cancelClasses(String gymId) {
        Gym gym = gymRepository.getGym(gymId);
        List<String> gymClassIds = gym.getClasses();
        for(String gymClassId : gymClassIds) {
            GymClass gymClass = gymClassRepository.getGymClass(gymClassId);
            List<String> bookingIds = gymClass.getBookingIds();

            for(String bookingId: bookingIds) {
                Booking booking = bookingRepository.getBooking(bookingId);
                String customerId = booking.getCustomerId();
                booking.setBookingStatus(BookingStatus.CANCELED);
                bookingRepository.updateBooking(bookingId, booking);
                Customer customer = customerRepository.getCustomer(customerId);
                List<Booking> bkList = customer.getBookingList();
                List<Booking> modifiedBkList = new ArrayList<>();
                for(Booking bk:  bkList) {
                    if(!bk.getBookingId().equals(bookingId)) {
                        modifiedBkList.add(bk);
                    } else {
                        modifiedBkList.add(booking);
                    }
                }
                customer.setBookingList(modifiedBkList);
                customerRepository.updateCustomer(customerId, customer);
            }

        }
    }
}
