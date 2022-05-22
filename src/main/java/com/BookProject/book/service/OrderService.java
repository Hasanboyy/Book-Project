package com.BookProject.book.service;

import com.BookProject.book.dto.OrderDto;
import com.BookProject.book.model.Order;
import com.BookProject.book.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private BookService bookService;

    private CustomerService customerService;

    public boolean create(OrderDto dto) {
        Order order = new Order();
        //TODO:Book checked
        customerService.get(dto.getCustomer_id());
        order.setCustomer_id(dto.getCustomer_id());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(true);
        return true;
    }

    public OrderDto get(Integer id) {
        Order order = getEntiy(id);
        OrderDto orderDto = new OrderDto();
        convertEntitiyToDto(order,orderDto);
        return orderDto;
    }



    public boolean update(OrderDto dto, Integer id) {
        Order update = getEntiy(id);
        customerService.get
        return false;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public List<OrderDto> fillAllByPage(Integer page, Integer size) {
        return null;
    }

    private void convertEntitiyToDto(Order order, OrderDto orderDto) {

    }

    private Order getEntiy(Integer id) {
        return null;
    }
}
