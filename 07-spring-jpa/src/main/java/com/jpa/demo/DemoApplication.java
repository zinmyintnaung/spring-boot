package com.jpa.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jpa.demo.dao.AppDAO;
import com.jpa.demo.entity.Course;
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
            //deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorWithCourses(appDAO);
            findInstructorWithCoursesJoinFetch(appDAO);
        };
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

    private void findInstructorWithCourses(AppDAO appDAO){
        Integer id = 1;
        System.out.println("Searching..");

        //find the instructor
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println("Instructor found " + tempInstructor);

        //find the associated courses
        List<Course> courses = appDAO.findCoursesByInstructorId(id);

        //bind the instructor and courses
        tempInstructor.setCourses(courses);

        System.out.println("Associated courses: " + tempInstructor.getCourses());
    }

    private void createInstructorWithCourses(AppDAO appDAO){
        //Create instructor first
        Instructor tempInstructor = new Instructor("Fahim", "Ansari", "fa@email.com");
        //Create instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com", "Coding");
        //associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //Create some courses
        Course tempCourseOne = new Course("Heavy Metal - One week crash course!");
        Course tempCourseTwo = new Course("Classical - 30 Days fingerstyle crash course!");
        tempInstructor.add(tempCourseOne);
        tempInstructor.add(tempCourseTwo);

        //Now we can save the instructor
        System.out.println("Saving instructor with courses.." + tempInstructor);
        System.out.println("Adding courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor); //this will also save courses associated with instructor
        System.out.println("Done");
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
