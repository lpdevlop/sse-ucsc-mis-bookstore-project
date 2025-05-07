package com.ucsc.bookstoreproject.database.dto;
import lombok.Data;

import java.util.List;


@Data
public class OrderDTO {

    private List<BookDTO> items;

    private Double totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private String orderStatus;

    private String couponCode;
    private Double discount;
    private Double finalAmount;

    private String trackingNumber;
}
