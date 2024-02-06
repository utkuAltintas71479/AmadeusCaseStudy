package com.example.Amadeus.dto;

import java.util.List;

public class GetAllAirportsResponseDTO {
    public GetAllAirportsResponseDTO(String message, List<AirportDTO> airports) {
        this.message = message;
        this.airports = airports;
    }

    public List<AirportDTO> getAirports() {
        return airports;
    }

    String message;

    public void setAirports(List<AirportDTO> airports) {
        this.airports = airports;
    }

    public GetAllAirportsResponseDTO(List<AirportDTO> airports) {

        this.airports = airports;
    }

    private List<AirportDTO> airports;

}
