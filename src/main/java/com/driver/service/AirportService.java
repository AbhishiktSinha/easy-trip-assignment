package com.driver.service;

import com.driver.model.Airport;
import com.driver.repository.AirportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    private static final Logger log = LoggerFactory.getLogger(AirportService.class);
    private AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public boolean createAirport(Airport airport) {

        if (airportRepository.get(airport.getAirportName()) != null) {
            log.warn("Airport with name {} already exists", airport.getAirportName());
            return false;
        }

        airportRepository.add(airport);
        return true;
    }

}
