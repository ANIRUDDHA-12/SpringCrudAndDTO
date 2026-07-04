package com.firstProjectDemo.first_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<Bankaccount,Integer> {
}
