package com.example.Amadeus.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateAirportRequestDTO {
    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @NotBlank(message = "City is required")
    private String City;
}
