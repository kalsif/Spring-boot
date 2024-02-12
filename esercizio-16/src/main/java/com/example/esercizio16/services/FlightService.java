package com.example.esercizio16.services;

import com.example.esercizio16.entities.Flight;
import com.example.esercizio16.entities.Status;
import com.example.esercizio16.repositories.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightService {
    @Autowired
    private FlightRepo flightRepo;

    public List<Flight> createFlights(int n){
        Random r = new Random();
        List<Flight> flights = new ArrayList<>();
        for(int f = 0 ; f < n; f++){
            Flight flight = new Flight();
            flight.setDescription("Volo " + generateRandomString(5));
            flight.setFromAirport(generateRandomString(5));
            flight.setToAirport(generateRandomString(5));
            flight.setStatus(generateRandomStatus());
            flights.add(flight);

        }
        flightRepo.saveAll(flights);
        return flights;
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    private Status generateRandomStatus(){
        Random r = new Random();
        return Status.values()[r.nextInt(Status.values().length)];
    }

    public List<Flight> getAll(){
        return flightRepo.findAllFlights();
    }

    public Page<Flight> getAllPaged(int page, int size){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return flightRepo.findAllPaged(pageable);
    }

    public List<Flight> findByStatus(Status status){
        return flightRepo.findByStatus(status);
    }

    public List<Flight> delayedOrCancelled(Status p1, Status p2){
        return flightRepo.delayedOrCanceled(p1.name().toString(),p2.name().toString());
    }

    public List<Flight> onTimeOrDelayed(Status p1, Status p2){
        return flightRepo.delayedOrCanceled(p1.name().toString(), p2.name().toString());
    }
}
