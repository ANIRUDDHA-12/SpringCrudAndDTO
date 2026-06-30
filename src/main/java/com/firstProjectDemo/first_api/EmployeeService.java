//package com.firstProjectDemo.first_api;
//
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class EmployeeService {
//    private final EmployeeRepository employeeRepository;
//
//    EmployeeService(EmployeeRepository employeeRepository){
//        this.employeeRepository=employeeRepository;
//    }
//
//    public List<Employee> getAllEmployees(){
//        return employeeRepository.findAll();
//    }
//
//    public void addEmployees(List<Employee> newEmployees){
//        employeeRepository.saveAll(newEmployees);
//    }
//
//    public void deleteEmployees(int employeeId){
//        if(!employeeRepository.existsById(employeeId)){
//            throw new RuntimeException("Employee not found");
//        }
//        employeeRepository.deleteById(employeeId);
//    }
//
//    public List<Employee> findByDepartment( String department){
//        return employeeRepository.findByDepartment(department);
//    }
//
//    public Employee updateEmployees(int id,Employee updatedData){
//        Employee existingEmployee = employeeRepository.findById(id)
//                .orElseThrow(()-> new RuntimeException("Employee not found on our records"));
//
//        existingEmployee.setName(updatedData.getName());
//        existingEmployee.setDepartment(updatedData.getDepartment());
//        existingEmployee.setSalary(updatedData.getSalary());
//
//        return employeeRepository.save(existingEmployee);
//    }
//
//}
