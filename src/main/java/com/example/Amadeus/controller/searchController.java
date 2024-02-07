package com.example.Amadeus.controller;

import com.example.Amadeus.dto.request.SearchFlightRequestDTO;
import com.example.Amadeus.dto.response.SearchedFlightResponseDTO;
import com.example.Amadeus.dto.response.UpdateFlightResponseDTO;
import com.example.Amadeus.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class searchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/flights/search")
    @Operation(summary = "Find your perfect flight", description = "Search for flights that match your travel needs and preferences using the provided details. Considers the flights that are in the same day with the provided date information in the request body.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Matching flights found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SearchedFlightResponseDTO.class))),
    })
    public ResponseEntity<SearchedFlightResponseDTO> searchFlight(@Validated @RequestBody SearchFlightRequestDTO searchFlightRequestDTO) {
        return new ResponseEntity<>(searchService.searchFlights(searchFlightRequestDTO), HttpStatus.OK);
    }
}
