package com.example.Amadeus.dto;

public class AirportDTO {
    Integer id;
    String city;

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

    public AirportDTO(Integer id, String city) {
        this.id = id;
        this.city = city;
    }
}
