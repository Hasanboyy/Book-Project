package com.BookProject.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    private Integer id;

    @NotBlank(message = "The name cannot empty")
    private String name;

    @NotBlank(message = "The surname cannot empty")
    private String surname;

    private String password;

    @NotBlank(message = "Invalid contact")
    @Size(min = 12, max = 13)
    private String contact;

    @Email(message = "enter the email correctly")
    private Integer email;

    private Boolean city;
    private Boolean status;

}
