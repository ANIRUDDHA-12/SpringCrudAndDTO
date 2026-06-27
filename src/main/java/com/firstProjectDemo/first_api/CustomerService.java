package com.firstProjectDemo.first_api;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public void addCustomers(List<Customer> customerList){
        customerRepository.saveAll(customerList);
    }
}
