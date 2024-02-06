package com.example.Amadeus.service;

import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.entity.Flight;
import com.example.Amadeus.repository.AirportRepository;
import com.example.Amadeus.repository.FlightRepository;
import com.example.Amadeus.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
@Service
public class ScheduledJobService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Scheduled(cron = "0 */5 * * * ?")
    public void mockApiCall() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/generate-flights", null, String.class);
        } catch (Exception e) {
            System.out.println("Error during mock API call: " + e.getMessage());
        }
    }
    public void generateMockFlights() {
        List<Flight> generatedFlights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Flight flight = new Flight();
            List<Airport> airports = airportRepository.findAll();
            int randomAirportIndex = new Random().nextInt(airports.size());
            Airport departureAirport = airports.get(randomAirportIndex);
            randomAirportIndex = new Random().nextInt(airports.size());
            Airport arrivalAirport = airports.get(randomAirportIndex);
            LocalDateTime departureDateTime = LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextInt(1, 30));
            LocalDateTime returnDateTime = null;
            if (new Random().nextInt(2) == 0) {
                returnDateTime = departureDateTime.plusDays(ThreadLocalRandom.current().nextInt(1, 7));
            }
            flight.setDepartureDateTime(departureDateTime);
            flight.setReturnDateTime(returnDateTime);
            flight.setDepartureAirport(departureAirport);
            flight.setArrivalAirport(arrivalAirport);
            flight.setPrice(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(Constants.MIN_FLIGHT_PRICE, Constants.MAX_FLIGHT_PRICE)));
            generatedFlights.add(flight);
        }
        flightRepository.saveAll(generatedFlights);
    }








}
