package com.BookProject.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    private Integer id;

    private String name;

    private String surname;

    private String password;

    private String contact;

    private Boolean city;

    private Integer email;

}
