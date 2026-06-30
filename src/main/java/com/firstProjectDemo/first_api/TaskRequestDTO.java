package com.firstProjectDemo.first_api;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TaskRequestDTO(
    @Min(1)
    Integer projectId,
    @NotBlank
    String taskName,
    @Min(1)
    double cost,
    @Enumerated(EnumType.STRING)
    TaskForProject.Status status
) {}
