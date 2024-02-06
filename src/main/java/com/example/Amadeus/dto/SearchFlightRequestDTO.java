package com.example.Amadeus.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SearchFlightRequestDTO {
    @NotNull(message = "departure airport should be set")
    String departurePlace;
    @NotNull(message = "arrival airport should be set")
    String arrivalPlace;
    @NotNull(message = "departure date should be set")
    LocalDate departureDate;
    LocalDate returnDate;

    public SearchFlightRequestDTO(String departurePlace, String arrivalPlace, LocalDate departureDate, LocalDate returnDate) {
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }


}
