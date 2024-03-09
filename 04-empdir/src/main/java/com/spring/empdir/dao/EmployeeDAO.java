package com.spring.empdir.dao;


import java.util.List;

import com.spring.empdir.entity.Employee;

public interface EmployeeDAO {
     //Find all employees
     List<Employee> findAll();

     //find employee by ID
     Employee findById(int Id);

     //create or update employee
     Employee save(Employee theEmployee);
     
     //delete employee
     void deleteById(int Id);

}
