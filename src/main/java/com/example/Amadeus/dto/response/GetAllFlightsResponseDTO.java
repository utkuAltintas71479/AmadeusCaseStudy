package com.example.Amadeus.dto.response;

import com.example.Amadeus.dto.FlightDTO;
import com.example.Amadeus.util.Constants;

import java.util.List;

public class GetAllFlightsResponseDTO {
    String message;
    private List<FlightDTO> flights;

    public GetAllFlightsResponseDTO(List<FlightDTO> flights) {
        this.message = Constants.FLIGHT_RETRIEVAL_SUCCESS;
        this.flights = flights;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }


}
