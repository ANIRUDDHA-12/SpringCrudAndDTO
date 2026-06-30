package com.firstProjectDemo.first_api;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PatientRequestDTO(

        @NotBlank
        String patientName,

        @Min(1)
        int fee,

        @Min(1)
        int doctorId
) {}
