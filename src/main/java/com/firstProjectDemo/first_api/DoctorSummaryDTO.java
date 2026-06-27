package com.firstProjectDemo.first_api;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;

public record DoctorSummaryDTO(
        @Min(1)
        int doctorId,
        @Column(nullable = false)
        String doctorName,
        @Column(nullable = false)
        String department,
        @Min(1)
        int patientCount,
        @Min(1)
        double totalRevenue
) {}
