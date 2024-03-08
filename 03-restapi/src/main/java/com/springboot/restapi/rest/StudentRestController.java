package com.springboot.restapi.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.restapi.entity.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")

public class StudentRestController {

    private List<Student> theStudents;

    //we can use @PostConstruct to load the data, this only execute once after construtor is called
    @PostConstruct
    public void loadStudents(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("John", "Doe"));
        theStudents.add(new Student("Jane", "Moe"));
        theStudents.add(new Student("Kane", "Soe"));
    }

    //get all student end-point
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    //get single student by index of students array list
    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        //check studentId against the array size in case item may not be in the list
        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Studnet ID  " + studentId + " not found.");
        }
        return theStudents.get(studentId);
    }

    
    
}
