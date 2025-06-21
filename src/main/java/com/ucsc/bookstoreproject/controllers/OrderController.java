package com.ucsc.bookstoreproject.controllers;

import com.ucsc.bookstoreproject.database.dto.OrderDTO;
import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    public ResponseEntity<Object> searchOrderById() {
        return ResponseEntity.status(200).body(orderService.searchOrderById());
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/my")
    public ResponseEntity<Object> getMyOrders() {
        return ResponseEntity.status(200).body(orderService.getMyOrders());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<PayLoadDTO> getAllOrders() {
        PayLoadDTO payLoadDTO = new PayLoadDTO();
        payLoadDTO.put("data",orderService.getAllOrders());
        return ResponseEntity.status(200).body(payLoadDTO);
    }


}
