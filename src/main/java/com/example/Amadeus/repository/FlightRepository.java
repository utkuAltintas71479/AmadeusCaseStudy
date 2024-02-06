package com.example.Amadeus.repository;

import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f FROM Flight f WHERE f.departureAirport.id = :airportId OR f.arrivalAirport.id = :airportId")
    List<Flight> findFlightsByAirportId(Integer airportId);

}
