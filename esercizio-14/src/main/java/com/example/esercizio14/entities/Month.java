package com.example.esercizio14.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Month {

    private long monthNumber;
    private String englishName;
    private String italianName;
    private String germanName;

}
