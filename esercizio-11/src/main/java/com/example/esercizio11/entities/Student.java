package com.example.esercizio11.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.TrueFalseConverter;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
//    @Convert(converter = TrueFalseConverter.class)
    private boolean isWorking;
}
