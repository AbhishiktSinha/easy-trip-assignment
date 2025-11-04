package com.driver.service;

import com.driver.datatransferobjects.BookingDTO;
import com.driver.model.Booking;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.repository.BookingRepository;
import com.driver.repository.FlightRepository;
import com.driver.repository.PassengerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);
    private BookingRepository bookingRepository;
    private FlightRepository flightRepository;
    private PassengerRepository passengerRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository, PassengerRepository passengerRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    public boolean createBooking(Integer flightId, Integer passengerId) {

        Flight flight = flightRepository.get(flightId);
        Passenger passenger = passengerRepository.get(passengerId);

        // check if flight exists
        if (flight == null) {
            log.warn("Could not create booking :: Invalid Flight Id");
            return false;
        }
        // check if passenger exists
        if (passenger == null) {
            log.warn("Could not create booking :: Invalid Passenger Id");
            return false;
        }

        // if flight date has `passed
        Date today = new Date();
        if (flight.getFlightDate().before(today)) {
            log.warn("Booking aborted: flight date: {} is less than booking date: {}", flight.getFlightDate().toString(), today.toString());
            return false;
        }

        // get all passengers already booked on the flight
        List<Booking> bookingList = getBookings(flightId, null);

        // check if flight and passenger booking pair already exists
        for (Booking booking : bookingList) {
            if (booking.getFlightId() == flightId &&
                    booking.getPassengerId() == passengerId) {
                log.warn("Booking already exists for passengerId: {} & flightId: {}", passengerId, flightId);
                return false;
            }
        }

        // check for remaining seats
        Integer flightCapacity = flight.getMaxCapacity();

        if (bookingList.size() < flightCapacity) {
            // create booking object
            Booking booking = new Booking(
                    UUID.randomUUID().toString(),
                    flightId,
                    passengerId,
                    new Date()  );

            bookingRepository.create(booking);
            return true;
        }
        else {
            log.warn("Booking aborted --- REASON: Flight Capacity Reached");
            return false;
        }

    }

    public boolean cancelBooking(Integer passengerId, Integer flightId) {

        Flight flight = flightRepository.get(flightId);
        Passenger passenger = passengerRepository.get(passengerId);

        if (passenger == null || flight == null) {
            log.warn("Booking could not be cancelled :: Invalid PassengerId or FlightId");
            return false;
        }

        // get booking by passenger for flight
        List<Booking> bookingList = getBookings(flightId, passengerId);

        if (bookingList.size() == 1) {
            bookingRepository.delete(bookingList.get(0).getBookingId());
            log.info("CANCELLED BOOKING for passengerId: {}, flightId: {}", passengerId, flightId);
            return true;
        }
        else{
            log.warn("Booking does not exist");
            return false;
        }
    }

    public List<Booking> getPassengerBookings(Integer passengerId) {

        return getBookings(null, passengerId);
    }

    public Booking getBooking(String bookingId) {
        return bookingRepository.get(bookingId);
    }




    /* ------------------------- AUXILIARY METHODS -------------------- */
    private List<Booking> getBookings(Integer flightId, Integer passengerId) {

        List<Booking> allBookings = bookingRepository.list();
        if (flightId == null && passengerId == null)
            return allBookings;

        List<Booking> bookingList= new ArrayList<>();
        for (Booking booking: allBookings) {

            boolean isValid = true;
            if (flightId != null) {

                if (booking.getFlightId() != flightId) isValid = false;
            }
            if (passengerId != null) {
                if (booking.getPassengerId() != passengerId) isValid = false;
            }

            if (isValid) bookingList.add(booking);
        }

        return bookingList;

    }
}
