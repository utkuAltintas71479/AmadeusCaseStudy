package com.example.Amadeus.dto.response;

import com.example.Amadeus.util.Constants;

public class CreateAirportResponseDTO {
    private String message;
    private String airportCity;
    private Integer airportId;

    public CreateAirportResponseDTO(String airportCity, Integer airportId) {
        this.message = Constants.AIRPORT_CREATION_SUCCESS;
        this.airportCity = airportCity;
        this.airportId = airportId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
