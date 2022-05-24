package com.BookProject.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private Integer id;
    @NotNull
    private Integer quality;
    private BookDto bookDto;
    @NotNull
    private Integer bookId;
    private CustomerDto customerDto;
    @NotNull
    private Integer customerId;
}
