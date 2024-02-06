package com.example.Amadeus.repository;

import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f FROM Flight f WHERE f.departureAirport.id = :airportId OR f.arrivalAirport.id = :airportId")
    List<Flight> findFlightsByAirportId(Integer airportId);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM flight f " +
            "INNER JOIN airport departure_airport ON f.departure_airportid = departure_airport.airport_id " +
            "INNER JOIN airport arrival_airport ON f.arrival_airportid = arrival_airport.airport_id " +
            "WHERE (departure_airport.airport_city = :departureCity AND arrival_airport.airport_city = :arrivalCity) AND " +
            "(f.departure_date_time BETWEEN :departureDateStart AND :departureDateEnd) AND " +
            "(f.return_date_time IS NULL)")
    List<Flight> findFlightsBySearchCriteriaOneWay(
            String departureCity,
            String arrivalCity,
            LocalDateTime departureDateStart,
            LocalDateTime departureDateEnd
);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM flight f " +
            "INNER JOIN airport departure_airport ON f.departure_airportid = departure_airport.airport_id " +
            "INNER JOIN airport arrival_airport ON f.arrival_airportid = arrival_airport.airport_id " +
            "WHERE (departure_airport.airport_city = :departureCity AND arrival_airport.airport_city = :arrivalCity) AND " +
            "(f.departure_date_time BETWEEN :departureDateStart AND :departureDateEnd) AND " +
            "(f.return_date_time BETWEEN :returnDateStart AND :returnDateEnd)")
    List<Flight> findFlightsBySearchCriteriaTwoWay(
            String departureCity,
            String arrivalCity,
            LocalDateTime departureDateStart,
            LocalDateTime departureDateEnd,
            LocalDateTime returnDateStart,
            LocalDateTime returnDateEnd
    );}
