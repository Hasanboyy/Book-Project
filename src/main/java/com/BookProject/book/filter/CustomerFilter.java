package com.BookProject.book.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFilter extends FilterDto{
    private String name;
    private String surname;

}
