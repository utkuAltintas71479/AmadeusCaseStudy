package com.example.Amadeus.service;

import com.example.Amadeus.dto.*;
import com.example.Amadeus.dto.request.CreateFlightRequestDTO;
import com.example.Amadeus.dto.request.UpdateFlightRequestDTO;
import com.example.Amadeus.dto.response.CreateFlightResponseDTO;
import com.example.Amadeus.dto.response.GetAllFlightsResponseDTO;
import com.example.Amadeus.dto.response.GetFlightResponseDTO;
import com.example.Amadeus.dto.response.UpdateFlightResponseDTO;
import com.example.Amadeus.entity.Airport;
import com.example.Amadeus.entity.Flight;
import com.example.Amadeus.exception.*;
import com.example.Amadeus.repository.AirportRepository;
import com.example.Amadeus.repository.FlightRepository;
import com.example.Amadeus.util.Constants;
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
                throw new DateMismatchException(Constants.DATE_MISMATCH_DEPARTURE_AFTER_RETURN);
            }
        }
        if(createFlightRequestDTO.getDepartureAirportId() == createFlightRequestDTO.getArrivalAirportId()){
            throw new AirportMismatchException(Constants.AIRPORT_MISMATCH);
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
        return new CreateFlightResponseDTO(flight.getFlightId(),  new AirportDTO(departureAirport.getAirportId(), departureAirport.getAirportCity()),new AirportDTO(arrivalAirport.getAirportId(), arrivalAirport.getAirportCity()),
        flight.getDepartureDateTime(),  flight.getReturnDateTime(), flight.getPrice());
    }

    public Airport[] getAirportsForFlight(CreateFlightRequestDTO createFlightRequestDTO){
        if (!(airportRepository.existsById(createFlightRequestDTO.getArrivalAirportId()) &&
                airportRepository.existsById(createFlightRequestDTO.getDepartureAirportId()))) {
            throw new NoSuchAirportException(Constants.NO_SUCH_AIRPORT);
        }
        Airport arrivalAirport = airportRepository.getById(createFlightRequestDTO.getArrivalAirportId());
        Airport depratureAirport = airportRepository.getById(createFlightRequestDTO.getDepartureAirportId());
        return new Airport[]{depratureAirport, arrivalAirport};
    }

    @Transactional
    public void deleteFlight( Integer flightId) {
        Flight flightToDelete = flightRepository.findById(flightId).orElseThrow(() -> new NoSuchFlightException(Constants.NO_SUCH_FLIGHT));
        flightRepository.delete(flightToDelete);
    }


    @Transactional
    public GetAllFlightsResponseDTO getAllFlights() {
        List<Flight> listOfFlightEntities = flightRepository.findAll();
        if(listOfFlightEntities.isEmpty()){
            throw new NoFlightException(Constants.NO_FLIGHT_IN_DB);
        }
        List<FlightDTO> listOfFlightDTOs = new ArrayList<>();
        for (Flight flight : listOfFlightEntities){
            listOfFlightDTOs.add(new FlightDTO(flight.getFlightId(), new AirportDTO(flight.getDepartureAirport().getAirportId(), flight.getDepartureAirport().getAirportCity()), new AirportDTO(flight.getArrivalAirport().getAirportId(), flight.getArrivalAirport().getAirportCity()), flight.getDepartureDateTime(), flight.getReturnDateTime(), flight.getPrice()));
        }
        return new GetAllFlightsResponseDTO( listOfFlightDTOs);
    }

    @Transactional
    public GetFlightResponseDTO getFlight(Integer flightID) {
        Flight flight = flightRepository.findById(flightID).orElseThrow(() -> new NoSuchFlightException(Constants.NO_SUCH_FLIGHT));
        return new GetFlightResponseDTO( flight.getFlightId(), flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureDateTime(), flight.getReturnDateTime(), flight.getPrice());
    }

    @Transactional
    public UpdateFlightResponseDTO updateFlight(Integer flightId, UpdateFlightRequestDTO updateFlightRequestDTO) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new NoSuchFlightException(Constants.NO_SUCH_FLIGHT));
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
                    .orElseThrow(() -> new NoSuchAirportException(Constants.NO_SUCH_AIRPORT));
            Airport departureAirport = airportRepository.findById(updateFlightRequestDTO.getArrivalAirportId())
                    .orElseThrow(() -> new NoSuchAirportException(Constants.NO_SUCH_AIRPORT));
            flight.setArrivalAirport(arrivalAirport);
            flight.setDepartureAirport(departureAirport);
        }
        if(updateFlightRequestDTO.getDepartureAirportId()!=null){
            if(flight.getArrivalAirport().getAirportId()==updateFlightRequestDTO.getDepartureAirportId()){
                throw new AirportMismatchException(Constants.AIRPORT_MISMATCH);
            }
            Airport departureAirport = airportRepository.findById(updateFlightRequestDTO.getDepartureAirportId())
                    .orElseThrow(() -> new NoSuchAirportException(Constants.NO_SUCH_AIRPORT));
            flight.setDepartureAirport(departureAirport);
        }
        if(updateFlightRequestDTO.getArrivalAirportId()!=null){
            if(flight.getDepartureAirport().getAirportId()==updateFlightRequestDTO.getArrivalAirportId()){
                throw new AirportMismatchException(Constants.NO_SUCH_AIRPORT);
            }
            Airport arrivalAirport = airportRepository.findById(updateFlightRequestDTO.getArrivalAirportId())
                    .orElseThrow(() -> new NoSuchAirportException(Constants.NO_SUCH_AIRPORT));
            flight.setDepartureAirport(arrivalAirport);
        }
        if(updateFlightRequestDTO.getDepartureDateTime()!=null && updateFlightRequestDTO.getReturnDateTime()!=null){
            flight.setDepartureDateTime(updateFlightRequestDTO.getDepartureDateTime());
            flight.setReturnDateTime(updateFlightRequestDTO.getReturnDateTime());
        }
        if(updateFlightRequestDTO.getReturnDateTime()!=null){
            if(flight.getDepartureDateTime().isAfter(updateFlightRequestDTO.getReturnDateTime())){
                throw new DateMismatchException(Constants.DATE_MISMATCH_DEPARTURE_AFTER_RETURN);
            }
            flight.setReturnDateTime(updateFlightRequestDTO.getReturnDateTime());
        }
        if(updateFlightRequestDTO.getDepartureDateTime()!=null && flight.getReturnDateTime()!=null ){
            if(updateFlightRequestDTO.getDepartureDateTime().isAfter(flight.getReturnDateTime())){
                throw new DateMismatchException(Constants.DATE_MISMATCH_DEPARTURE_AFTER_RETURN);
            }
            flight.setDepartureDateTime(updateFlightRequestDTO.getDepartureDateTime());
        }
        return flight;
    }

}
