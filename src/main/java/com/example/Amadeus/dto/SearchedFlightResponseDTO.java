package com.example.Amadeus.dto;

import java.util.List;

public class SearchedFlightResponseDTO {String message;
    private List<SearchedFlightDTO> flights;

    public SearchedFlightResponseDTO(List<SearchedFlightDTO> flights) {
        this.message = "Flights retrieved successfully";
        this.flights = flights;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SearchedFlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<SearchedFlightDTO> flights) {
        this.flights = flights;
    }

}
