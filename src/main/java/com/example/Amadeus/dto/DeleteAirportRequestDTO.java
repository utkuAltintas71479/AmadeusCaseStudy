package com.example.Amadeus.dto;

import jakarta.validation.constraints.NotNull;

public class DeleteAirportRequestDTO {
    @NotNull(message = "id is required")
    private Integer airportId;

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }
}
