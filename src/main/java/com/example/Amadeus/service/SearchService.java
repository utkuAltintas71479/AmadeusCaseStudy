package com.example.Amadeus.service;

import com.example.Amadeus.dto.*;
import com.example.Amadeus.dto.request.SearchFlightRequestDTO;
import com.example.Amadeus.dto.response.SearchedFlightResponseDTO;
import com.example.Amadeus.entity.Flight;
import com.example.Amadeus.exception.NoSuitableFlightException;
import com.example.Amadeus.repository.FlightRepository;
import com.example.Amadeus.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private FlightRepository flightRepository;
    @Transactional
    public SearchedFlightResponseDTO searchFlights(SearchFlightRequestDTO searchFlightRequestDTO) {
        if(searchFlightRequestDTO.getReturnDate()==null){
            return searchOneWayFlight(searchFlightRequestDTO);
        }
        return searchTwoWayFlight(searchFlightRequestDTO);
    }

    private SearchedFlightResponseDTO searchOneWayFlight(SearchFlightRequestDTO searchFlightRequestDTO) {
        List<Flight> listOfSuitableFlightEntities = flightRepository.findFlightsBySearchCriteriaOneWay(searchFlightRequestDTO.getDeparturePlace(),
                searchFlightRequestDTO.getArrivalPlace(),searchFlightRequestDTO.getDepartureDate().atStartOfDay(),
                searchFlightRequestDTO.getDepartureDate().plusDays(1).atStartOfDay());
        List<SearchedFlightDTO> listOfSearchedFlightDTOs = new ArrayList<>();
        if(listOfSuitableFlightEntities.isEmpty()){
            throw new NoSuitableFlightException(Constants.NO_SUITABLE_FLIGHT);
        }
        for (Flight flight : listOfSuitableFlightEntities){
            FlightDTO departureFlightDto = new FlightDTO(flight.getFlightId(), new AirportDTO(flight.getDepartureAirport().getAirportId(), flight.getDepartureAirport().getAirportCity()), new AirportDTO(flight.getArrivalAirport().getAirportId(), flight.getArrivalAirport().getAirportCity()), flight.getDepartureDateTime(), flight.getReturnDateTime(), flight.getPrice());
            FlightDTO returnFlightDTO = null;
            listOfSearchedFlightDTOs.add(new SearchedFlightDTO(departureFlightDto,returnFlightDTO));
        }
        return new SearchedFlightResponseDTO( listOfSearchedFlightDTOs);
    }

    private SearchedFlightResponseDTO searchTwoWayFlight(SearchFlightRequestDTO searchFlightRequestDTO) {
        List<Flight> listOfSuitableFlightEntities = flightRepository.findFlightsBySearchCriteriaTwoWay(searchFlightRequestDTO.getDeparturePlace(),
                searchFlightRequestDTO.getArrivalPlace(),searchFlightRequestDTO.getDepartureDate().atStartOfDay(),
                searchFlightRequestDTO.getDepartureDate().plusDays(1).atStartOfDay(),searchFlightRequestDTO.getReturnDate().atStartOfDay(),
                searchFlightRequestDTO.getReturnDate().plusDays(1).atStartOfDay());
        List<SearchedFlightDTO> listOfSearchedFlightDTOs = new ArrayList<>();
        if(listOfSuitableFlightEntities.isEmpty()){
            throw new NoSuitableFlightException(Constants.NO_SUITABLE_FLIGHT);
        }
        for (Flight flight : listOfSuitableFlightEntities){
            FlightDTO departureFlightDto = new FlightDTO(flight.getFlightId(), new AirportDTO(flight.getDepartureAirport().getAirportId(), flight.getDepartureAirport().getAirportCity()), new AirportDTO(flight.getArrivalAirport().getAirportId(), flight.getArrivalAirport().getAirportCity()), flight.getDepartureDateTime(), flight.getReturnDateTime(), flight.getPrice());
            FlightDTO returnFlightDTO = new FlightDTO(flight.getFlightId(), new AirportDTO(flight.getArrivalAirport().getAirportId(), flight.getArrivalAirport().getAirportCity()), new AirportDTO(flight.getDepartureAirport().getAirportId(), flight.getDepartureAirport().getAirportCity()), flight.getReturnDateTime(),null, null);
            listOfSearchedFlightDTOs.add(new SearchedFlightDTO(departureFlightDto,returnFlightDTO));
        }
        return new SearchedFlightResponseDTO( listOfSearchedFlightDTOs);
    }
}
