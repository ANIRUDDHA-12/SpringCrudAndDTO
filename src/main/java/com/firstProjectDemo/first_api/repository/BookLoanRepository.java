package com.firstProjectDemo.first_api.repository;

import com.firstProjectDemo.first_api.model.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan,Integer> {

    List<BookLoan> findReturnDateByNull();
}
