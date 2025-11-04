package com.driver.repository;

import com.driver.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FlightRepository {

    private static final Logger log = LoggerFactory.getLogger(FlightRepository.class);
    private final Map<Integer, Flight> map;

    public FlightRepository() {
        map = new HashMap<>();
    }

    public boolean add(Flight flight) {

        if (map.containsKey(flight.getFlightId())) {
            log.warn("Duplicate flight id: {}, for flight: {}", flight.getFlightId(), flight.toString());
            return false;
        }

        map.put(flight.getFlightId(), flight);
        log.info("Flight created: {}", flight.toString());
        return true;
    }

    public boolean update(int flightId, Flight flight) {
        if (map.containsKey(flightId)) {
            map.put(flightId, flight);
            log.info("Flight updated with id: {}", flightId);
            return true;
        }
        else {
            log.warn("Flight not found with id: {}", flightId);
            return false;
        }
    }

    public Flight get(Integer id) {
        return map.get(id);
    }

    public List<Flight> list() {
        return new ArrayList<>(map.values());
    }
}
