package com.firstProjectDemo.first_api;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdempotencyRecord {

    @EmbeddedId
    private IdempotencyKeyId id;

    @Column(nullable = false)
    private String status;

    @Min(1)
    private BigDecimal resultBalance;

    private LocalDateTime createdAt;




}
