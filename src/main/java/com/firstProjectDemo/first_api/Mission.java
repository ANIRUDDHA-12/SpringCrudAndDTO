package com.firstProjectDemo.first_api;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity
public class Mission {
    private Integer id;
    private String name;
    private int duration;


    @ManyToMany(mappedBy = "missions")
    private List<Employee>employeeList;

}
