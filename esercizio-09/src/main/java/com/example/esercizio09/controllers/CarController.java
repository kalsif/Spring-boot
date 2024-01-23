package com.example.esercizio09.controllers;

import com.example.esercizio09.entities.cars.Car;
import com.example.esercizio09.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @PostMapping("/car/create")
    public void addCar(@RequestBody Car car){
        carRepository.saveAndFlush(car);
    }

    @GetMapping("/getAll")
    public ArrayList<Car> getAllCars(){
        ArrayList<Car> avaiable = new ArrayList<>(carRepository.findAll());
        return avaiable;
    }
}
