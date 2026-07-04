package com.firstProjectDemo.first_api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequestDTO(
        @NotBlank(message = "Name cannot be blank") String name,
        @Positive(message = "Price must be greater than zero") double price
) {
}
