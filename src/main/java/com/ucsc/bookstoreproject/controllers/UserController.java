package com.ucsc.bookstoreproject.controllers;


import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.database.dto.UserDTO;
import com.ucsc.bookstoreproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<PayLoadDTO> registerUser(@RequestBody UserDTO userDTO) {
        PayLoadDTO payLoadDTO=new PayLoadDTO();
        payLoadDTO.put("User registered successfully", userService.registerUser(userDTO));
        return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<PayLoadDTO> registerAdminUser(@RequestBody UserDTO userDTO) {
        PayLoadDTO payLoadDTO=new PayLoadDTO();
        payLoadDTO.put("User registered successfully", userService.registerAdminUser(userDTO));
        return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
    }


}
