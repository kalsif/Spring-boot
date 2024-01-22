package com.example.esercizio08.controllers;

import com.example.esercizio08.entities.Car;
import com.example.esercizio08.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/v1/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/car/create")
    public void createCar(@RequestBody Car car) {
        carRepository.saveAndFlush(car);
    }

    @GetMapping("/getAll")
    public ArrayList<Car> getAllCars() {
        ArrayList<Car> allCar = new ArrayList<>();
        for (Car c : carRepository.findAll()) {
            allCar.add(c);
        }
        return allCar;
    }

    @GetMapping("/car/getById/{id}")
    public Car getById(@PathVariable long id) {
        for (Car c : carRepository.findAll()) {
            if (carRepository.existsById(id) && c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @PatchMapping("/car/update/{id}")
    public Car updateCar(@PathVariable long id, @RequestParam String newType) {
        for (Car c : carRepository.findAll()) {
            if (carRepository.existsById(id) && c.getId() == id) {
                c.setType(newType);
                carRepository.saveAndFlush(c);
                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/car/deleteOne/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllCars(){
        carRepository.deleteAll();
    }


}
