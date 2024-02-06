package com.example.Amadeus.dto;

public class UpdateAirportResponseDTO {
    private String message;
    private Integer airportId;

    private String airportCity;

    public UpdateAirportResponseDTO(Integer airportId, String airportCity) {
        this.airportId = airportId;
        this.message = "AirportUpdated successfully";
        this.airportCity = airportCity;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }
}
