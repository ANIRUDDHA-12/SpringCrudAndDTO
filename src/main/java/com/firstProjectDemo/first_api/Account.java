package com.firstProjectDemo.first_api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String holderName;

    @Min(1)
    private BigDecimal balance;


    @OneToMany(mappedBy = "fromAccount",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "toAccount",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> recievedTransaction;

    @Version
    private Long version;



}
