package com.example.Amadeus.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class GetAirportRequestDTO {
    @NotNull(message = "Id should be given")
    @Min(value = 1, message = "Given id should be numerical value above 0")
    private Integer airportId;

    public GetAirportRequestDTO(Integer airportId) {
        this.airportId = airportId;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }
}
