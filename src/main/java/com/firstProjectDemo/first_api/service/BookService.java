package com.firstProjectDemo.first_api.service;

import com.firstProjectDemo.first_api.BookUnavailableException;
import com.firstProjectDemo.first_api.model.Book;
import com.firstProjectDemo.first_api.model.BookLoan;
import com.firstProjectDemo.first_api.model.Member;
import com.firstProjectDemo.first_api.record.BookRequestDTO;
import com.firstProjectDemo.first_api.repository.BookLoanRepository;
import com.firstProjectDemo.first_api.repository.BookRepository;
import com.firstProjectDemo.first_api.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookLoanRepository bookLoanRepository;
    private MemberRepository memberRepository;

    public BookService(BookRepository bookRepository,
                       BookLoanRepository bookLoanRepository,
                       MemberRepository memberRepository
                       ){
        this.bookRepository=bookRepository;
        this.bookLoanRepository=bookLoanRepository;
        this.memberRepository=memberRepository;
    }

    @Transactional
    public void borrowBook(BookRequestDTO bookRequestDTO){
       Book book=bookRepository.findById(bookRequestDTO.bookId())
               .orElseThrow(()->new BookUnavailableException("Book not available"));

        Member member=memberRepository.findById(bookRequestDTO.memberId())
                .orElseThrow(()->new BookUnavailableException("Member not subscribed"));

        if(!book.isAvailable()){
            throw new BookUnavailableException("Not available");
        }
        book.setAvailable(false);
        bookRepository.save(book);

        BookLoan loan=new BookLoan();
        loan.setBook(book);
        loan.setMember(member);
        loan.setBorrowDate(LocalDateTime.now());
        bookLoanRepository.save(loan);
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

}
