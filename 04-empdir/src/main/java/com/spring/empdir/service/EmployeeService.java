package com.spring.empdir.service;

import java.util.List;

import com.spring.empdir.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();

    //find employee by ID
    Employee findById(int Id);

    //create or update employee
    Employee save(Employee theEmployee);
    
    //delete employee
    void deleteById(int Id);
}
