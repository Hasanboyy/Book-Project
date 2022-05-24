package com.BookProject.book.service;

import com.BookProject.book.dto.OrderDto;
import com.BookProject.book.exeption.BookException;
import com.BookProject.book.model.Order;
import com.BookProject.book.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private BookService bookService;

    private CustomerService customerService;

    public boolean create(OrderDto dto) {
        Order order = new Order();
        bookService.getEntity(dto.getBookId());
        customerService.get(dto.getCustomerId());
        order.setBookId(dto.getBookId());
        order.setCustomerId(dto.getCustomerId());
        order.setQuality(dto.getQuality());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(true);
        orderRepository.save(order);
        return true;
    }

    public OrderDto get(Integer id) {
        Order order = getEntiy(id);
        OrderDto orderDto = new OrderDto();
        convertEntityToDto(order,orderDto);
        return orderDto;
    }

    public boolean update(OrderDto dto, Integer id) {
        Order update = getEntiy(id);
        customerService.getEntity(update.getCustomerId());
        update.setCustomerId(dto.getCustomerId());
        bookService.getEntity(dto.getBookId());
        update.setBookId(dto.getBookId());
        update.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(update);

        return true;
    }

    public boolean delete(Integer id) {
        Order order = getEntiy(id);
        order.setDeletedAt(LocalDateTime.now());
        orderRepository.save(order);
        return true;
    }

    public List<OrderDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Order> resultPage = orderRepository.findAll(pageable);
        List<OrderDto> response = new ArrayList<>();
        for (Order order : resultPage) {
            if (order.getDeletedAt() == null){
                OrderDto dto = new OrderDto();
                dto.setId(order.getId());
                dto.setQuality(order.getQuality());
                response.add(dto);
            }
        }
        return response;
    }

    private void convertEntityToDto(Order order, OrderDto dto) {
        dto.setId(order.getId());
        dto.setCustomerDto(customerService.get(order.getCustomerId()));
        dto.setQuality(order.getQuality());
    }

    private Order getEntiy(Integer id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isEmpty()){
            throw new BookException("Order not found");
        }
        return optional.get();
    }
}
