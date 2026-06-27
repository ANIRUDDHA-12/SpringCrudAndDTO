package com.firstProjectDemo.first_api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record  OrderRequestDTO(
//         @NotBlank(message = "Customer email is required")
//         @Email(message = "Please enter a valid email id")
        @Min(1)
         int customerId,

         @NotBlank(message = "Product name is required")
         String productName,

         @Min(value=1,message = "quantity should be least one")
         int quantity,

         @Positive(message = "value should be greater than 0 here")
         int pricePerItem) {

}
