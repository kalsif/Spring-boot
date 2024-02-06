package com.example.esercizio15.controllers;

import com.example.esercizio15.entities.Flight;
import com.example.esercizio15.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;
    @PostMapping("/createFlights")
    public List<Flight> getCreatedFlights(){
        return flightService.createFlights();
    }

    @GetMapping("/getAll")
    public ArrayList<Flight> getAll(){
        return flightService.getAll();
    }
}
