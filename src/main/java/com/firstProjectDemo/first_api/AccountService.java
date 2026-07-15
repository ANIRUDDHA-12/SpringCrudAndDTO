package com.firstProjectDemo.first_api;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final IdempotencyRecordRepository idempotencyRecordRepository;
    private final IdempotencyKeyId idempotencyKeyId;

    public AccountService(AccountRepository accountRepository,
                          IdempotencyRecordRepository idempotencyRecordRepository,
                          IdempotencyKeyId idempotencyKeyId

    ){
        this.accountRepository=accountRepository;
        this.idempotencyRecordRepository=idempotencyRecordRepository;
        this.idempotencyKeyId=idempotencyKeyId;
    }

    // if something goes wrong between the in_progress and success ,then the best handling of error would be getting the cause of why and what went wrong which can be confirmed through the try catch block here which would catch the exception if any at all

    @Transactional
    public void transfer(String requestId,Long fromId, Long toId, BigDecimal amount) {

        IdempotencyKeyId keyId = IdempotencyKeyId.builder()
                .accountId(fromId)
                .requestId(requestId)
                .build();

        Optional<IdempotencyRecord> idempotencyRecord = idempotencyRecordRepository.findById(keyId);
        IdempotencyRecord activeRecord;

        if (idempotencyRecord.isPresent()) {
            IdempotencyRecord record = idempotencyRecord.get();
            if ("SUCCESS".equals(record.getStatus())) {
                return;
            }
            if ("IN_PROGRESS".equals(record.getStatus())) {
                boolean isStale = record.getCreatedAt().isBefore(LocalDateTime.now().minusMinutes(15));
                if (!isStale) {
                    throw new IllegalStateException("The transaction is already in progress");

                }
                record.setCreatedAt(LocalDateTime.now());
                idempotencyRecordRepository.saveAndFlush(record);
                activeRecord=record;
            } else {
                record.setStatus("IN_PROGRESS");
                record.setCreatedAt(LocalDateTime.now());
                idempotencyRecordRepository.saveAndFlush(record);
                activeRecord = record;
            }
        }else {
            activeRecord = IdempotencyRecord.builder()
                    .id(keyId)
                    .status("IN_PROGRESS")
                    .createdAt(LocalDateTime.now())
                    .build();
            idempotencyRecordRepository.saveAndFlush(activeRecord);
        }

        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));
        ;
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new IllegalArgumentException("Credit Account not found"));

        if(from.getBalance().compareTo(amount)<0){
            throw new IllegalArgumentException("Insufficient funds");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));


        activeRecord.setStatus("SUCCESS");
        activeRecord.setResultBalance(from.getBalance());
        idempotencyRecordRepository.save(activeRecord);

        accountRepository.save(from);
        accountRepository.save(to);
    }
}
