package com.example.ex2services.DTOs;

import com.example.ex2services.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String firstName;
    private String lastName;

    public User convertRequestToEntity() {
        return User.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .build();
    }

}