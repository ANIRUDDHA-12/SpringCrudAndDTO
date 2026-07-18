package com.firstProjectDemo.first_api.repository;

import com.firstProjectDemo.first_api.model.Book;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
