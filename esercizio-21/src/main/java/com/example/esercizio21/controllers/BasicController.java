package com.example.esercizio21.controllers;

import com.example.esercizio21.services.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/v1")
public class BasicController {

    Logger logger = LoggerFactory.getLogger(BasicController.class);
    @Autowired
    private BasicService basicService;
    @Value("${custom_int1}")
    private double number1;
    @Value("${custom_int2}")
    private double number2;

    @GetMapping("/")
    public String welcomeMsg(){
        logger.info("Welcome msg");
        return "Welcome user";
    }

    @GetMapping("/exp")
    public double powerExponent(){
        logger.info("Power calculation");
        return basicService.powerExponent(number1,number2);
    }

    @GetMapping("/get-error")
    public String newCustomError(){
        logger.error("errore");
        Error error = new Error("Errore");
        return "Questo è un " + error.getMessage();
    }
}
