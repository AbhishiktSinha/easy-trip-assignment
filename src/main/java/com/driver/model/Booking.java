package com.driver.model;

import com.driver.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Booking {

    private String bookingId;
    private int flightId;
    private int passengerId;
    private Date bookingDate;
    // private BookingStatus bookingStatus;

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Booking() {
    }

    public Booking(String bookingId, int flightId, int passengerId, Date bookingDate) {
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.bookingDate = bookingDate;
        // this.bookingStatus = bookingStatus;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", flightId=" + flightId +
                ", passengerId=" + passengerId +
                '}';
    }
}
