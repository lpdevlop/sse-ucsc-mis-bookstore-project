package com.ucsc.bookstoreproject.service.impl;

import com.ucsc.bookstoreproject.database.dto.BookDTO;
import com.ucsc.bookstoreproject.database.dto.OrderDTO;
import com.ucsc.bookstoreproject.database.model.BookModel;
import com.ucsc.bookstoreproject.database.model.OrderItemModel;
import com.ucsc.bookstoreproject.database.model.OrderModel;
import com.ucsc.bookstoreproject.database.repository.BookRepository;
import com.ucsc.bookstoreproject.database.repository.OrderRepository;
import com.ucsc.bookstoreproject.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final BookRepository bookRepository;

    private final OrderRepository orderRepository;

    @Override
    public Object createOrder(OrderDTO orderDTO) {

        OrderModel orderModel=new OrderModel();
        orderModel.setTotalAmount(orderDTO.getTotalAmount());
        orderModel.setStatus("PENDING");
        orderModel.setTotalAmount(orderDTO.getTotalAmount());
        orderModel.setShippingMethod(orderDTO.getShippingMethod());
        orderModel.setPaymentMethod(orderDTO.getPaymentMethod());
        orderModel.setFirstName(orderDTO.getFirstName());
        orderModel.setLastName(orderDTO.getLastName());

        orderModel.setCity(orderDTO.getCity());
        orderModel.setCountry(orderDTO.getCountry());
        orderModel.setZip(orderDTO.getZip());
        orderModel.setStreetAddress(orderDTO.getStreetAddress());
        List<OrderItemModel> itemList = new ArrayList<>();

        for (BookDTO itemDTO : orderDTO.getItems()) {
            Optional<BookModel> bookOpt = bookRepository.findById(itemDTO.getId());

            if (bookOpt.isPresent()) {
                BookModel book = bookOpt.get();

                OrderItemModel item = new OrderItemModel();
                item.setProductName(book.getTitle());
                item.setPrice(book.getPrice());
                item.setQuantity(itemDTO.getOrderQuantity());
                item.setOrder(orderModel);
                itemList.add(item);
            } else {
                throw new EntityNotFoundException("Book not found with ID: ");
            }
        orderRepository.save(orderModel);
        }
       return null;
    }

    @Override
    public Object searchOrderById() {
        return null;
    }
}
