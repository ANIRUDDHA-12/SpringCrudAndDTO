package com.firstProjectDemo.first_api;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionSummaryDto(
        Long transactionId,
        BigDecimal amount,
        LocalDateTime timeStamp,
        String counterPartyName
) {
}

