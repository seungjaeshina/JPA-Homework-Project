package com.ohgiraffers.jpaproject.living.customer.controller;


import com.ohgiraffers.jpaproject.living.common.Pagenation;
import com.ohgiraffers.jpaproject.living.common.PagingButton;
import com.ohgiraffers.jpaproject.living.dto.CategoryDTO;
import com.ohgiraffers.jpaproject.living.dto.CustomerDTO;
import com.ohgiraffers.jpaproject.living.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.awt.*;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{customerCode}")
    public String findCustomerByCustomerCode(@PathVariable int customerCode, Model model) {

        CustomerDTO resultCustomer = customerService.findCustomerByCustomerCode(customerCode);
        model.addAttribute("customer", resultCustomer);

        return "customer/detail";
    }

    @GetMapping("/list")
    public String findCustomerList(Model model, @PageableDefault Pageable pageable) {
//        List<CustomerDTO> customerList = customerService.findCustomerList();
//        model.addAttribute("customerList", customerList);


        log.info("pageable: {}", pageable);

        Page<CustomerDTO> customerList = customerService.findCustomerList(pageable);
        log.info("{}", customerList.getContent());
        log.info("{}", customerList.getTotalPages());
        log.info("{}", customerList.getTotalElements());
        log.info("{}", customerList.getSize());
        log.info("{}", customerList.getNumberOfElements());
        log.info("{}", customerList.isFirst());
        log.info("{}", customerList.isLast());
        log.info("{}", customerList.getSort());
        log.info("{}", customerList.getNumber());

        PagingButton paging = Pagenation.getPagingButtonInfo(customerList);

        model.addAttribute("customerList", customerList);
        model.addAttribute("paging", paging);

        return "customer/list";
    }

    @GetMapping("/querymethod")
    public void querymethodPage() {
    }


    @GetMapping("/search")
    public String findByCustomerAge(@RequestParam Integer customerAge, Model model) {
        List<CustomerDTO> customerList = customerService.findByCustomerAge(customerAge);

        model.addAttribute("customerList", customerList);


        return "customer/searchResult";
    }

    @GetMapping("/regist")
    public void registPage() {
    }

    @GetMapping("/category")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return customerService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registNewMenu(@ModelAttribute CustomerDTO customerDTO) {
        customerService.registCustomer(customerDTO);
        return "redirect:/customer/list";
    }

    @GetMapping("/modify")
    public void modifyPage() {
    }

    @PostMapping("/modify")
    public String modifyCustomer(@ModelAttribute CustomerDTO customerDTO) {
        customerService.modifyCustomerInfo(customerDTO);
        return "redirect:/customer/" + customerDTO.getCustomerCode();
    }

    @GetMapping("/delete")
    public void deletePage() {
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam Integer customerCode){
        customerService.deleteCustomer(customerCode);
        return "redirect:/customer/list";
    }

}
