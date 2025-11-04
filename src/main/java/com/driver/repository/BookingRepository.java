package com.driver.repository;

import com.driver.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookingRepository {

    private static final Logger log = LoggerFactory.getLogger(BookingRepository.class);
    private final Map<String, Booking> map;
    public BookingRepository() {
        map = new HashMap<>();
    }

    public boolean create(Booking booking) {
        if (map.containsKey(booking.getBookingId())) {
            log.warn("Duplicate booking id: {}", booking.getPassengerId());
            return false;
        }
        map.put(booking.getBookingId(), booking);
        log.info("Booking created: {}", booking.toString());
        return true;
    }

    public boolean update(String bookingId, Booking booking) {
        if (!map.containsKey(bookingId)) {
            log.warn("Booking does not exist for id: {}", bookingId);
            return false;
        }
        map.put(bookingId, booking);
        log.info("Booking updated: {}", booking.toString());
        return true;
    }

    public void delete(String bookingId) {
            map.remove(bookingId);
    }

    public Booking get(String bookingId) {
        return map.get(bookingId);
    }

    public List<Booking> list() {
        return new ArrayList<>(map.values());
    }
}
