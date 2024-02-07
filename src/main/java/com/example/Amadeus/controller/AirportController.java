package com.example.Amadeus.controller;

import com.example.Amadeus.dto.request.CreateAirportRequestDTO;
import com.example.Amadeus.dto.request.UpdateAirportRequestDTO;
import com.example.Amadeus.dto.response.CreateAirportResponseDTO;
import com.example.Amadeus.dto.response.GetAirportResponseDTO;
import com.example.Amadeus.dto.response.GetAllAirportsResponseDTO;
import com.example.Amadeus.dto.response.UpdateAirportResponseDTO;
import com.example.Amadeus.service.AirportService;
import com.example.Amadeus.util.Constants;
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
public class AirportController {

    @Autowired
    private AirportService airportService;

    @PostMapping("/airports/create")
    @Operation(summary = "Create new airport", description = "Returns the created airport.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Airport created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateAirportResponseDTO.class))),
    })
    public ResponseEntity<CreateAirportResponseDTO> createAirport(@Validated @RequestBody CreateAirportRequestDTO createAirportRequestDTO) {
        return new ResponseEntity<>(airportService.createAirport(createAirportRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/airports/delete/{airportId}")
    @Operation(summary = "Delete airport", description = "Deletes the specified airport and flights with departure or arrival airports set as this airport.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<Void> deleteAirport(@PathVariable Integer airportId) {
        airportService.deleteAirport(airportId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/airports")
    @Operation(summary = "Discover all airports", description = "Explore a comprehensive list of all airports in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Airports retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetAllAirportsResponseDTO.class))),
    })
    public ResponseEntity<GetAllAirportsResponseDTO> getAllAirports() {
        return new ResponseEntity<>(airportService.getAllAirports(), HttpStatus.OK);
    }

    @GetMapping("/airports/{airportId}")
    @Operation(summary = "Get airport details", description = "Retrieves detailed information about the specified airport with the given ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Airport details retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetAirportResponseDTO.class))),
    })
    public ResponseEntity<GetAirportResponseDTO> getAirportById(@PathVariable Integer airportId) {
        return new ResponseEntity<>(airportService.getAirport(airportId), HttpStatus.OK);
    }

    @PutMapping("/airports/{airportId}")
    @Operation(summary = "Update airport details", description = "Modifies specific information about the specified airport, such as name, location, or facilities.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Airport details updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateAirportResponseDTO.class))),
    })
    public ResponseEntity<UpdateAirportResponseDTO> updateAirportById(@PathVariable Integer airportId, @Validated @RequestBody UpdateAirportRequestDTO updateAirportRequestDTO) {
        return new ResponseEntity<>(airportService.updateAirport(airportId, updateAirportRequestDTO), HttpStatus.OK);
    }
}
