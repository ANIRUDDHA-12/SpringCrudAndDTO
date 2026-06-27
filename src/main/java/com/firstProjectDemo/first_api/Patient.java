package com.firstProjectDemo.first_api;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double consultationFee;

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    Doctor doctor;


}
