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
    public CommandLineRunner commandLineRunner(AppDAO appDao){
        return runner-> {
            createInstructor(appDao);
        };
    }

    private void createInstructor(AppDAO appDAO) {
        /* //Create instructor first
        Instructor tempInstructor = new Instructor("Fahim", "Ansari", "fa@email.com");
        //Create instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com", "Coding");
 */
        //Create instructor first
        Instructor tempInstructor = new Instructor("John", "Doe", "dj@email.com");
        //Create instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com", "Travel");

        //associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //save the instructor now
        System.out.println("Saving instructor :" + tempInstructor);
        //now the DAO will save both instructor and details becuase of @OneToOne(cascade = CascadeType.ALL)
        appDAO.save(tempInstructor);
        System.out.println("Done.");
    }

}
