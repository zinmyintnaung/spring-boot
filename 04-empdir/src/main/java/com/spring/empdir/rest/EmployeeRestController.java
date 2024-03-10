package com.spring.empdir.rest;

import org.springframework.web.bind.annotation.RestController;

import com.spring.empdir.entity.Employee;
import com.spring.empdir.service.EmployeeService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    //expose endpoint
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getStudent(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee ID is not found.");
        }

        return theEmployee;
    }

    //Add mapping for POST request
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        //if id is included in JSON, we will set it to zero, coz we are creating new employee here
        theEmployee.setId(0);
        //there is no id so we can save to db and get the inserted employee
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    
    //Add mapping for PUT request
    @PutMapping("/employees")
    public Employee updatEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee == null){
            throw new RuntimeException("Employee Id not found and deleting is impossible.");
        }
        employeeService.deleteById(employeeId);

        return "Employee ID: " + employeeId + " deleted successfully";
    }
    
}
