package com.example.esercizio20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HelloController {
    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public String helloMsg(){
        return env.getProperty("welcomeMsg");
    }
}
