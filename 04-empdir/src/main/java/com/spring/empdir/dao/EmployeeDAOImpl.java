package com.spring.empdir.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.empdir.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor autowired
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int Id) {
        return entityManager.find(Employee.class, Id);
    }

    //We will use the same save method for creating new employee and updating existing employee
    //We will set employee id to zero on create method to make sure Id is inserted by MySQL autoincrement
    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee = entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int Id) {
        Employee theEmployee = entityManager.find(Employee.class, Id);
        entityManager.remove(theEmployee);
    }

}
