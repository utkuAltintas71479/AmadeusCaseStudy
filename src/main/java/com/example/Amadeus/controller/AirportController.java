package com.example.Amadeus.controller;

import com.example.Amadeus.dto.request.CreateAirportRequestDTO;
import com.example.Amadeus.dto.request.UpdateAirportRequestDTO;
import com.example.Amadeus.dto.response.CreateAirportResponseDTO;
import com.example.Amadeus.dto.response.GetAirportResponseDTO;
import com.example.Amadeus.dto.response.GetAllAirportsResponseDTO;
import com.example.Amadeus.dto.response.UpdateAirportResponseDTO;
import com.example.Amadeus.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AirportController {

    @Autowired
    private AirportService airportService;

    @PostMapping("/airports/create")
    public ResponseEntity<CreateAirportResponseDTO> createAirport(@Validated @RequestBody CreateAirportRequestDTO createAirportRequestDTO) {
        return new ResponseEntity<>(airportService.createAirport(createAirportRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/airports/delete/{airportId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Integer airportId) {
        airportService.deleteAirport(airportId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/airports")
    public ResponseEntity<GetAllAirportsResponseDTO> getAllAirports() {
        return new ResponseEntity<>(airportService.getAllAirports(), HttpStatus.OK);
    }

    @GetMapping("/airports/{airportId}")
    public ResponseEntity<GetAirportResponseDTO> getAirportById(@PathVariable Integer airportId) {
        return new ResponseEntity<>(airportService.getAirport(airportId), HttpStatus.OK);
    }

    @PutMapping("/airports/{airportId}")
    public ResponseEntity<UpdateAirportResponseDTO> updateAirportById(@PathVariable Integer airportId, @Validated @RequestBody UpdateAirportRequestDTO updateAirportRequestDTO) {
        return new ResponseEntity<>(airportService.updateAirport(airportId, updateAirportRequestDTO), HttpStatus.OK);
    }
}
