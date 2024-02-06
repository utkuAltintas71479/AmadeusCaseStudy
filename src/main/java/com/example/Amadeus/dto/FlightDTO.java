package com.example.Amadeus.dto;

import com.example.Amadeus.entity.Airport;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlightDTO {


    private Integer flightId;


    private AirportDTO departureAirport;


    private AirportDTO arrivalAirport;


    private LocalDateTime departureDateTime;


    private LocalDateTime returnDateTime;


    private BigDecimal price;


    public FlightDTO(Integer flightId, AirportDTO departureAirport, AirportDTO arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime, BigDecimal price) {
        this.flightId = flightId;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.price = price;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public AirportDTO getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportDTO departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportDTO getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(AirportDTO arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
