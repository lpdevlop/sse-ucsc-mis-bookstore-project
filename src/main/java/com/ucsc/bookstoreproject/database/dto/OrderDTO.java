package com.ucsc.bookstoreproject.database.dto;
import com.ucsc.bookstoreproject.database.model.OrderModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private List<BookDTO> items;

    private Double totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private String orderStatus;

    private String couponCode;
    private Double discount;
    private Double finalAmount;

    private UUID trackingNumber;


    private String shippingMethod;


    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String zip;
    private String country;
    private String province;

    public OrderDTO(OrderModel orderModel) {
        this.totalAmount = orderModel.getTotalAmount();
        this.paymentMethod = orderModel.getPaymentMethod();
        this.paymentStatus = orderModel.getStatus();
        this.trackingNumber=orderModel.getOrderNumber();
        this.finalAmount = orderModel.getTotalAmount();
        this.shippingMethod = orderModel.getShippingMethod();
        this.firstName = orderModel.getFirstName();
        this.lastName = orderModel.getLastName();
        this.streetAddress = orderModel.getStreetAddress();
        this.city = orderModel.getCity();
        this.zip = orderModel.getZip();
        this.country = orderModel.getCountry();
        this.province = orderModel.getProvince();
    }
}
