package com.springboot.hibernate;

import java.util.List;

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
            //queryForStudents(studentDAO);
            //queryForStudentsByLastName(studentDAO);
            //updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            //deleteAllStudents(studentDAO);
        };
    }

   
    private void updateStudent(StudentDAO studentDAO) {
        //Get student to update
        System.out.println("Getting student..");
        Student myStudent = studentDAO.findById(2);

        //Call update using studentDAO
        myStudent.setFirstName("Jenny");
        studentDAO.update(myStudent);

        //Display the updated studnet
        System.out.println("The updated student is : " + myStudent);
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

    public void queryForStudents(StudentDAO studentDAO){
        //Find all students and create studnets list
        List<Student> theStudents = studentDAO.findAll();
        
        //Loop each student from theStudents List
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        //Find student with given last name
        List<Student> theStudents = studentDAO.findByLastName("doe");

        //Loop result and display
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }

    private void deleteStudent(StudentDAO studentDAO) {
        //Find a student to remove
        Student myStudent = studentDAO.findById(1);
        
        
        if(myStudent != null){
            System.out.println("This student will be removed: " + myStudent);
            //Remove a student
            System.out.println("Removing student..");
            studentDAO.delete(myStudent.getId());

            //Get all remaining student to check
            List<Student> theStudents = studentDAO.findAll();
            //Loop result and display
            for(Student tempStudent : theStudents){
                System.out.println(tempStudent);
            }
        }else{
            System.out.println("Student NOT found!");
        }
        
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        int num = studentDAO.deleteAll();
        System.out.println("Total " + num + " rows deleted!");
    }


}
