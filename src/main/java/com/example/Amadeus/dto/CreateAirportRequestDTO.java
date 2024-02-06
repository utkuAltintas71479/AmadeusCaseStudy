package com.example.Amadeus.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateAirportRequestDTO {
    @NotBlank(message = "City is required")
    private String airportCity;

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }
}
