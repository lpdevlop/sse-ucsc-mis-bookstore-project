package com.ucsc.bookstoreproject.database.repository;

import com.ucsc.bookstoreproject.database.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Long> {
}
