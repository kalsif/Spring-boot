package com.example.esercizio15.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Flight.findAllFlights",
                query = "SELECT * FROM Flight ",
                resultClass = Flight.class
        )
})



public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private String fromAirport;
    private String toAirport;

    @Enumerated(value = EnumType.STRING)
    private Status status;



}
