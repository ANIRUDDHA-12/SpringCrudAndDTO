package com.firstProjectDemo.first_api.model;

import com.firstProjectDemo.first_api.repository.BookLoanRepository;
import com.firstProjectDemo.first_api.repository.BookRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OverdueScanner {
    private final BookLoanRepository bookLoanRepository;

    public OverdueScanner(BookLoanRepository bookLoanRepository){
        this.bookLoanRepository=bookLoanRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void scanOverdue(){
        LocalDateTime localDateTime=LocalDateTime.now().minusDays(14);
        List<BookLoan> bookLoanList=bookLoanRepository.findReturnDateByNull();

        bookLoanList.stream()
                .filter(loan->loan.getBorrowDate().isBefore(localDateTime))
                .forEach(bookLoan -> System.out.printf(
                        "[OVERDUE ALERT] Book ID: %d (\"%s\") borrowed by Member ID: %d is overdue! Borrowed on: %s%n",
                        bookLoan.getBook().getId(),
                        bookLoan.getBook().getTitle(),
                        bookLoan.getBook().getIsbn(),
                        bookLoan.getBorrowDate()
                ));
    }
}
