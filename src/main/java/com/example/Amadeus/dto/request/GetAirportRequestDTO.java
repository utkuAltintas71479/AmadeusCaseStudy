package com.example.Amadeus.dto.request;

import com.example.Amadeus.util.Constants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class GetAirportRequestDTO {
    @NotNull(message = Constants.ID_REQUIRED)
    @Min(value = Constants.ID_LOWER_LIMIT, message = Constants.ID_LOWER_LIMIT_FAILED)
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
