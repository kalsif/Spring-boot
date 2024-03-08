package com.example.ex2services.entities;

import com.example.ex2services.DTOs.UserResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String profileImg;


    public UserResponseDto convertEntityToUserResponse() {
        return UserResponseDto.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .profileImg(this.profileImg)
                .build();
    }
}
