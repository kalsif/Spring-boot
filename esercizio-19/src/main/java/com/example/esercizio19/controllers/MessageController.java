package com.example.esercizio19.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class MessageController {
    @Autowired
    private Environment environment;

    @GetMapping("/greeting")
    public String greetingMessage(){
        return "Un saluto da " + environment.getProperty("devName") + " con codice " + environment.getProperty("authCode");
    }
}
