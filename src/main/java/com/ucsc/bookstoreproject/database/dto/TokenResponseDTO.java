package com.ucsc.bookstoreproject.database.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO {

    private String token;

    public TokenResponseDTO(String token) {
        this.token = token;
    }

}
