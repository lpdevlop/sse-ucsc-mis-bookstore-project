package com.ucsc.bookstoreproject.controllers;

import com.ucsc.bookstoreproject.database.dto.OrderDTO;
import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<PayLoadDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        PayLoadDTO payLoadDTO = new PayLoadDTO();
        payLoadDTO.put("Order created successfully", orderService.createOrder(orderDTO));
        return ResponseEntity.status(200).body(payLoadDTO);
    }

    public ResponseEntity<PayLoadDTO> searchOrderById() {
        PayLoadDTO payLoadDTO = new PayLoadDTO();
        payLoadDTO.put("Order found successfully", orderService.searchOrderById());
        return ResponseEntity.status(200).body(payLoadDTO);
    }



}
