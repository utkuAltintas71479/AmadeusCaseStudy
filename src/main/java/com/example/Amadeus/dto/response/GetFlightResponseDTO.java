package com.example.Amadeus.dto.response;

import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.util.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GetFlightResponseDTO {
    private final String message;
    private Integer flightId;


    private Airport departureAirport;


    private Airport arrivalAirport;


    private LocalDateTime departureDateTime;


    private LocalDateTime returnDateTime;


    private BigDecimal price;

    public GetFlightResponseDTO(Integer flightId, Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime, BigDecimal price) {
        this.flightId = flightId;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.price = price;
        this.message = Constants.FLIGHT_RETRIEVAL_SUCCESS;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
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
