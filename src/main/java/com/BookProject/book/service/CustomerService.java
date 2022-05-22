package com.BookProject.book.service;

import com.BookProject.book.dto.CustomerDto;
import com.BookProject.book.exeption.BookException;
import com.BookProject.book.filter.CustomerFilter;
import com.BookProject.book.model.Customer;
import com.BookProject.book.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public boolean create(CustomerDto customerDto) {
        Customer customer = new Customer();
        customerDto.setId(customer.getId());
        convertDtoToEntity(customerDto, customer);
        customerRepository.save(customer);
        return true;
    }

    public CustomerDto get(Integer id) {
        Customer customer = getEntity(id);
        CustomerDto customerDto = new CustomerDto();
        convertEntityToDto(customerDto, customer);
        return customerDto;
    }


    public boolean update(Integer id, CustomerDto customerDto) {
        Customer update = getEntity(id);
        update.setName(customerDto.getName());
        update.setSurname(customerDto.getSurname());
        update.setPassword(customerDto.getPassword());
        update.setContact(customerDto.getContact());
        /*update.setEmail(customerDto.getEmail());*/
        update.setUpdatedAt(LocalDateTime.now());
        update.setStatus(true);
        update.setCity(true);
        customerRepository.save(update);
        return true;
    }

    public boolean delete(Integer id) {
        Customer customer = getEntity(id);
        customer.setDeletedAt(LocalDateTime.now());
        customerRepository.save(customer);
        return true;
    }

    public List<CustomerDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> resultPage = customerRepository.findAll(pageable);
        List<CustomerDto> response = new ArrayList<>();
        for (Customer customer : resultPage) {
            if (customer.getDeletedAt() == null){
                CustomerDto dto = new CustomerDto();
                convertEntityToDto(dto, customer);
                response.add(dto);
            }
        }
        return response;
    }

    public List<CustomerDto> filter(CustomerFilter dto) {
        String sortBy = dto.getSortBy();
        if (sortBy == null || sortBy.isEmpty()){
            sortBy = "createdAt";
        }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Customer> specifications = (((root, query, criteriaBuilder) -> {
            if (dto.getName() != null){
                predicateList.add(criteriaBuilder.like(root.get("name"), ("%" + dto.getName() + "%")));
            }
            if (dto.getSurname() != null){
                predicateList.add(criteriaBuilder.like(root.get("surname"), ("%" + dto.getSurname() + "%")));
            }
            if (dto.getDirection() != null){
                predicateList.add(criteriaBuilder.equal(root.get("direction"), dto.getDirec()));
            }
            return criteriaBuilder.and(predicateList.toArray(new javax.persistence.criteria.Predicate[0]));
        }));

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), dto.getDirection(), sortBy);
        List<CustomerDto> resultList = new ArrayList<>();
        Page<Customer> customerPage = customerRepository.findAll(specifications, pageable);
        for (Customer customer : customerPage) {
            if (customer.getDeletedAt() == null){
                CustomerDto customerDto = new CustomerDto();
                convertEntityToDto(customerDto, customer);
                resultList.add(customerDto);
            }
        }
        return resultList;
    }

    public Customer getEntity(Integer id) {
        Optional<Customer> optional = customerRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new BookException("Customer not found");
        }
        return optional.get();
    }

    private void convertDtoToEntity(CustomerDto dto, Customer entity) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPassword(dto.getPassword());
        entity.setContact(dto.getContact());
        /*entity.setEmail(dto.getEmail());*/
        entity.setCity(true);
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
    }

    private void convertEntityToDto(CustomerDto dto, Customer entity) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPassword(entity.getPassword());
        dto.setContact(entity.getContact());
        /*dto.setEmail(entity.getEmail());*/
    }
}
