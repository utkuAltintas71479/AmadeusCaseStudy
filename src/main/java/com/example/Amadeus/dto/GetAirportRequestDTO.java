package com.example.Amadeus.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class GetAirportRequestDTO {
    @NotNull(message = "Id should be given")
    @Min(value = 1,message = "Given id should be numerical value above 0")
    private Integer Id;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public GetAirportRequestDTO(Integer id) {
        Id = id;
    }
}
