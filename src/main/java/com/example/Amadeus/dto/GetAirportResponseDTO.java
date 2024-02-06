package com.example.Amadeus.dto;

import jakarta.persistence.Column;

public class GetAirportResponseDTO {
    private Integer id;

    public GetAirportResponseDTO(Integer id, String message, String city) {
        this.id = id;
        this.message = message;
        this.city = city;
    }

    private String message;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public GetAirportResponseDTO(Integer id, String city) {
        this.id = id;
        this.city = city;
    }

    private String city;
}
