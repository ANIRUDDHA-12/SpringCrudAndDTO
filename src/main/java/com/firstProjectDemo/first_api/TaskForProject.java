package com.firstProjectDemo.first_api;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskForProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String taskName;

    @Min(1)
    private double cost;



    public enum Status{
        TODO,
        IN_PROGRESS,
        COMPLETED
    }
    @Enumerated(EnumType.STRING)
    private TaskForProject.Status status;


    @ManyToOne
    @JoinColumn(
            name = "project_id",
            nullable=false
    )
    private Project project;
}
