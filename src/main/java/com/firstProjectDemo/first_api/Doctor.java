package com.firstProjectDemo.first_api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String department;

    @OneToMany(mappedBy = "patient_id", cascade=CascadeType.ALL)
    List<Patient> patientList;
}
