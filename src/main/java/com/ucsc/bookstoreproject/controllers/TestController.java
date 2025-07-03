package com.ucsc.bookstoreproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> registerUser() {
       return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
