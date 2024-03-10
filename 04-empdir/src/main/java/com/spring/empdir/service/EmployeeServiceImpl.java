package com.spring.empdir.service;

import java.util.List;
import java.util.Optional;

//import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.spring.empdir.dao.EmployeeDAO;
import com.spring.empdir.dao.EmployeeRepository;
import com.spring.empdir.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    //With JpaRepository, we won't be using DAO way
    //private EmployeeDAO employeeDAO;

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        this.employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int Id) {
        //JpaRepository use Optional incase the value is not found and make sure to return something
        //Using result.isPresent() and result.get() is the basic usage to handle this kind of situation
        Optional<Employee> result = employeeRepository.findById(Id);
        Employee theEmployee = null;

        if(result.isPresent()){
            theEmployee = result.get();
        }else{
            throw new RuntimeException("Could not find employee ID: " + Id);
        }

        return theEmployee;
    }

    //We will only use transaction at service layer for best practice
    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    //We will only use transaction at service layer for best practice
    @Transactional
    @Override
    public void deleteById(int Id) {
        employeeRepository.deleteById(Id);
    }
}
