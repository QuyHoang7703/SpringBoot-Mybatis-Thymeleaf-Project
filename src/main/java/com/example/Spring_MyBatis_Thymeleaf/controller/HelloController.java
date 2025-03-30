package com.example.Spring_MyBatis_Thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping
    public String helloWorld() {
        return "hello-world";
    }

}
