package com.example.Amadeus.dto.request;

import com.example.Amadeus.util.Constants;
import jakarta.validation.constraints.NotNull;

public class DeleteAirportRequestDTO {
    @NotNull(message = Constants.ID_REQUIRED)
    private Integer airportId;

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }
}
