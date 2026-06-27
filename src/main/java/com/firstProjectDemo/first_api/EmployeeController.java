package com.firstProjectDemo.first_api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
     @PostMapping
    public void addEmployees(@RequestBody List<Employee> employee){
        employeeService.addEmployees(employee);
     }


     @DeleteMapping("/{id}")
    public void deleteEmployees(@PathVariable int id){
        employeeService.deleteEmployees(id);
     }

     @GetMapping("/department/{departmentName}")
    public List<Employee> findByDepartment(@PathVariable String departmentName){
        return employeeService.findByDepartment(departmentName);
     }

     @PutMapping("/{id}")
    public Employee updateEmployees(@PathVariable int id,@RequestBody Employee updatedData){
        return employeeService.updateEmployees(id,updatedData);
     }

}
