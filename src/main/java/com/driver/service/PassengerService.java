package com.driver.service;


import com.driver.model.Passenger;
import com.driver.repository.PassengerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    private static final Logger log = LoggerFactory.getLogger(PassengerService.class);
    @Autowired
    private PassengerRepository passengerRepository;


    public boolean add(Passenger passenger) {

        if (passengerRepository.get(passenger.getPassengerId()) != null) {
            log.warn("Passenger record already exists for id: {}", passenger.getPassengerId());
            return false;
        }

        passengerRepository.add(passenger);
        return true;
    }
}
