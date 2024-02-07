package com.example.Amadeus.dto.response;

import com.example.Amadeus.dto.AirportDTO;
import com.example.Amadeus.util.Constants;

import java.util.List;

public class GetAllAirportsResponseDTO {
    String message;
    private List<AirportDTO> airports;

    public GetAllAirportsResponseDTO(List<AirportDTO> airports) {
        this.message = Constants.AIRPORT_RETRIEVAL_SUCCESS;
        this.airports = airports;
    }

    public List<AirportDTO> getAirports() {
        return airports;
    }

    public void setAirports(List<AirportDTO> airports) {
        this.airports = airports;
    }

}
