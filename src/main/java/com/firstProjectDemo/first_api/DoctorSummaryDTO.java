package com.firstProjectDemo.first_api;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record DoctorSummaryDTO(
        @Min(1)
        int doctorId,
        @NotBlank
        String doctorName,
        @NotBlank
        String department,
        @Min(1)
        int patientCount,
        @Min(1)
        double totalRevenue
) {}
