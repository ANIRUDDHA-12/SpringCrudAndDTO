package com.firstProjectDemo.first_api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public List<Employee> findByDepartment(String department);
}
