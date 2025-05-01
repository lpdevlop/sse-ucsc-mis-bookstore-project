package com.ucsc.bookstoreproject.database.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String username;

    private String email;

    private String password;
}
