package com.example.Amadeus.dto;

import java.util.List;

public class GetAllAirportsResponseDTO {
    String message;
    private List<AirportDTO> airports;

    public GetAllAirportsResponseDTO(List<AirportDTO> airports) {
        this.message = "Airports retrieved successfully";
        this.airports = airports;
    }

    public List<AirportDTO> getAirports() {
        return airports;
    }

    public void setAirports(List<AirportDTO> airports) {
        this.airports = airports;
    }

}
