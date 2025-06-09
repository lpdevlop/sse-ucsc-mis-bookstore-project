package com.ucsc.bookstoreproject.database.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
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


    private String shippingMethod;


    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String zip;
    private String country;
    private String province;

}
