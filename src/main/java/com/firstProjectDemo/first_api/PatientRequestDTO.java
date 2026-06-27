package com.firstProjectDemo.first_api;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;

public record PatientRequestDTO(

        @Column(nullable = false)
        String patientName,

        @Min(1)
        int fee,

        @Min(1)
        int doctorId
) {}
