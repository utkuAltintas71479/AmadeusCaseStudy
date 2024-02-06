package com.example.Amadeus.dto;

public class GetAirportResponseDTO {
    private final String message;
    private Integer airportId;
    private String airportCity;


    public GetAirportResponseDTO(Integer airportId, String airportCity) {
        this.airportId = airportId;
        this.message = "Airport retrieved Successfully";
        this.airportCity = airportCity;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }
}
