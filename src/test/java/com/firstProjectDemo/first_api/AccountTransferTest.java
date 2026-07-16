package com.firstProjectDemo.first_api;


import jakarta.persistence.OptimisticLockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class AccountTransferTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private IdempotencyRecordRepository idempotencyRecordRepository;



    @InjectMocks
    private AccountService account;

    private Account fromAccount;
    private Account toAccount;
    private IdempotencyKeyId idempotencyKeyId;


    @BeforeEach
    void setUp(){

        fromAccount=new Account();
        fromAccount.setId(1L);
        fromAccount.setBalance(new BigDecimal("100.00"));
        idempotencyKeyId=new IdempotencyKeyId(1L,"1L");





        toAccount=new Account();
        toAccount.setId(2L);
        toAccount.setBalance(new BigDecimal("90.00"));
//        idempotencyKeyId=new IdempotencyKeyId(2L,"2L");
    }


//    @Test
//    void TransferTest(){
//       Long fromId=1L;
//       Long toId=2L;
//
//       BigDecimal transferAmount=new BigDecimal("30.00");
//       when(accountRepository.findById(fromId)).thenReturn(Optional.of(fromAccount));
//       when(accountRepository.findById(toId)).thenReturn(Optional.of(toAccount));
//
//
//
//       account.transfer(fromId,toId,transferAmount);
//
//       assertEquals(new BigDecimal("70.00"),fromAccount.getBalance());
//       assertEquals(new BigDecimal("120.00"),toAccount.getBalance());
//
//       verify(accountRepository,times(1)).save(fromAccount);
//       verify(accountRepository,times(1)).save(toAccount);
//    }

    @Test
    void transferTest_duplicateRequestId_isNoOp(){
        Long fromId=1L;
        Long toId=2L;
        BigDecimal transfer=new BigDecimal("46.98");
        String requestId="1L";

        IdempotencyRecord record=new IdempotencyRecord()
                .builder()
                .id(idempotencyKeyId)
                .status("SUCCESS")
                .build();

        when(idempotencyRecordRepository.findById(idempotencyKeyId)).thenReturn(Optional.of(record));

        account.transfer(requestId,fromId,toId,transfer);

        verify(idempotencyRecordRepository,never()).save(any());
        verify(idempotencyRecordRepository,never()).saveAndFlush(any());

        verify(accountRepository,never()).findById(any());
        verify(accountRepository,never()).save(any());


    }

    @Test
    void AccountNotFoundTest_ThrowsException(){
        Long fromId=1L;
        Long toId=2L;
        String requestId="2L";

        BigDecimal transferAmount=new BigDecimal("30.00");

        when(accountRepository.findById(fromId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->{
            account.transfer(requestId,fromId,toId,transferAmount);
        });

        verify(accountRepository,times(0)).save(any(Account.class));

    }
}
