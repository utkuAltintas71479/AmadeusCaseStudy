package com.example.Amadeus.repository;

import com.example.Amadeus.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

}
