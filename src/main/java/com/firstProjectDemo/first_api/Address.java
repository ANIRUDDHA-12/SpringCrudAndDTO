package com.firstProjectDemo.first_api;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Integer id;

    private String street;
    private String zipCode;

//    @OneToOne
//    @JoinColumn(name = "employee-id")
//    private Employee employee;
}
