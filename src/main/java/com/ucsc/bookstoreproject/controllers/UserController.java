package com.ucsc.bookstoreproject.controllers;


import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.database.dto.UserDTO;
import com.ucsc.bookstoreproject.database.dto.user.UserPayloadDTO;
import com.ucsc.bookstoreproject.database.model.UserModel;
import com.ucsc.bookstoreproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<PayLoadDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        PayLoadDTO payLoadDTO = new PayLoadDTO();
        try {
            userService.registerUser(userDTO);
            payLoadDTO.put("User registered successfully", true);
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        }catch (Exception e){
            payLoadDTO.put("Error Creating User",false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payLoadDTO);
        }
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<PayLoadDTO> registerAdminUser(@RequestBody UserDTO userDTO) {
        try {
            PayLoadDTO payLoadDTO = new PayLoadDTO();
            long adminUser = userService.registerAdminUser(userDTO);
            if (Objects.nonNull(adminUser)) {
                payLoadDTO.put("User registered successfully", true);
            }
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        }catch (Exception e){
            PayLoadDTO payLoadDTO = new PayLoadDTO();
            payLoadDTO.put("Error Creating User", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payLoadDTO);
        }
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER') and  #userDTO.id == authentication.principal.uuid")
    @PostMapping
    public ResponseEntity<PayLoadDTO> getUserProfile(@RequestBody UserPayloadDTO userDTO,@AuthenticationPrincipal UserModel user) {
        UUID uuid =user.getUuid();
        PayLoadDTO payLoadDTO = new PayLoadDTO();
        if(uuid.equals(userDTO.getId())){
            payLoadDTO.put("userprofile", userService.getUserProfile(userDTO));
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        }else {
            payLoadDTO.put("Error", "User ID does not match");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payLoadDTO);
        }
    }



}
