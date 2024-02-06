package com.example.Amadeus.controller;

import com.example.Amadeus.service.ScheduledJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockApiController {
    @Autowired
    private ScheduledJobService scheduledJobService;

    @PostMapping
    @RequestMapping("/generate-flights")
    public ResponseEntity<String> generateMockFlights() {
        try {
            scheduledJobService.generateMockFlights();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
