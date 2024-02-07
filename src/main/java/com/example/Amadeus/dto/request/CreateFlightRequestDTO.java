package com.example.Amadeus.dto.request;

import com.example.Amadeus.util.Constants;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateFlightRequestDTO {


    @NotNull(message = Constants.DEPARTURE_AIRPORT_REQUIRED)
    private Integer departureAirportId;

    @NotNull(message = Constants.ARRIVAL_AIRPORT_REQUIRED)
    private Integer arrivalAirportId;

    @Future(message = Constants.DEPARTURE_DATE_REQUIRED)
    private LocalDateTime departureDateTime;

    @Future(message = Constants.RETURN_DATE_REQUIRED)
    private LocalDateTime returnDateTime;

    @NotNull(message = Constants.PRICE_REQUIRED)
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
