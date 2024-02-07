package com.example.Amadeus.entity;

import com.example.Amadeus.util.Constants;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer flightId;

    @ManyToOne
    @JoinColumn(name = "departureAirportID", nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrivalAirportID", nullable = false)
    private Airport arrivalAirport;

    @Column(nullable = false)
    private LocalDateTime departureDateTime;

    @Column
    private LocalDateTime returnDateTime;

    @Column(nullable = false, precision = Constants.PRICE_PRECISION, scale = Constants.PRICE_SCALE)
    private BigDecimal price;

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
