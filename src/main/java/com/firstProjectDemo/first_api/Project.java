package com.firstProjectDemo.first_api;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String projectName;
    @Min(1)
    private double allocatedBudget;

    @CreationTimestamp
    @Column(name = "created_At",updatable = false)
    LocalDateTime created_At;

    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updated_At;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    private List<TaskForProject> taskList;
}
