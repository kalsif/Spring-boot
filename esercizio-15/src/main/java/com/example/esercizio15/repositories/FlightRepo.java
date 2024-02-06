package com.example.esercizio15.repositories;

import com.example.esercizio15.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface FlightRepo extends JpaRepository<Flight,Long> {

    List<Flight> findAllFlights();
}
