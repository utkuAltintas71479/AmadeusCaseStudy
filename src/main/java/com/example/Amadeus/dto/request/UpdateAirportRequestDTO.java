package com.example.Amadeus.dto.request;

import jakarta.annotation.Nullable;

public class UpdateAirportRequestDTO {
    private String airportCity;

    @Nullable
    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(@Nullable String airportCity) {
        this.airportCity = airportCity;
    }
}
