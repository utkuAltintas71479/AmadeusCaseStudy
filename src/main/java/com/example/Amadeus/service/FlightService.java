package com.example.Amadeus.service;

import com.example.Amadeus.dto.*;
import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.entity.Flight;
import com.example.Amadeus.exception.*;
import com.example.Amadeus.repository.AirportRepository;
import com.example.Amadeus.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportRepository airportRepository;
    @Transactional
    public CreateFlightResponseDTO createFlight(CreateFlightRequestDTO createFlightRequestDTO) {
        Flight flight = new Flight();
        if (createFlightRequestDTO.getReturnDateTime() != null) {
            if (createFlightRequestDTO.getDepartureDateTime().isAfter(createFlightRequestDTO.getReturnDateTime())) {
                throw new DateMismatchException("Departure time cannot be after return time");
            }
        }
        if(createFlightRequestDTO.getDepartureAirportId() == createFlightRequestDTO.getArrivalAirportId()){
            throw new AirportMismatchException("Arrival and departure airports cannot be the same");
        }
        flight.setPrice(createFlightRequestDTO.getPrice());
        flight.setReturnDateTime(createFlightRequestDTO.getReturnDateTime());
        flight.setDepartureDateTime(createFlightRequestDTO.getDepartureDateTime());
        Airport[] airportsForFlight = getAirportsForFlight(createFlightRequestDTO);
        flight.setDepartureAirport(airportsForFlight[0]);
        flight.setArrivalAirport(airportsForFlight[1]);
        flightRepository.save(flight);
        Airport departureAirport = flight.getDepartureAirport();
        Airport arrivalAirport = flight.getArrivalAirport();
        return new CreateFlightResponseDTO(flight.getFlightId(),  new AirportDTO(departureAirport.getAirportId(),departureAirport.getAirportCity()),new AirportDTO(arrivalAirport.getAirportId(),arrivalAirport.getAirportCity()),
        flight.getDepartureDateTime(),  flight.getReturnDateTime(), flight.getPrice());
    }

    public Airport[] getAirportsForFlight(CreateFlightRequestDTO createFlightRequestDTO){
        if (!(airportRepository.existsById(createFlightRequestDTO.getArrivalAirportId()) &&
                airportRepository.existsById(createFlightRequestDTO.getDepartureAirportId()))) {
            throw new NoSuchAirportException("One or more specified airports do not exist");
        }
        Airport arrivalAirport = airportRepository.getById(createFlightRequestDTO.getArrivalAirportId());
        Airport depratureAirport = airportRepository.getById(createFlightRequestDTO.getDepartureAirportId());
        return new Airport[]{depratureAirport,arrivalAirport};
    }

    @Transactional
    public void deleteFlight( Integer flightId) {
        Flight flightToDelete = flightRepository.findById(flightId).orElseThrow(() -> new NoSuchFlightException("There is no flight with this Id"));
        flightRepository.delete(flightToDelete);
    }


    @Transactional
    public GetAllFlightsResponseDTO getAllFlights() {
        List<Flight> listOfFlights = flightRepository.findAll();
        if(listOfFlights.isEmpty()){
            throw new NoFlightException("There is no flight in the database yet");
        }
        List<FlightDTO> listOfFlightDTOs = new ArrayList<>();
        for (Flight flight : listOfFlights){
            listOfFlightDTOs.add(new FlightDTO(flight.getFlightId(), new AirportDTO(flight.getDepartureAirport().getAirportId(),flight.getDepartureAirport().getAirportCity()), new AirportDTO(flight.getArrivalAirport().getAirportId(),flight.getArrivalAirport().getAirportCity()), flight.getDepartureDateTime(), flight.getReturnDateTime(), flight.getPrice()));
        }
        return new GetAllFlightsResponseDTO( listOfFlightDTOs);
    }

    @Transactional
    public GetFlightResponseDTO getFlight( Integer flightID) {
        Flight flight = flightRepository.findById(flightID).orElseThrow(() -> new NoSuchFlightException("There is no flight with this Id"));
        return new GetFlightResponseDTO( flight.getFlightId(), flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureDateTime(), flight.getReturnDateTime(), flight.getPrice());
    }

    @Transactional
    public UpdateFlightResponseDTO updateFlight( Integer flightId,UpdateFlightRequestDTO updateFlightRequestDTO) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new NoSuchFlightException("There is no flight with this Id"));
        Flight updatedFlight =createUpdatedFlight(flight,updateFlightRequestDTO);
        flightRepository.save(updatedFlight);
        return new UpdateFlightResponseDTO(updatedFlight.getDepartureAirport(), updatedFlight.getArrivalAirport(), updatedFlight.getDepartureDateTime(), updatedFlight.getReturnDateTime(), updatedFlight.getPrice());
    }

    private Flight createUpdatedFlight(Flight flight, UpdateFlightRequestDTO updateFlightRequestDTO){
        if(updateFlightRequestDTO.getPrice()!=null){
            flight.setPrice(updateFlightRequestDTO.getPrice());
        }
        if(updateFlightRequestDTO.getDepartureAirportId()!=null && updateFlightRequestDTO.getArrivalAirportId()!=null){
            Airport arrivalAirport = airportRepository.findById(updateFlightRequestDTO.getArrivalAirportId())
                    .orElseThrow(() -> new NoSuchAirportException("No airport with this id"));
            Airport departureAirport = airportRepository.findById(updateFlightRequestDTO.getArrivalAirportId())
                    .orElseThrow(() -> new NoSuchAirportException("No airport with this id"));
            flight.setArrivalAirport(arrivalAirport);
            flight.setDepartureAirport(departureAirport);
        }
        if(updateFlightRequestDTO.getDepartureAirportId()!=null){
            if(flight.getArrivalAirport().getAirportId()==updateFlightRequestDTO.getDepartureAirportId()){
                throw new AirportMismatchException("Arrival and Departure airports should be different");
            }
            Airport departureAirport = airportRepository.findById(updateFlightRequestDTO.getDepartureAirportId())
                    .orElseThrow(() -> new NoSuchAirportException("No airport with this id"));
            flight.setDepartureAirport(departureAirport);
        }
        if(updateFlightRequestDTO.getArrivalAirportId()!=null){
            if(flight.getDepartureAirport().getAirportId()==updateFlightRequestDTO.getArrivalAirportId()){
                throw new AirportMismatchException("Arrival and Departure airports should be different");
            }
            Airport arrivalAirport = airportRepository.findById(updateFlightRequestDTO.getArrivalAirportId())
                    .orElseThrow(() -> new NoSuchAirportException("No airport with this id"));
            flight.setDepartureAirport(arrivalAirport);
        }
        if(updateFlightRequestDTO.getDepartureDateTime()!=null && updateFlightRequestDTO.getReturnDateTime()!=null){
            flight.setDepartureDateTime(updateFlightRequestDTO.getDepartureDateTime());
            flight.setReturnDateTime(updateFlightRequestDTO.getReturnDateTime());
        }
        if(updateFlightRequestDTO.getReturnDateTime()!=null){
            if(flight.getDepartureDateTime().isAfter(updateFlightRequestDTO.getReturnDateTime())){
                throw new DateMismatchException("return date cannot be before departure time");
            }
            flight.setReturnDateTime(updateFlightRequestDTO.getReturnDateTime());
        }
        if(updateFlightRequestDTO.getDepartureDateTime()!=null &&flight.getReturnDateTime()!=null ){
            if(updateFlightRequestDTO.getDepartureDateTime().isAfter(flight.getReturnDateTime())){
                throw new DateMismatchException("return date cannot be before departure time");
            }
            flight.setDepartureDateTime(updateFlightRequestDTO.getDepartureDateTime());
        }
        return flight;
    }

}
