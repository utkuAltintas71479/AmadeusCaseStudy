package com.example.Amadeus.service;

import com.example.Amadeus.dto.*;
import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.entity.Flight;
import com.example.Amadeus.exception.NoAirportException;

import com.example.Amadeus.exception.NoSuchAirportException;
import com.example.Amadeus.repository.AirportRepository;
import com.example.Amadeus.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

    @Autowired
    AirportRepository airportRepository;
    @Autowired
    FlightRepository flightRepository;
    @Transactional
    public CreateAirportResponseDTO createAirport(CreateAirportRequestDTO createAirportRequestDTO) {
        Airport airport = new Airport();
        airport.setAirportCity(createAirportRequestDTO.getAirportCity());
        airportRepository.save(airport);
        return new CreateAirportResponseDTO(airport.getAirportCity(), airport.getAirportId());
    }

    @Transactional
    public void deleteAirport( Integer airportId) {
        Airport airportToDelete = airportRepository.findById(airportId).orElseThrow(() -> new NoSuchAirportException("There is no airport with this Id"));
        deleteFlightsWithAirport(airportToDelete);
        airportRepository.delete(airportToDelete);
    }

    public void deleteFlightsWithAirport(Airport airportToDelete) {
        List<Flight> flightsOfAirport = flightRepository.findFlightsByAirportId(airportToDelete.getAirportId());
        for (Flight flight : flightsOfAirport){
            flightRepository.delete(flight);
        }
    }


    @Transactional
    public GetAllAirportsResponseDTO getAllAirports() {
        List<Airport> listOfAirports = airportRepository.findAll();
        if(listOfAirports.isEmpty()){
            throw new NoAirportException("There is no airport in the database yet");
        }
        List<AirportDTO> listOfAirportDTOs = new ArrayList<>();
        for (Airport airport : listOfAirports){
            listOfAirportDTOs.add(new AirportDTO(airport.getAirportId(),airport.getAirportCity()));
        }
        return new GetAllAirportsResponseDTO( listOfAirportDTOs);
    }
    @Transactional
    public GetAirportResponseDTO getAirport( Integer airportId) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new NoSuchAirportException("There is no airport with this Id"));
        return new GetAirportResponseDTO(airportId,airport.getAirportCity());
    }

    @Transactional
    public UpdateAirportResponseDTO updateAirport( Integer airportId,UpdateAirportRequestDTO updateAirportRequestDTO) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new NoSuchAirportException("There is no airport with this Id"));
        airport.setAirportCity(updateAirportRequestDTO.getAirportCity());
        airportRepository.save(airport);
        return new UpdateAirportResponseDTO(airportId,airport.getAirportCity());
    }

    public boolean checkIfAirportsExist(Integer arrivalAirportId,Integer departureAirportId){
        if(airportRepository.existsById(arrivalAirportId) && airportRepository.existsById(departureAirportId)){
            return true;
        }
        return false;
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


}
