package com.ucsc.bookstoreproject.controllers;


import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.database.dto.TokenResponseDTO;
import com.ucsc.bookstoreproject.database.dto.login.LoginDTO;
import com.ucsc.bookstoreproject.database.filters.LoginAttemptFilter;
import com.ucsc.bookstoreproject.database.model.UserModel;
import com.ucsc.bookstoreproject.security.JWTHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final JWTHelper jwtHelper;

    private final LoginAttemptFilter loginAttemptFilter;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<PayLoadDTO> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest httpRequest) {
        String ip =loginAttemptFilter.getClientIP(httpRequest);
        try {
            PayLoadDTO payLoadDTO = new PayLoadDTO();
            if (loginAttemptFilter.isBlocked(ip)) {
                payLoadDTO.put("data", "Invalid email or password,Please try again later");
            }
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            UserModel userModel = (UserModel) auth.getPrincipal();
            String token = jwtHelper.generateToken(userModel.getUuid(),userModel.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
            if(Objects.nonNull(token)) {
                loginAttemptFilter.loginSucceeded(ip);
                payLoadDTO.put("data", new TokenResponseDTO(token));
                payLoadDTO.setStatus("200");
            }else {
                loginAttemptFilter.loginFailed(ip);
                payLoadDTO.put("data","Invalid email or password,Please try again later");
                payLoadDTO.setStatus("401");
            }
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        } catch (Exception e) {
            PayLoadDTO payLoadDTO=new PayLoadDTO();
            loginAttemptFilter.loginFailed(ip);
            payLoadDTO.setData("Invalid email or password. Please try again");
            return ResponseEntity.status(500).body(payLoadDTO);
        }
    }



}
