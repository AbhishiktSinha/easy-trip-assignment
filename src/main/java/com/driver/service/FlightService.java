package com.driver.service;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.repository.AirportRepository;
import com.driver.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlightService {

    private FlightRepository flightRepository;
    private AirportRepository airportRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void createFlight(Flight flight) {
        flightRepository.add(flight);
    }

    public List<Flight> getFlights(City fromCity, City toCity, Date date) {

        List<Flight> flightList = new ArrayList<>();

        for (Flight flight : flightRepository.list()) {

            boolean isValid = true;
            if (fromCity != null && flight.getFromCity() != fromCity) isValid = false;
            if (toCity != null && flight.getToCity() != toCity) isValid = false;
            if (date != null && date.compareTo(flight.getFlightDate()) != 0) isValid = false;


            if (isValid) flightList.add(flight);
        }

        return flightList;

    }
}
