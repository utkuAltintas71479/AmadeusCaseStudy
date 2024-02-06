package com.example.Amadeus.dto;

public class UpdateAirportResponseDTO {
    private Integer id;
    private String message;
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UpdateAirportResponseDTO(Integer id, String message, String city) {
        this.id = id;
        this.message = message;
        this.city = city;
    }
}
