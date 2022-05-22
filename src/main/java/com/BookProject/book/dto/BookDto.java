package com.BookProject.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    private Integer id;
    @NotBlank(message = "Invalid author")
    private String author;
    @NotBlank(message = "Invalid title")
    private String title;
    private BigDecimal price;
    @NotBlank(message = "Invalid book image")
    private String bookImage;
    private Boolean status;
}
