package com.firstProjectDemo.first_api;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class IdempotencyKeyId implements Serializable {
    private Long accountId;
    private String requestId;

}
