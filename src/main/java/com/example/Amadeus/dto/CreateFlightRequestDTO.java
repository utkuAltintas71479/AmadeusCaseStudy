package com.example.Amadeus.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateFlightRequestDTO {


    @NotNull(message = "Departure Airport must be specified")
    private Integer departureAirportId;

    @NotNull(message = "Arrival Airport must be specified")
    private Integer arrivalAirportId;

    @Future(message = "Departure date must be in the future")
    private LocalDateTime departureDateTime;

    @Future(message = "Return date must be in the future")
    private LocalDateTime returnDateTime;

    @NotNull(message = "Price must be specified")
    private BigDecimal price;

    public Integer getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Integer departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Integer getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(Integer arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
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
