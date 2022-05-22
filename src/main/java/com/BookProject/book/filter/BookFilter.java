package com.BookProject.book.filter;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookFilter extends  FilterDto{
    private String author;
    private String title;
    private BigDecimal price;
    private String bookImage;

}
