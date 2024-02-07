package com.example.Amadeus.dto.request;

import com.example.Amadeus.util.Constants;
import jakarta.validation.constraints.NotBlank;

public class CreateAirportRequestDTO {
    @NotBlank(message = Constants.CITY_REQUIRED)
    private String airportCity;

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }
}
