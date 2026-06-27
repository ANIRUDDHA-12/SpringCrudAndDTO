package com.firstProjectDemo.first_api;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @PostMapping
    public void addCustomers( @RequestBody List<Customer> customerList){
        customerService.addCustomers(customerList);
    }
}
