package com.BookProject.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    private Integer id;
    @NotBlank(message = "Invalid author")
    @Size(min = 8, max = 255)
    private String author;
    @NotBlank(message = "Invalid title")
    @Size(min = 8,max = 255)
    private String title;
    @NotBlank(message = "Invalid price")
    private BigDecimal price;
    @NotBlank(message = "Invalid book image")
    @Size(min = 8,max = 255)
    private String bookImage;
    private Boolean status;


}
