package com.ucsc.bookstoreproject.database.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
