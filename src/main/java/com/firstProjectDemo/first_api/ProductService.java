package com.firstProjectDemo.first_api;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public String createProduct(ProductRequestDTO dto){
        return"Product"+dto.name()+"saved";
    }
}
