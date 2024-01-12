package com.example.esercizio03;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class NameController {
    @GetMapping(path = "/ciao")
    public String ciao(@RequestParam String name){
        return "Ciao " + name ;
    }

    @PostMapping("/ciao/reverse")
    public StringBuilder reverse(@RequestParam String name){
        StringBuilder reverse = new StringBuilder(name);
        reverse.reverse();
        return reverse;
    }
}
