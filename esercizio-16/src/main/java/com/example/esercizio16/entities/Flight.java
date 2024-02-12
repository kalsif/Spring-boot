package com.example.esercizio16.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Flight.findAllFlights",
                query = "SELECT * FROM Flight",
                resultClass = Flight.class
        ),
        @NamedNativeQuery(
                name = "Flight.findAllPaged",
                query = "SELECT id, description, from_airport, to_airport, status FROM Flight",
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
