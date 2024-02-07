package com.example.Amadeus.dto.response;

import com.example.Amadeus.dto.SearchedFlightDTO;
import com.example.Amadeus.util.Constants;

import java.util.List;

public class SearchedFlightResponseDTO {String message;
    private List<SearchedFlightDTO> flights;

    public SearchedFlightResponseDTO(List<SearchedFlightDTO> flights) {
        this.message = Constants.FLIGHT_RETRIEVAL_SUCCESS;
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
