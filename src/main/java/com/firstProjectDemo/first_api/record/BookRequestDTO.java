package com.firstProjectDemo.first_api.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


public record BookRequestDTO(
        @NotNull(message = "member id cannot be blank")
        @Positive
         Integer memberId,

        @NotNull(message = "book id cannot be null")
        @Positive
        Integer bookId
) {}
