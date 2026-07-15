package com.firstProjectDemo.first_api;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionSummaryProjection{
    Long getTransactionId();
    BigDecimal getAmount();
    LocalDateTime getTimeStamp();
    String getCounterPartyName();
}
