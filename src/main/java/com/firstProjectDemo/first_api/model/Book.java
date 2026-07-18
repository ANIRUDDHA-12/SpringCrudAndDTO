package com.firstProjectDemo.first_api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String isbn;

    private boolean isAvailable;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL,orphanRemoval = true)
    List<BookLoan> bookLoanListwithBooks;
}
