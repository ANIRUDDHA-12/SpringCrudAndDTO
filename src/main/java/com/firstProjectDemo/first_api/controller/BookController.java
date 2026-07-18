package com.firstProjectDemo.first_api.controller;

import com.firstProjectDemo.first_api.AppUser;
import com.firstProjectDemo.first_api.model.Book;
import com.firstProjectDemo.first_api.record.BookRequestDTO;
import com.firstProjectDemo.first_api.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @PreAuthorize("Admin")
    @PostMapping("/lend/books")
    public ResponseEntity<String> borrowBook(@Valid @RequestBody BookRequestDTO bookRequestDTO){
        bookService.borrowBook(bookRequestDTO);
        return ResponseEntity.ok("Book borrowed successfully ");
    }

    @GetMapping("/books")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }


}
