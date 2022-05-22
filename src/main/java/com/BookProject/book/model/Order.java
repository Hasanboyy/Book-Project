package com.BookProject.book.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = ("orders"))
public class Order {
    @Id
    private Integer id;
    private Integer quality;

    @ManyToOne
    @JoinColumn(name = ("book_id"),insertable = false,updatable = false)
    private Book book;
    @Column(name = ("book_id"))
    private Integer book_id;

    @ManyToOne
    @JoinColumn(name = ("customer_id"),insertable = false,updatable = false)
    private Customer customer;
    @Column(name = ("customer_id"))
    private Integer customer_id;
}
