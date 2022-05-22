package com.BookProject.book.service;

import com.BookProject.book.dto.OrderDto;
import com.BookProject.book.model.Order;
import com.BookProject.book.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public boolean create(Order order) {
        return false;
    }

    public Order get(Integer id) {
        return null;
    }

    public boolean update(Order order, Integer id) {
        return false;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public List<OrderDto> fillAllByPage(Integer page, Integer size) {
        return null;
    }
}
