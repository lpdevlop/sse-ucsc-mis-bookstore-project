package com.ucsc.bookstoreproject.controllers;

import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    public ResponseEntity<PayLoadDTO> createOrder() {
        PayLoadDTO payLoadDTO = new PayLoadDTO();
        payLoadDTO.put("Order created successfully", orderService.createOrder());
        return ResponseEntity.status(200).body(payLoadDTO);
    }


    public ResponseEntity<PayLoadDTO> searchOrderById() {
        PayLoadDTO payLoadDTO = new PayLoadDTO();
        payLoadDTO.put("Order found successfully", orderService.searchOrderById());
        return ResponseEntity.status(200).body(payLoadDTO);
    }



}
