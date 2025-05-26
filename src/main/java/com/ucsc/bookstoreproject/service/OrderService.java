package com.ucsc.bookstoreproject.service;

import com.ucsc.bookstoreproject.database.dto.OrderDTO;

public interface OrderService {
    Object createOrder(OrderDTO orderDTO);

    Object searchOrderById();
}
