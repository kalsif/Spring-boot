package com.example.esercizio17.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/names")
public class DevNameController {
    @GetMapping("/myname")
    public String devName(@RequestParam String name){
        return "MY name is " + name;
    }
}
