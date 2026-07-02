package com.firstProjectDemo.first_api;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyHolding {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String currencyCode;

    @Min(1)
    private double amount;

    @ManyToOne
    private UserWallet userWallet;

    public CurrencyHolding(String currencyCode, double amount, UserWallet userWallet) {
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.userWallet = userWallet;
    }
}
