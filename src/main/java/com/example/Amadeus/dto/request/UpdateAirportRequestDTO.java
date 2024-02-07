package com.example.Amadeus.dto.request;

import com.example.Amadeus.util.Constants;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public class UpdateAirportRequestDTO {
    @NotNull(message = Constants.CITY_REQUIRED)
    private String airportCity;

    @Nullable
    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(@Nullable String airportCity) {
        this.airportCity = airportCity;
    }
}
