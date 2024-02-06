package com.example.esercizio15.services;

import com.example.esercizio15.entities.Flight;
import com.example.esercizio15.entities.Status;
import com.example.esercizio15.repositories.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightService {
    @Autowired
    private FlightRepo flightRepo;

    public List<Flight> createFlights(){
        Random r = new Random();
        List<Flight> flights = new ArrayList<>();
        for(int f = 0 ; f < 50; f++){
            Flight flight = new Flight();
            flight.setDescription("Volo " + generateRandomString(5));
            flight.setFromAirport(generateRandomString(5));
            flight.setToAirport(generateRandomString(5));
            flight.setStatus(Status.ONTIME);
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

    public ArrayList<Flight> getAll(){
        return new ArrayList<>(flightRepo.findAllFlights());
    }
}
