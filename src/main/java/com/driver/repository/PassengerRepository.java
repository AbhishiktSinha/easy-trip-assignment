package com.driver.repository;

import com.driver.model.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PassengerRepository {

    private static final Logger log = LoggerFactory.getLogger(PassengerRepository.class);
    private final Map<Integer, Passenger> map;
    public PassengerRepository() {
        map = new HashMap<>();
    }


    public boolean add(Passenger passenger) {
        if (!map.containsKey(passenger.getPassengerId())) {
            map.put(passenger.getPassengerId(), passenger);
            log.info("Passenger created: {}", passenger.toString());
            return true;
        }
        else {
            log.warn("Duplicate passenger id: {}", passenger.getPassengerId());
            return false;
        }
    }

    public boolean update(Integer passengerId, Passenger passenger) {
        if (map.containsKey(passengerId)) {
            map.put(passengerId, passenger);
            log.info("Passenger details updated: {}", passenger.toString());
            return true;
        }
        else {
            log.warn("Passenger doesn't exist for id: {}", passenger.getPassengerId());
            return false;
        }
    }

    public Passenger get(Integer passengerId) {
        return map.get(passengerId);
    }
}
