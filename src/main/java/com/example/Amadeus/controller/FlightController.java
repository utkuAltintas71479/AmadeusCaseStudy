package com.example.Amadeus.controller;

import com.example.Amadeus.dto.request.CreateFlightRequestDTO;
import com.example.Amadeus.dto.request.UpdateFlightRequestDTO;
import com.example.Amadeus.dto.response.*;
import com.example.Amadeus.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Schedule a new flight", description = "Creates a new flight with the details provided in the request body.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Flight scheduled successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateFlightResponseDTO.class))),
    })
    public ResponseEntity<CreateFlightResponseDTO> createFlight(@Validated @RequestBody CreateFlightRequestDTO createFlightRequestDTO) {
        return new ResponseEntity<>(flightService.createFlight(createFlightRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/flights/delete/{flightId}")
    @Operation(summary = "Delete a flight (caution: irreversible)", description = "Permanently removes the specified flight and potentially affects associated bookings.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Flight deleted"),
    })
    public ResponseEntity<Void> deleteFlight(@PathVariable Integer flightId) {
        flightService.deleteFlight(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/flights")
    @Operation(summary = "Explore all flights", description = "Retrieve a comprehensive list of all flights currently available in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Flights retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetAllFlightsResponseDTO.class))),
    })
    public ResponseEntity<GetAllFlightsResponseDTO> getAllFlights() {
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @GetMapping("/flights/{flightId}")
    @Operation(summary = "Get detailed flight information", description = "Retrieves detailed information about the specified flight, including schedule, passengers, and booking details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Flight details retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetFlightResponseDTO.class))),
    })
    public ResponseEntity<GetFlightResponseDTO> getFlightById(@PathVariable Integer flightId) {
        return new ResponseEntity<>(flightService.getFlight(flightId), HttpStatus.OK);
    }


    @PutMapping("/flights/{flightId}")
    @Operation(summary = "Update flight details", description = "Modifies specific information about the specified flight, such as schedule, passengers, or booking details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Flight details updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateFlightResponseDTO.class))),
    })
    public ResponseEntity<UpdateFlightResponseDTO> updateFlight(@PathVariable Integer flightId, @Validated @RequestBody UpdateFlightRequestDTO updateFlightRequestDTO) {
        return new ResponseEntity<>(flightService.updateFlight(flightId, updateFlightRequestDTO), HttpStatus.OK);
    }

}
