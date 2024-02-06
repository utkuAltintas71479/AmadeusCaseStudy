package com.example.Amadeus.controller;

import com.example.Amadeus.dto.*;
import com.example.Amadeus.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;


    @PostMapping("/flights/create")
    public ResponseEntity<CreateFlightResponseDTO> createFlight(@Validated @RequestBody CreateFlightRequestDTO createFlightRequestDTO){
        return new ResponseEntity<>(flightService.createFlight(createFlightRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/flights/delete/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Integer flightId){
        flightService.deleteFlight(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/flights")
    public ResponseEntity<GetAllFlightsResponseDTO> getAllFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<GetFlightResponseDTO> getFlightById(@PathVariable Integer flightId){
        return new ResponseEntity<>(flightService.getFlight(flightId), HttpStatus.OK);
    }


    @PutMapping("/flights/{flightId}")
    public ResponseEntity<UpdateFlightResponseDTO> updateFlight(@PathVariable Integer flightId,@Validated @RequestBody UpdateFlightRequestDTO updateFlightRequestDTO){
        return new ResponseEntity<>(flightService.updateFlight(flightId,updateFlightRequestDTO), HttpStatus.OK);
    }

}
