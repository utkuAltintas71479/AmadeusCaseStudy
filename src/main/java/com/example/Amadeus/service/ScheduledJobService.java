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

    @Scheduled(cron = "0 */1 * * * ?")
    public void mockApiCall() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/generate-flights", null, String.class);
        } catch (Exception e) {
            System.out.println(Constants.MOCK_API_ERROR);
        }
    }
    public void generateMockFlights() {
        List<Flight> generatedFlightEntities = new ArrayList<>();
        for (int i = 0; i < Constants.NUMBER_OF_MOCK_FLIGHTS; i++) {
            Flight flight = new Flight();
            List<Airport> airportEntities = airportRepository.findAll();
            int randomAirportIndexDeparture=0;
            int randomAirportIndexArrival=0;
            while (randomAirportIndexDeparture==randomAirportIndexArrival){
                randomAirportIndexDeparture = new Random().nextInt(airportEntities.size());
                randomAirportIndexArrival = new Random().nextInt(airportEntities.size());
            }
            Airport departureAirport = airportEntities.get(randomAirportIndexDeparture);
            Airport arrivalAirport = airportEntities.get(randomAirportIndexArrival);
            LocalDateTime departureDateTime = LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextInt(1, Constants.MAX_DAYS_TO_FLIGHT));
            LocalDateTime returnDateTime = null;
            if (new Random().nextInt(2) == 0) {
                returnDateTime = departureDateTime.plusDays(ThreadLocalRandom.current().nextInt(1, Constants.MAX_DAYS_UNTIL_RETURN));
            }
            flight.setDepartureDateTime(departureDateTime);
            flight.setReturnDateTime(returnDateTime);
            flight.setDepartureAirport(departureAirport);
            flight.setArrivalAirport(arrivalAirport);
            flight.setPrice(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(Constants.MIN_FLIGHT_PRICE, Constants.MAX_FLIGHT_PRICE)));
            generatedFlightEntities.add(flight);
        }
        flightRepository.saveAll(generatedFlightEntities);
    }








}
