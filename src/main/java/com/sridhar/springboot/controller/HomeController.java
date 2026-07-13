package com.sridhar.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello! Spring Boot application deployed using Jenkins, Docker and AWS EC2.";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is healthy";
    }
}