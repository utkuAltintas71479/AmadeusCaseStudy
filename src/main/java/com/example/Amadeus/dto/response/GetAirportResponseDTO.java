package com.example.Amadeus.dto.response;

import com.example.Amadeus.util.Constants;

public class GetAirportResponseDTO {
    private final String message;
    private Integer airportId;
    private String airportCity;


    public GetAirportResponseDTO(Integer airportId, String airportCity) {
        this.airportId = airportId;
        this.message = Constants.AIRPORT_RETRIEVAL_SUCCESS;
        this.airportCity = airportCity;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }
}
