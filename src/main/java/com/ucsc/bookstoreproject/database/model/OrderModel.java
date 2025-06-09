package com.ucsc.bookstoreproject.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", unique = true, nullable = false)
    private UUID orderNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "order_date")
    @CreatedDate
    private LocalDateTime orderDate;


    @Column(name = "payment_method")
    private String paymentMethod;


    private String shippingMethod;


    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String zip;
    private String country;
    private String province;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @CreatedBy
    private UserModel user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemModel> orderItems;

    @PrePersist
    public void prePersist() {
        if (this.orderNumber == null) {
            this.orderNumber = UUID.randomUUID();
        }
    }


}
