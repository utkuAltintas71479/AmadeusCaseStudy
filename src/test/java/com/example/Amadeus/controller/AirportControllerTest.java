package com.example.Amadeus.controller;

import com.example.Amadeus.dto.AirportDTO;
import com.example.Amadeus.dto.request.CreateAirportRequestDTO;
import com.example.Amadeus.dto.request.UpdateAirportRequestDTO;
import com.example.Amadeus.dto.response.CreateAirportResponseDTO;
import com.example.Amadeus.dto.response.GetAirportResponseDTO;
import com.example.Amadeus.dto.response.GetAllAirportsResponseDTO;
import com.example.Amadeus.dto.response.UpdateAirportResponseDTO;
import com.example.Amadeus.service.AirportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AirportControllerTest {
    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAirport() {
        CreateAirportRequestDTO requestDTO = new CreateAirportRequestDTO();
        CreateAirportResponseDTO responseDTO = new CreateAirportResponseDTO("Test",1);
        when(airportService.createAirport(requestDTO)).thenReturn(responseDTO);
        ResponseEntity<CreateAirportResponseDTO> response = airportController.createAirport(requestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(airportService, times(1)).createAirport(requestDTO);
    }

    @Test
    void testDeleteAirport() {
        Integer airportId = 1;
        ResponseEntity<Void> response = airportController.deleteAirport(airportId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(airportService, times(1)).deleteAirport(airportId);
    }
    @Test
    void testGetAllAirports() {
        AirportDTO airportDTO=new AirportDTO(1,"Test");
        List<AirportDTO> airports = new ArrayList<>();
        airports.add(airportDTO);
        GetAllAirportsResponseDTO responseDTO = new GetAllAirportsResponseDTO(airports);
        when(airportService.getAllAirports()).thenReturn(responseDTO);
        ResponseEntity<GetAllAirportsResponseDTO> response = airportController.getAllAirports();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(airportService, times(1)).getAllAirports();
    }

    @Test
    void testGetAirportById() {
        Integer airportId = 1;
        GetAirportResponseDTO responseDTO = new GetAirportResponseDTO(1,"Test");
        when(airportService.getAirport(airportId)).thenReturn(responseDTO);
        ResponseEntity<GetAirportResponseDTO> response = airportController.getAirportById(airportId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(airportService, times(1)).getAirport(airportId);
    }

    @Test
    void testUpdateAirportById() {
        Integer airportId = 1;
        UpdateAirportRequestDTO requestDTO = new UpdateAirportRequestDTO();
        requestDTO.setAirportCity("test");
        UpdateAirportResponseDTO responseDTO = new UpdateAirportResponseDTO(1,"test");
        when(airportService.updateAirport(airportId, requestDTO)).thenReturn(responseDTO);
        ResponseEntity<UpdateAirportResponseDTO> response = airportController.updateAirportById(airportId, requestDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(airportService, times(1)).updateAirport(airportId, requestDTO);
    }
}
