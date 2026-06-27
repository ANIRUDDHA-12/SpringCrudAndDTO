package com.firstProjectDemo.first_api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<Student> students;

    public StudentService(){
        this.students=new ArrayList<>();
        this.students.add(new Student(1, "Alice", "Java"));
        this.students.add(new Student(2, "Bob", "Spring"));
    }
    public List<Student> getAllStudents(){
       return this.students;
    }
    public void addNewStudent(Student student){
        this.students.add(student);
        System.out.println(student+"was added successfully");
    }
}