package com.example.Amadeus.dto;

public class CreateAirportResponseDTO {
    private String City;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CreateAirportResponseDTO(String city, Integer airportId, String message) {
        City = city;
        this.airportId = airportId;
        this.message = message;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    private Integer airportId;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }


}
