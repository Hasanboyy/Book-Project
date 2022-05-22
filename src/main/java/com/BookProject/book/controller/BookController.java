package com.BookProject.book.controller;

import com.BookProject.book.dto.BookDto;
import com.BookProject.book.filter.BookFilter;
import com.BookProject.book.model.Book;
import com.BookProject.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid BookDto bookDto) {
        Book result = bookService.create(bookDto);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {
        BookDto result = bookService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid BookDto bookDto) {
        boolean result = bookService.update(id, bookDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        boolean result = bookService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("p") Integer page,
                                    @RequestParam("s") Integer size) {
        List<BookDto> result = bookService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody BookFilter bookFilter) {
        List<BookDto> result = bookService.filter(bookFilter);
        return ResponseEntity.ok(result);
    }

}
