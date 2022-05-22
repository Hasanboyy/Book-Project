package com.BookProject.book.service;

import com.BookProject.book.dto.BookDto;
import com.BookProject.book.exeption.BookException;
import com.BookProject.book.filter.BookFilter;
import com.BookProject.book.model.Book;
import com.BookProject.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public boolean create(BookDto bookDto){
        Book book=new Book();
        bookDto.setId(book.getId());
        convertDtoToEntity(bookDto, book);
        bookRepository.save(book);
        return true;
    }

    public BookDto get(Integer id){
        Book book=getEntity(id);
        BookDto bookDto=new BookDto();
        convertEntityToDto(bookDto,book);
        return bookDto;
    }
    public boolean update(Integer id, BookDto bookDto){
        Book old=getEntity(id);
        old.setAuthor(bookDto.getAuthor());
        old.setTitle(bookDto.getTitle());
        old.setBookImage(bookDto.getBookImage());
        old.setPrice(bookDto.getPrice());
        old.setStatus(true);
        old.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(old);
        return true;
    }
    public boolean delete(Integer id){
        Book book=getEntity(id);
        book.setDeletedAt(LocalDateTime.now());
        bookRepository.delete(book);
        return true;
    }

    public List<BookDto>findAllByPage(Integer page, Integer size){
        Pageable pageable= (Pageable) PageRequest.of(page,size);
        Page<Book> resultPage=bookRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        List<BookDto>response=new ArrayList<>();
        for (Book book:resultPage) {
            if (book.getDeletedAt()==null){
                BookDto bookDto=new BookDto();
                convertEntityToDto(bookDto,book);
                response.add(bookDto);
            }
        }
        return response;
    }
    public List<BookDto> filter(BookFilter bookFilter){
        String sortBy = bookFilter.getSortBy();
        if (sortBy==null || sortBy.isEmpty()){
            sortBy="createdAt";
        }

        List<Predicate> predicateList=new ArrayList<>();
        Specification<Book> specification =(((root, query, criteriaBuilder) -> {
            if (bookFilter.getAuthor() != null){
                predicateList.add((Predicate) criteriaBuilder.like(root.get("author"), ("%"+ bookFilter.getAuthor()+"%")));
            }
            if (bookFilter.getTitle() != null){
                predicateList.add((Predicate) criteriaBuilder.like(root.get("title"), ("%"+ bookFilter.getTitle()+"%")));
            }
            if (bookFilter.getPrice() != null){
                predicateList.add((Predicate) criteriaBuilder.like(root.get("price"), ("%"+ bookFilter.getPrice()+"%")));
            }
            if (bookFilter.getBookImage() != null){
                predicateList.add((Predicate) criteriaBuilder.like(root.get("book image"), ("%"+ bookFilter.getBookImage()+"%")));
            }
            return criteriaBuilder.and(predicateList.toArray(new javax.persistence.criteria.Predicate[0]));

        }));

        Pageable pageable = (Pageable) PageRequest.of(bookFilter.getPage(),bookFilter.getSize(),bookFilter.getDirection(),sortBy);
        List<BookDto> resultList=new ArrayList<>();
        Page<Book>bookPage=bookRepository.findAll(specification, (org.springframework.data.domain.Pageable) pageable);
        for (Book book: bookPage){
            if (book.getDeletedAt()==null){
                BookDto bookDto=new BookDto();
                convertEntityToDto(bookDto, book);
                resultList.add(bookDto);
            }
        }
        return resultList;
    }

    public Book getEntity(Integer id){
        Optional<Book>optional=bookRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw  new BookException("Book not found");
        }
        return optional.get();
    }

    public void convertDtoToEntity(BookDto bookDto, Book entity){
        entity.setAuthor(bookDto.getAuthor());
        entity.setTitle(bookDto.getTitle());
        entity.setPrice(bookDto.getPrice());
        entity.setBookImage(bookDto.getBookImage());
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
    }

    public void convertEntityToDto(BookDto bookDto, Book book){
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setBookImage(book.getBookImage());
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());
    }

}
