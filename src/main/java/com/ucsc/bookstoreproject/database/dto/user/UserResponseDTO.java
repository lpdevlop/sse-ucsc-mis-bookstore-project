package com.ucsc.bookstoreproject.database.dto.user;


import com.ucsc.bookstoreproject.enums.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private String firstName;
    private String lastName;
    private String email;
}
