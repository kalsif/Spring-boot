package com.example.esercizio14.controllers;

import com.example.esercizio14.entities.Month;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/welcome")
public class BasicController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Welcome user";
    }
}
