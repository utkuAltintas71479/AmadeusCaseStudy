package com.example.Amadeus.service;

import com.example.Amadeus.dto.*;
import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.entity.User;
import com.example.Amadeus.exception.NoAirportException;
import com.example.Amadeus.exception.UserNameAlreadyInUseException;

import com.example.Amadeus.exception.NoSuchAirportException;
import com.example.Amadeus.repository.AirportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

    @Autowired
    AirportRepository airportRepository;
    @Transactional
    public CreateAirportResponseDTO createAirport(CreateAirportRequestDTO createAirportRequestDTO) {
        Airport airport = new Airport();
        airport.setCity(createAirportRequestDTO.getCity());
        airportRepository.save(airport);
        return new CreateAirportResponseDTO(airport.getCity(), airport.getId(),"Airport created successfully");
    }

    @Transactional
    public void deleteAirport( Integer airportId) {
        Airport airportToDelete = airportRepository.findById(airportId).orElseThrow(() -> new NoSuchAirportException("There is no airport with this Id"));
        airportRepository.delete(airportToDelete);
    }

    @Transactional
    public GetAllAirportsResponseDTO getAllAirports() {
        List<Airport> listOfAirports = airportRepository.findAll();
        if(listOfAirports.isEmpty()){
            throw new NoAirportException("There is no airport in the database yet");
        }
        List<AirportDTO> listOfAirportDTOs = new ArrayList<>();
        for (Airport airport : listOfAirports){
            listOfAirportDTOs.add(new AirportDTO(airport.getId(),airport.getCity()));
        }
        return new GetAllAirportsResponseDTO("Airport deleted successfully", listOfAirportDTOs);
    }
    @Transactional
    public GetAirportResponseDTO getAirport( Integer airportId) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new NoSuchAirportException("There is no airport with this Id"));
        return new GetAirportResponseDTO(airportId, "Airport retreived successfully",airport.getCity());
    }

    @Transactional
    public UpdateAirportResponseDTO updateAirport( Integer airportId,UpdateAirportRequestDTO updateAirportRequestDTO) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new NoSuchAirportException("There is no airport with this Id"));
        airport.setCity(updateAirportRequestDTO.getCity());
        airportRepository.save(airport);
        return new UpdateAirportResponseDTO(airportId, "Airport updated successfully",airport.getCity());
    }
}
