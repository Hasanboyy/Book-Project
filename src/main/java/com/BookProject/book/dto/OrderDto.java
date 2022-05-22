package com.BookProject.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private Integer id;
    private BookDto bookDto;
    private Integer bookId;
    private CustomerDto customerDto;
    private Integer customerId;
}
