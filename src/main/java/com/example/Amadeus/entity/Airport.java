package com.example.Amadeus.entity;

import com.example.Amadeus.util.Constants;
import jakarta.persistence.*;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer airportId;

    @Column(nullable = false, length = Constants.MAX_CITY_NAME_LENGTH)
    private String airportCity;

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
