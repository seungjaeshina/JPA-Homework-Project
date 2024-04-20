package com.ohgiraffers.jpaproject.living.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LivingController {

    @GetMapping(value = {"/", "/main"})
    public String mainPage(){
        return "main/main";
    }
}
