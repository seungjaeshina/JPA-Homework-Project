package com.ohgiraffers.jpaproject.living.repository;

import com.ohgiraffers.jpaproject.living.entity.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByCustomerAgeGreaterThan(Integer customerAge);
    List<Customer> findByCustomerAgeGreaterThanOrderByCustomerAge(Integer customerAge);
    List<Customer> findByCustomerAgeGreaterThan(Integer customerAge, Sort sort);





}
