package com.example.esercizio14.controllers;

import com.example.esercizio14.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/months")
public class MonthController {
    @GetMapping("/month")
    public Month getSingle(@RequestAttribute Month month){
        return month;
    }


}
