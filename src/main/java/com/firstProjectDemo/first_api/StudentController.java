package com.firstProjectDemo.first_api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public String getSingleStudent(@PathVariable int studentId){
        return "Your requested to find the student with the id : "+studentId;
    }

    @PostMapping
    public void registerStudent( @RequestBody Student student){
        studentService.addNewStudent(student);
    }



}