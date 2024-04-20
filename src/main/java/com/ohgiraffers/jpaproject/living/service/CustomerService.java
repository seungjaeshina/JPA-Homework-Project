package com.ohgiraffers.jpaproject.living.service;



import com.ohgiraffers.jpaproject.living.dto.CategoryDTO;
import com.ohgiraffers.jpaproject.living.dto.CustomerDTO;
import com.ohgiraffers.jpaproject.living.entity.Category;
import com.ohgiraffers.jpaproject.living.entity.Customer;
import com.ohgiraffers.jpaproject.living.repository.CategoryRepository;
import com.ohgiraffers.jpaproject.living.repository.CustomerRepository;
import com.ohgiraffers.jpaproject.living.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public CustomerDTO findCustomerByCustomerCode(int customerCode) {
        Customer foundCustomer = customerRepository.findById(customerCode).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(foundCustomer,CustomerDTO.class);
    }

    public List<CustomerDTO> findCustomerList() {
    List<Customer> customerList = customerRepository.findAll(Sort.by("customerCode").descending());
    return customerList.stream()
            .map(customer -> modelMapper.map(customer,CustomerDTO.class))
            .toList();

    }
    public Page<CustomerDTO> findCustomerList(Pageable pageable){
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1,
                pageable.getPageSize(),
                Sort.by("customerCode").descending()
        );
        Page<Customer> customerList =  customerRepository.findAll(pageable);
        return customerList.map(customer -> modelMapper.map(customer, CustomerDTO.class));
    }

    public List<CustomerDTO> findByCustomerAge(Integer customerAge) {

//        List<Customer> customerList = customerRepository.findByCustomerAgeGreaterThan(customerAge);
//        List<Customer> customerList = customerRepository.findByCustomerAgeGreaterThanOrderByCustomerAge(customerAge);
        List<Customer> customerList = customerRepository.findByCustomerAgeGreaterThan(
                customerAge,
                Sort.by("customerAge").descending()
        );

        return customerList.stream()
                .map(customer -> modelMapper.map(customer,CustomerDTO.class))
                .toList();

    }
    public List<CategoryDTO> findAllCategory() {
        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
    }

    @Transactional
    public void registCustomer(CustomerDTO customerDTO) {

        customerRepository.save(modelMapper.map(customerDTO,Customer.class));
    }

    @Transactional
    public void modifyCustomerInfo(CustomerDTO customerDTO) {
        Customer foundCustomer = customerRepository.findById(customerDTO.getCustomerCode()).orElseThrow(IllegalArgumentException::new);

        foundCustomer.modifyCustomerName(customerDTO.getCustomerName());
        foundCustomer.modifyCustomerGender(customerDTO.getCustomerGender());
        foundCustomer.modifyCustomerAge(customerDTO.getCustomerAge());
        foundCustomer.modifyCategoryNo(customerDTO.getCategoryNo());
        foundCustomer.modifyRoomCode(customerDTO.getRoomCode());

    }

    public void deleteCustomer(Integer customerCode) {
        customerRepository.deleteById(customerCode);
    }
}