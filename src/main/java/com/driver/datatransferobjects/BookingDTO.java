package com.driver.datatransferobjects;

public class BookingDTO {
    private Integer passengerId;
    private Integer flightId;

    public BookingDTO() {
    }

    public BookingDTO(Integer flightId, Integer passengerId) {
        this.flightId = flightId;
        this.passengerId = passengerId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }
}
