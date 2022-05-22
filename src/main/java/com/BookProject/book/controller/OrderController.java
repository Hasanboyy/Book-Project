package com.BookProject.book.controller;

import com.BookProject.book.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/create")
    public ResponseEntity<?> create(){
        return null;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        return null;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Order order,
                                    @PathVariable("id") Integer id){
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return null;
    }
}
