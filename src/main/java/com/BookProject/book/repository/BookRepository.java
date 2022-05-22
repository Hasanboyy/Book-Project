package com.BookProject.book.repository;

import com.BookProject.book.model.Book;
import com.BookProject.book.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    Optional<Book> findByIdAndDeletedAtIsNull(Integer integer);


}
