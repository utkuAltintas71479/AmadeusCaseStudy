package com.example.Amadeus.service;

import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.entity.Flight;
import com.example.Amadeus.repository.AirportRepository;
import com.example.Amadeus.repository.FlightRepository;
import com.example.Amadeus.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduledJobServiceTest {
    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private ScheduledJobService scheduledJobService;


    @Test
    public void testGenerateRandomPriceRange() {
        BigDecimal price = scheduledJobService.generateRandomPrice();
        assertTrue(price.compareTo(BigDecimal.valueOf(50.0)) >= 0 &&
                        price.compareTo(BigDecimal.valueOf(1000.0)) <= 0);
    }

    @Test
    public void testGeneratesDepartureDateTime() {
        LocalDateTime[] dateTimes = scheduledJobService.generateRandomDepartureAndReturnDateTimes();
        LocalDateTime departureDateTime = dateTimes[0];
        assertTrue(departureDateTime.isAfter(LocalDateTime.now()));
        if (dateTimes[1] != null) {
            assertTrue(dateTimes[1].isAfter(dateTimes[0]));
        }
    }

    @Test
    public void testGenerateMockAirport() {
        Airport[] airports = scheduledJobService.generateMockAirports();
        String departureCity = airports[0].getCity();
        String arrivalCity = airports[1].getCity();
        assertNotEquals(airports[0].getId(), airports[1].getId());
        assertTrue(airports[0].getId() >= 1 && airports[0].getId() <= Constants.AIRPORT_CITIES.length);
        assertTrue(airports[1].getId() >= 1 && airports[1].getId() <= Constants.AIRPORT_CITIES.length);
        assertTrue(Arrays.asList(Constants.AIRPORT_CITIES).contains(departureCity));
        assertTrue(Arrays.asList(Constants.AIRPORT_CITIES).contains(arrivalCity));
    }

    @Test
    public void testGenerateMockFlights() {
        List<Flight> flights = scheduledJobService.generateMockFlightData();
        assertEquals(10, flights.size());
        for (Flight flight : flights) {
            assertNotNull(flight.getDepartureAirport());
            assertNotNull(flight.getArrivalAirport());
            assertNotNull(flight.getDepartureDateTime());
            assertNotNull(flight.getPrice());
        }
    }

    @Test
    public void testFindOrCreateAirportExistingAirport() {
        Airport existingAirport = new Airport();
        existingAirport.setId(1);
        existingAirport.setCity("TEST");
        when(airportRepository.findById(1)).thenReturn(Optional.of(existingAirport));
        Airport foundAirport = scheduledJobService.findOrCreateAirport(existingAirport);
        assertSame(existingAirport, foundAirport);
        verify(airportRepository, times(1)).findById(1);
        verify(airportRepository, never()).save(any());
    }

    @Test
    public void testFindOrCreateAirportCreatesNewAirport() {
        Airport newAirport = new Airport();
        newAirport.setId(1);
        when(airportRepository.save(newAirport)).thenReturn(newAirport);
        when(airportRepository.findById(1)).thenReturn(Optional.empty());
        Airport savedAirport = scheduledJobService.findOrCreateAirport(newAirport);
        assertEquals(newAirport, savedAirport);
        verify(airportRepository, times(1)).findById(1);
        verify(airportRepository, times(1)).save(newAirport);
    }

    @Test
    public void testUpdatesFlights() {
        List<Flight> expectedFlights = scheduledJobService.generateMockFlightData();
        scheduledJobService.updateFlightInformation();
        verify(flightRepository, times(expectedFlights.size())).save(any());
    }


}
