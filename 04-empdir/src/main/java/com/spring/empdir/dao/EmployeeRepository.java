package com.spring.empdir.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.empdir.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    //no need to write implementation class, we will get crud from JpaRepository
}
