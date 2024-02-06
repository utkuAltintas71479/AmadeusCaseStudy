package com.example.Amadeus.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class UpdateAirportRequestDTO {
    @NotBlank(message = "A city name should be provided for update")
    private String city;

    @Nullable
    public String getCity() {
        return city;
    }

    public void setCity(@Nullable String city) {
        this.city = city;
    }
}
