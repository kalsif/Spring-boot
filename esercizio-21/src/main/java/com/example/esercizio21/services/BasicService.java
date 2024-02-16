package com.example.esercizio21.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BasicService {

    Logger logger = LoggerFactory.getLogger(BasicService.class);

    public double powerExponent(double number1, double number2){
        logger.debug("Power calculation start");
        double result =  Math.pow(number1,number2);
        logger.debug("End calculation");
        return result;
    }
}
