package com.firstProjectDemo.first_api;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NotBlank
    private  String userName;

    @OneToMany
    private List<CurrencyHolding> currencyHoldingList;

    public UserWallet(String userName) {
        this.userName=userName;
    }
}
