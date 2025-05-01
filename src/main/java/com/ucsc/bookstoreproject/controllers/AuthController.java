package com.ucsc.bookstoreproject.controllers;


import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.database.dto.login.LoginDTO;
import com.ucsc.bookstoreproject.security.JWTHelper;
import com.ucsc.bookstoreproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final JWTHelper jwtHelper;


    @RequestMapping("/login")
    public ResponseEntity<PayLoadDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            boolean isAuthenticated = authService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());

            PayLoadDTO payload=new PayLoadDTO();

            if (!isAuthenticated) {
                payload.put("Invalid username or password", null);
                return ResponseEntity.status(401).body(payload);
            }

            String token = jwtHelper.generateToken(loginDTO.getUsername(), loginDTO.getPassword());

            payload.put("Login successful", token);

            return ResponseEntity.ok(payload);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }



}
