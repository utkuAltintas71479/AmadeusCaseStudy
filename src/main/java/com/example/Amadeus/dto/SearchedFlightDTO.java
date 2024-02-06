package com.example.Amadeus.dto;

public class SearchedFlightDTO {
    FlightDTO departureFlight;
    FlightDTO returnFlight;

    public FlightDTO getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(FlightDTO departureFlight) {
        this.departureFlight = departureFlight;
    }

    public FlightDTO getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(FlightDTO returnFlight) {
        this.returnFlight = returnFlight;
    }

    public SearchedFlightDTO(FlightDTO departureFlight, FlightDTO returnFlight) {
        this.departureFlight = departureFlight;
        this.returnFlight = returnFlight;
    }
}
