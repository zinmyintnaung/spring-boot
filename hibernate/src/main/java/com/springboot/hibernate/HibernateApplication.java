package com.springboot.hibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.hibernate.dao.StudentDAO;
import com.springboot.hibernate.entity.Student;

@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner -> {
            //createStudent(studentDAO);
            //createMultipleStudents(studentDAO);
            readStudent(studentDAO);
        };
    }

    private void readStudent(StudentDAO studentDAO) {
        //create student object
        System.out.println("Creating new student object..");
        Student newStudent = new Student("john", "doe", "john@email.com");
        
        //save student object
        System.out.println("Saving new student to database..");
        studentDAO.save(newStudent);

        //display id of the created student object
        int theId = newStudent.getId();
        System.out.println("Saved Student. Generate id: " + theId);

        //retrieving student with id
        Student studentResult = studentDAO.findById(theId);
        System.out.println("Found the student: " + studentResult);
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        //create multiple students
        System.out.println("Creating 3 student objects..");
        Student newStudent1 = new Student("jane", "soe", "jane@email.com");
        Student newStudent2 = new Student("paul", "moe", "paul@email.com");
        Student newStudent3 = new Student("betty", "coe", "betty@email.com");

        //save multiple student using DAO
        System.out.println("Saving 3 students to database..");
        studentDAO.save(newStudent1);
        studentDAO.save(newStudent2);
        studentDAO.save(newStudent3);
    }

    private void createStudent(StudentDAO studentDAO) {
        //create student object
        System.out.println("Creating new student object..");
        Student newStudent = new Student("john", "doe", "john@email.com");
        
        //save student object
        System.out.println("Saving new student to database..");
        studentDAO.save(newStudent);

        //display id of the created student object
        System.out.println("Saved Student. Generate id: " + newStudent.getId());
    }

}
