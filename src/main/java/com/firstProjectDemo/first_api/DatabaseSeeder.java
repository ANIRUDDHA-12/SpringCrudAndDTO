package com.firstProjectDemo.first_api;

import com.firstProjectDemo.first_api.Account;
import com.firstProjectDemo.first_api.Transaction;
import com.firstProjectDemo.first_api.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    // Injecting ONLY the transaction repository
    private final TransactionRepository transactionRepository;

    public DatabaseSeeder(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Safe check using the transaction repo instead
        if (transactionRepository.count() > 0) {
            return;
        }

        // 1. Instantiate the accounts (Do NOT save them yet, let cascade handle it)
        Account mainAccount = Account.builder()
                .accountNumber("ACC-MAIN-123")
                .holderName("Alice Smith")
                .balance(new BigDecimal("50000.0"))
                .build();

        Account bob = Account.builder()
                .accountNumber("ACC-BOB-999")
                .holderName("Bob Jones")
                .balance(new BigDecimal("1000.00"))
                .build();

        Account charlie = Account.builder()
                .accountNumber("ACC-CHAS-888")
                .holderName("Charlie Brown")
                .balance(new BigDecimal("2500.00"))
                .build();

        Account delta = Account.builder()
                .accountNumber("ACC-DELT-777")
                .holderName("Delta Corp")
                .balance(new BigDecimal("150000.00"))
                .build();

        List<Account> counterparties = List.of(bob, charlie, delta);

        // 2. Build the transactions list
        List<Transaction> transactions = new ArrayList<>();
        LocalDateTime baseTime = LocalDateTime.now().minusDays(20);

        for (int i = 1; i <= 18; i++) {
            BigDecimal amount = new BigDecimal(i * 15.50);
            LocalDateTime timestamp = baseTime.plusHours(i * 6);
            Account receiver = counterparties.get(i % counterparties.size());

           transactions.add(Transaction.builder()
                           .amount(amount)
                           .timestamp(timestamp)
                           .fromAccount(mainAccount)
                           .toAccount(receiver)
                   .build());
        }

        // 3. Save all transactions. 
        // Because of cascade = CascadeType.PERSIST on the @ManyToOne fields, 
        // Hibernate will automatically insert the 4 accounts first, then the 18 transactions.
        transactionRepository.saveAll(transactions);

        System.out.println("=== Database seeded using ONLY TransactionRepository! ===");
    }
}