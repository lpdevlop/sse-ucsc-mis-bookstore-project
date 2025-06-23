package com.ucsc.bookstoreproject.database.dto;

import com.ucsc.bookstoreproject.database.model.OrderItemModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;

    private String productName;
    private Double price;
    private Integer quantity;


    public OrderItemDTO(OrderItemModel orderItemModel) {
            setPrice(orderItemModel.getPrice());
            setQuantity(orderItemModel.getQuantity());
            setProductName(orderItemModel.getProductName());
    }
}
