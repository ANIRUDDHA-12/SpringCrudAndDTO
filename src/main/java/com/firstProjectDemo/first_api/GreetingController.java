package com.firstProjectDemo.first_api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @GetMapping("/public/hello")
    public String hello(){
        return "Welcome to the public page";
    }

    @GetMapping("/private/hello")
    public String getHello(){
        return "Welcome to the Vip page";
    }
}
