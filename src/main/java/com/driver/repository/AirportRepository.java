package com.driver.repository;

import com.driver.model.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AirportRepository {

    private static final Logger log = LoggerFactory.getLogger(AirportRepository.class);
    private final Map<String, Airport> map;

    public AirportRepository() {
        map = new HashMap<>();
    }

    public boolean add(Airport airport) {
        if (!map.containsKey(airport.getAirportName())) {
            map.put(airport.getAirportName(), airport);
            log.info("Airport created: {}", airport.toString());
            return true;
        }
        else {
            log.warn("Duplicate airport name for: {}", airport.getAirportName());
            return false;
        }
    }

    public boolean update(String airportName, Airport airport) {

        if (map.containsKey(airportName)) {
            map.put(airportName, airport);
            log.info("Airport updated: {}", airport.toString());
            return true;
        }
        else {
            log.warn("Airport doesn't exist with name: {}", airport.getAirportName());
            return false;
        }
    }

    public Airport get(String airportName) {
        return map.get(airportName);
    }
}
