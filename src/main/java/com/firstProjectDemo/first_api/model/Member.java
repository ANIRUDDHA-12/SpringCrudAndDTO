package com.firstProjectDemo.first_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String memberShipType;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    List<BookLoan> bookLoanList;
}
