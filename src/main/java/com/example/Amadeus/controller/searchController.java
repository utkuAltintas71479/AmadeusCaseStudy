package com.example.Amadeus.controller;

import com.example.Amadeus.dto.*;
import com.example.Amadeus.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class searchController {
    @Autowired
    private SearchService searchService;
    @GetMapping("/flights/search")
    public ResponseEntity<SearchedFlightResponseDTO> getFlightById(@Validated @RequestBody SearchFlightRequestDTO searchFlightRequestDTO){
        return new ResponseEntity<>(searchService.searchFlights(searchFlightRequestDTO), HttpStatus.OK);
    }
}
