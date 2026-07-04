package com.firstProjectDemo.first_api;

import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository=bankAccountRepository;
    }
    public Bankaccount withDrawAmount(Integer id,double amount){
        Bankaccount bankaccount=bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account not found"));

        if(amount>bankaccount.getBalance()){
            throw new IllegalArgumentException("Insufficient funds!");
        }
        bankaccount.setBalance(bankaccount.getBalance()-amount);
       return bankAccountRepository.save(bankaccount);
    }

    public void closeAccount(Integer id){
        Bankaccount bankaccount=bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account not found"));

        if(bankaccount.getBalance()!=0.0){
            throw new IllegalStateException("Balance must be zero to close account.");
        }
        bankAccountRepository.delete(bankaccount);
    }
}
