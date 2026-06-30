package com.firstProjectDemo.first_api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProjectSummaryDTO(

        @Min(1)
        Integer projectId,

        @NotBlank
        String projectName,

        @Min(1)
        double allocatedBudget,

        @Min(1)
        double totalCostOfTasks,

        @Min(1)
        double remainingBudget

) {
}
