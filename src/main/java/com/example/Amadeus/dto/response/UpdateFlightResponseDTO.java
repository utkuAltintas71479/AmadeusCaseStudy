package com.example.Amadeus.dto.response;

import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.util.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateFlightResponseDTO {
    private String message;
    private Airport departureAirport;


    private Airport arrivalAirport;


    private LocalDateTime departureDateTime;


    private LocalDateTime returnDateTime;


    private BigDecimal price;


    public UpdateFlightResponseDTO(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime, BigDecimal price) {
        this.message = Constants.FLIGHT_UPDATE_SUCCESS;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.price = price;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
