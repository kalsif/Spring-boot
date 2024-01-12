package com.example.esercizio04.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class NameController {

    @GetMapping(path = "/name")
    @Operation(summary = "name", description = "The name of user")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "400" , description = "Bad input"),
            @ApiResponse(responseCode = "500", description = "Internal error"),
            @ApiResponse(responseCode = "202", description = "created")
    })
    public String ciao(@RequestParam String name){
        return "Ciao " + name ;
    }


    @PostMapping("/name/reverse")
    @Operation(summary = "reverse-name", description = "The reverse name of the user")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400" , description = "Bad input")
    })
    public StringBuilder reverse(@RequestParam String name){
        StringBuilder reverse = new StringBuilder(name);
        reverse.reverse();
        return reverse;
    }
}
