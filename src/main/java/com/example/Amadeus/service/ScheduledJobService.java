package com.example.Amadeus.service;

import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.entity.Flight;
import com.example.Amadeus.repository.AirportRepository;
import com.example.Amadeus.repository.FlightRepository;
import com.example.Amadeus.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
@Service
public class ScheduledJobService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;



    @Scheduled(cron = "0 * * * * ?")
    @Transactional
    public void updateFlightInformation() {
        List<Flight> flights = generateMockFlightData();
        for (Flight flight : flights) {
            Airport departureAirport = findOrCreateAirport(flight.getDepartureAirport());
            Airport arrivalAirport = findOrCreateAirport(flight.getArrivalAirport());
            flight.setDepartureAirport(departureAirport);
            flight.setArrivalAirport(arrivalAirport);
            flightRepository.save(flight);
        }
    }

    private Airport findOrCreateAirport(Airport airport) {
        Airport existingAirport = airportRepository.findById(airport.getId()).orElse(null);
        if (existingAirport != null) {
            return existingAirport;
        } else {
            return airportRepository.save(airport);
        }
    }

    public List<Flight> generateMockFlightData() {
        List<Flight> mockFlights = new ArrayList<>();
        int numberOfFlightsToGenerate = 10;
        for (int i = 0; i < numberOfFlightsToGenerate; i++) {
            Flight flight = new Flight();
            LocalDateTime[] departureAndReturnDates = generateRandomDepartureAndReturnDateTimes();
            Airport[] airports = generateMockAirports();
            flight.setDepartureAirport(airports[0]);
            flight.setArrivalAirport(airports[1]);
            flight.setDepartureDateTime(departureAndReturnDates[0]);
            flight.setReturnDateTime(departureAndReturnDates[1]);
            flight.setPrice(generateRandomPrice());
            mockFlights.add(flight);
        }
        return mockFlights;
    }

    private Airport[] generateMockAirports() {
        Airport departureAirport = new Airport();
        int departureAirportId = ThreadLocalRandom.current().nextInt(1,Constants.AIRPORT_CITIES.length+1);
        departureAirport.setId(departureAirportId);
        departureAirport.setCity(Constants.AIRPORT_CITIES[departureAirportId-1]);
        Airport arrivalAirport = new Airport();
        int arrivalAirportId = ThreadLocalRandom.current().nextInt(1,Constants.AIRPORT_CITIES.length+1);
        while(arrivalAirportId == departureAirportId) {
            arrivalAirportId = ThreadLocalRandom.current().nextInt(1,Constants.AIRPORT_CITIES.length);
        }
        arrivalAirport.setId(arrivalAirportId);
        arrivalAirport.setCity(Constants.AIRPORT_CITIES[arrivalAirportId-1]);
        return new Airport[]{departureAirport, arrivalAirport};
    }

    private LocalDateTime[] generateRandomDepartureAndReturnDateTimes() {
        LocalDateTime departureDateTime = LocalDateTime.now()
                .plusDays(ThreadLocalRandom.current().nextInt(1, 366))
                .withHour(ThreadLocalRandom.current().nextInt(0, 24))
                .truncatedTo(ChronoUnit.HOURS);
        LocalDateTime returnDateTime = null;
        if (ThreadLocalRandom.current().nextDouble() >= 0.5) {
            returnDateTime = departureDateTime
                    .plusDays(ThreadLocalRandom.current().nextInt(1, 30))
                    .withHour(ThreadLocalRandom.current().nextInt(0, 24))
                    .truncatedTo(ChronoUnit.HOURS);
        }
        return new LocalDateTime[]{departureDateTime, returnDateTime};
    }

    private BigDecimal generateRandomPrice() {
        double randomPrice = ThreadLocalRandom.current().nextDouble(50.0, 1000.0);
        return BigDecimal.valueOf(randomPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
    }



}
