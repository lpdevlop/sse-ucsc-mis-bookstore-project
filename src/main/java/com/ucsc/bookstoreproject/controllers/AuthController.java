package com.ucsc.bookstoreproject.controllers;


import com.ucsc.bookstoreproject.database.dto.TokenResponseDTO;
import com.ucsc.bookstoreproject.database.dto.login.LoginDTO;
import com.ucsc.bookstoreproject.security.JWTHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final JWTHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtHelper.generateToken(user.getUsername(),user.getPassword(),user.getAuthorities().stream().collect(Collectors.toList()).get(0).getAuthority());
            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }



}
