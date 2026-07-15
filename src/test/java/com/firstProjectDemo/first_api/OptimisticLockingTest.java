package com.firstProjectDemo.first_api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OptimisticLockingTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void concurrentUpdate_shouldThrowOptimisticLockException(){
        Long accountId=1L;

        Account copyA=accountRepository.findById(accountId).orElseThrow();
        Account copyB=accountRepository.findById(accountId).orElseThrow();

        copyA.setBalance(copyA.getBalance().subtract(new BigDecimal("100")));
        copyB.setBalance(copyB.getBalance().subtract(new BigDecimal("50")));

        accountRepository.saveAndFlush(copyA);

        assertThrows(ObjectOptimisticLockingFailureException.class,()->{
            accountRepository.saveAndFlush(copyB);
        });

    }
}
