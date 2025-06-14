package com.ucsc.bookstoreproject.controllers;


import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.database.dto.UserDTO;
import com.ucsc.bookstoreproject.database.dto.user.UserPayloadDTO;
import com.ucsc.bookstoreproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<PayLoadDTO> registerUser(@RequestBody UserDTO userDTO) {
        PayLoadDTO payLoadDTO=new PayLoadDTO();
        userService.registerUser(userDTO);
        payLoadDTO.put("User registered successfully",true);
        return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<PayLoadDTO> registerAdminUser(@RequestBody UserDTO userDTO) {
        PayLoadDTO payLoadDTO=new PayLoadDTO();
        long adminUser=userService.registerAdminUser(userDTO);
        if(Objects.nonNull(adminUser)) {
            payLoadDTO.put("User registered successfully",true);
        }
        return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER') and  #userDTO.id == authentication.principal.uuid")
    @PostMapping
    public ResponseEntity<PayLoadDTO> getUserProfile(@RequestBody UserPayloadDTO userDTO) {
        PayLoadDTO payLoadDTO = new PayLoadDTO();
        payLoadDTO.put("userprofile", userService.getUserProfile(userDTO));
        return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
    }



}
