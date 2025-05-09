package com.ucsc.bookstoreproject.database.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", unique = true, nullable = false)
    private String orderNumber;

    @Column(name = "status")
    private String status; // e.g., PENDING, PAID, SHIPPED, CANCELLED

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;



}
