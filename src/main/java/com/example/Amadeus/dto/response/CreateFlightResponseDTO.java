package com.example.Amadeus.dto.response;

import com.example.Amadeus.dto.AirportDTO;
import com.example.Amadeus.util.Constants;
import org.aspectj.apache.bcel.classfile.Constant;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateFlightResponseDTO {
    private String message;

    private int flightId;
    private AirportDTO departureAirport;
    private AirportDTO arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;
    private BigDecimal price;

    public CreateFlightResponseDTO(int flightId, AirportDTO departureAirport, AirportDTO arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime, BigDecimal price) {
        this.flightId = flightId;
        this.message = Constants.FLIGHT_CREATION_SUCCESS;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.price = price;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
