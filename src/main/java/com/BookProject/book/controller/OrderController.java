package com.BookProject.book.controller;

import com.BookProject.book.dto.OrderDto;
import com.BookProject.book.model.Order;
import com.BookProject.book.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid OrderDto order) {
        boolean result = orderService.create(order);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {
        OrderDto result = orderService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid OrderDto order,
                                    @PathVariable("id") Integer id) {
        boolean result = orderService.update(order, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        boolean result = orderService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> pagenation(@RequestParam("s") Integer size,
                                        @RequestParam("p") Integer page){
        List<OrderDto> result = orderService.fillAllByPage(page,size);
        return ResponseEntity.ok(result);
    }
}
