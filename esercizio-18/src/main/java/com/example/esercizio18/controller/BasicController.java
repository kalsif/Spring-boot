package com.example.esercizio18.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class BasicController {
    @GetMapping("/sum")
    public int sum(){
        return 3+5;
    }
}
