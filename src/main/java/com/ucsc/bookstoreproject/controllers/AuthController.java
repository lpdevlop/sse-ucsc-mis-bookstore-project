package com.ucsc.bookstoreproject.controllers;


import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.database.dto.TokenResponseDTO;
import com.ucsc.bookstoreproject.database.dto.login.LoginDTO;
import com.ucsc.bookstoreproject.security.JWTHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final JWTHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<PayLoadDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtHelper.generateToken(user.getUsername(),loginDTO.getEmail(),user.getAuthorities().stream().collect(Collectors.toList()).get(0).getAuthority());
            PayLoadDTO payLoadDTO = new PayLoadDTO();
            if(Objects.nonNull(token)) {
                payLoadDTO.put("data", new TokenResponseDTO(token));
                payLoadDTO.setStatus("200");
            }else {
                payLoadDTO.put("data",null);
                payLoadDTO.setStatus("401");
            }
            return ResponseEntity.ok(payLoadDTO);
        } catch (Exception e) {
            PayLoadDTO payLoadDTO=new PayLoadDTO();
            payLoadDTO.setData("Invalid email or password. Please try again");
            return ResponseEntity.status(500).body(payLoadDTO);
        }
    }



}
