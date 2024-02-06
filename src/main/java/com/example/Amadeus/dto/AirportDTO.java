package com.example.Amadeus.dto;

public class AirportDTO {
    Integer airportId;
    String airportCity;

    public AirportDTO(Integer airportId, String airportCity) {
        this.airportId = airportId;
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
