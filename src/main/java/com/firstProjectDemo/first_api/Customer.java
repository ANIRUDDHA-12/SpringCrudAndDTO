package com.firstProjectDemo.first_api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order> orders;
}
