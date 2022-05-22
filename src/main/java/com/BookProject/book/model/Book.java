package com.BookProject.book.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter

@Entity
@Table(name = ("books"))
public class Book {
    @Id
    private Integer id;
}
