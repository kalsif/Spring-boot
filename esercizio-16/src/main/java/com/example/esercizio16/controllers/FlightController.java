package com.example.esercizio16.controllers;

import com.example.esercizio16.entities.Flight;
import com.example.esercizio16.entities.Status;
import com.example.esercizio16.repositories.FlightRepo;
import com.example.esercizio16.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/create")
    public List<Flight> createFlights(@RequestParam(name = "n", defaultValue = "100") int n ){
        return flightService.createFlights(n);
    }

    @GetMapping("/getAll")
    public List<Flight> getAllFlights(){
        return flightService.getAll();
    }

    @GetMapping("/getAllPaged")
    public Page<Flight> getAllPaged(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size){
        return flightService.getAllPaged(page, size);
    }

    @GetMapping("/findBy/{status}")
    public List<Flight> findByStatus(@PathVariable Status status){
        return flightService.findByStatus(status);
    }

    @GetMapping("/delayedOrCancelled")
    public List<Flight> delayedOrCancelled(@RequestParam(name = "p1") Status p1, @RequestParam(name = "p2") Status p2){
        return flightService.delayedOrCancelled(p1, p2);
    }

    @GetMapping("/onTimeOrDelayed")
    public List<Flight> onTimeOrDelayed(@RequestParam(name = "p1") Status p1, @RequestParam(name = "p2") Status p2){
        return flightService.onTimeOrDelayed(p1, p2);
    }
}
