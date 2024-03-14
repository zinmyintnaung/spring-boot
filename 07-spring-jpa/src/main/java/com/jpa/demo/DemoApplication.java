package com.jpa.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jpa.demo.dao.AppDAO;
import com.jpa.demo.entity.Instructor;
import com.jpa.demo.entity.InstructorDetail;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO){
        return runner-> {
            //createInstructor(appDAO);
            //findInstructor(appDAO);
            //deleteInstructor(appDAO);
            //findInstructorDetail(appDAO);
            deleteInstructorDetail(appDAO);
        };
    }

    private void deleteInstructorDetail(AppDAO appDAO){
        Integer id = 3;
        System.out.println("Deleting.." + id);
        appDAO.deleteInstructorDetailById(id);
        System.out.println("Record deleted.");
    }

    private void findInstructorDetail(AppDAO appDAO){
        Integer id = 1;
        System.out.println("Searching..");
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);
        System.out.println(tempInstructorDetail);
        System.out.println("Printing Details..");
        System.out.println(tempInstructorDetail.getInstructor());
    }

    private void deleteInstructor(AppDAO appDAO){
        Integer id = 2;
        System.out.println("Deleting.." + id);
        appDAO.deleteInstructorById(id);
        System.out.println("Record deleted.");
    }

    private void findInstructor(AppDAO appDAO){
        Integer id = 2;
        System.out.println("Searching..");
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println(tempInstructor); //this also include instructor detail as one of its properties
        System.out.println("Printing Details..");
        System.out.println(tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        //Create instructor first
        Instructor tempInstructor = new Instructor("Fahim", "Ansari", "fa@email.com");
        //Create instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com", "Coding");
 
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //save the instructor now
        System.out.println("Saving instructor :" + tempInstructor);
        //now the DAO will save both instructor and details becuase of @OneToOne(cascade = CascadeType.ALL)
        appDAO.save(tempInstructor);
        System.out.println("Done.");

        //Create instructor first
        Instructor tempInstructor1 = new Instructor("John", "Doe", "dj@email.com");
        //Create instructor detail
        InstructorDetail tempInstructorDetail1 = new InstructorDetail("http://youtube.com", "Travel");

        //associate the objects
        tempInstructor1.setInstructorDetail(tempInstructorDetail1);

        //save the instructor now
        System.out.println("Saving instructor :" + tempInstructor1);
        //now the DAO will save both instructor and details becuase of @OneToOne(cascade = CascadeType.ALL)
        appDAO.save(tempInstructor1);
        System.out.println("Done.");
    }

}
