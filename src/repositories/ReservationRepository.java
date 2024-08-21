package repositories;

import exceptions.RestaurantServiceException;
import models.ErrorCode;
import models.Reservation;
import utils.ErrorUtil;

import java.util.HashMap;
import java.util.Set;

public class ReservationRepository {

    HashMap<Integer, Reservation> reservations = new HashMap<>();

    private static ReservationRepository reservationRepository = null;

    public static ReservationRepository getInstance(){
        if(reservationRepository == null) {
            return new ReservationRepository();
        }
        return reservationRepository;
    }



    public void addReservation(int id, Reservation reservation){
        reservations.put(id, reservation);
    }

    public void updateReservation(int id, Reservation reservation) {
        if(isvalidReservation(id)) {
            reservations.put(id, reservation);
        } else {
            new RestaurantServiceException(ErrorCode.RESERVATION_DOES_NOT_EXIST,
                    ErrorUtil.errorCodeHashMap.get(ErrorCode.RESERVATION_DOES_NOT_EXIST));
        }
    }

    private boolean isvalidReservation(int id) {
        return reservations.keySet().contains(id);
    }

    public Set<Integer> getAllReservationIds() {
        return reservations.keySet();
    }

    public Reservation getReservation(int id) {
        if(isvalidReservation(id)) {
            return reservations.get(id);
        }

        throw new RestaurantServiceException(ErrorCode.RESERVATION_DOES_NOT_EXIST,
                ErrorUtil.errorCodeHashMap.get(ErrorCode.RESERVATION_DOES_NOT_EXIST));
    }
}
