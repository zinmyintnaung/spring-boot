package com.spring.empdir.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.empdir.dao.EmployeeDAO;
import com.spring.empdir.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        this.employeeDAO = theEmployeeDAO;
    }

    @Override
    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int Id) {
        return employeeDAO.findById(Id);
    }

    //We will only use transaction at service layer for best practice
    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }

    //We will only use transaction at service layer for best practice
    @Transactional
    @Override
    public void deleteById(int Id) {
       employeeDAO.deleteById(Id);
    }
}
