package com.firstProjectDemo.first_api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankAccountService bankAccountService;

    @Test
    public void createAccount(){
        Bankaccount bankaccount=new Bankaccount(1,100);
        when(bankAccountRepository.findById(1)).thenReturn(Optional.of(bankaccount));
        when(bankAccountRepository.save(any(Bankaccount.class))).thenReturn((bankaccount));

        Bankaccount withdraw= bankAccountService.withDrawAmount(1,40.0);

        assertEquals(60.0,withdraw.getBalance(),"This should give back the remaining balance");
    }

    @Test
    public void insufficientFunds(){
        Bankaccount bankaccount=new Bankaccount(1,20.0);
        when(bankAccountRepository.findById(1)).thenReturn(Optional.of(bankaccount));

        assertThrows(IllegalArgumentException.class,()->{
           bankAccountService.withDrawAmount(1,40.0);
        });
    }

    @Test
    public void clearBankAccount(){
        Bankaccount bankaccount=new Bankaccount(2,0.0);
        when(bankAccountRepository.findById(2)).thenReturn(Optional.of(bankaccount));
        bankAccountService.closeAccount(2);
        verify(bankAccountRepository,times(1)).delete(bankaccount);
        clearInvocations(bankAccountRepository);

        Bankaccount bankaccount1=new Bankaccount(3,50.0);
        when(bankAccountRepository.findById(3)).thenReturn(Optional.of(bankaccount1));
        assertThrows(IllegalStateException.class,()->{
           bankAccountService.closeAccount(3);
        });
        verify(bankAccountRepository,never()).delete(any(Bankaccount.class));
    }


}
