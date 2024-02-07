package com.example.Amadeus.dto.request;

import com.example.Amadeus.util.Constants;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SearchFlightRequestDTO {
    @NotNull(message = Constants.DEPARTURE_AIRPORT_PLACE_REQUIRED)
    String departurePlace;
    @NotNull(message = Constants.ARRIVAL_AIRPORT_PLACE_REQUIRED)
    String arrivalPlace;
    @NotNull(message = Constants.DEPARTURE_DATE_REQUIRED)
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
