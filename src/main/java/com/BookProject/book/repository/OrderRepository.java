package com.BookProject.book.repository;

import com.BookProject.book.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {
}
