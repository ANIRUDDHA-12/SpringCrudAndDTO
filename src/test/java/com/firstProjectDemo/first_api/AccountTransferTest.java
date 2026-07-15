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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class AccountTransferTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService account;

    private Account fromAccount;
    private Account toAccount;

    @BeforeEach
    void setUp(){
        fromAccount=new Account();
        fromAccount.setId(1L);
        fromAccount.setBalance(new BigDecimal("100.00"));

        toAccount=new Account();
        toAccount.setId(2L);
        toAccount.setBalance(new BigDecimal("90.00"));
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
    void AccountNotFoundTest_ThrowsException(){
        Long fromId=1L;
        Long toId=2L;

        BigDecimal transferAmount=new BigDecimal("30.00");

        when(accountRepository.findById(fromId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->{
//            account.transfer(fromId,toId,transferAmount);
        });

        verify(accountRepository,times(0)).save(any(Account.class));

    }
}
