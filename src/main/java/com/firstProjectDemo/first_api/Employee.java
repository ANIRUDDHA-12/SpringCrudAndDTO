//package com.firstProjectDemo.first_api;
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import java.util.List;
//
//@Entity
//public class Employee {
//
//    @Setter
//    @Getter
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Getter
//    @Setter
//    private String name;
//    @Setter
//    @Getter
//    private String department;
//    @Setter
//    @Getter
//    private double salary;
//
//    Employee(){
//
//    }
//    Employee(int id,String department,double salary){
//        this.id=id;
//        this.name=name;
//        this.department=department;
//        this.salary=salary;
//    }
//
//
//    @OneToOne
//    @JoinColumn(name = "address-id")
//    private Address address;
//
//    @ManyToOne
//    @JoinColumn(name="department-id")
//    private Department departmentManyToOne;
//
//
//    @ManyToMany(mappedBy = "missions")
////    @JoinTable(
////            name = "empoloyee-mission",
////            joinColumns = @JoinColumn(name="employee_id"),
////            inverseJoinColumns = @JoinColumn(name = "mission_id")
////    )
//    private List<Mission> missions;
//
//
//}
