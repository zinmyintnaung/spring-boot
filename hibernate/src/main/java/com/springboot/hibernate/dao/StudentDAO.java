package com.springboot.hibernate.dao;

import java.util.List;

import com.springboot.hibernate.entity.Student;

public interface StudentDAO {
    //Create new student
    void save(Student theStudent);
    
    //Find a student by id
    Student findById(Integer id);
    
    //Find all students
    List<Student> findAll();

    //Find student by last name
    List<Student> findByLastName(String theLastName);
    
    //Update a student
    void update(Student theStudent);

    //Delete a student
    void delete(Integer id);

    //Delete all records
    int deleteAll();
}
