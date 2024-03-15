package com.jpa.demo.dao;

import java.util.List;

import com.jpa.demo.entity.Course;
import com.jpa.demo.entity.Instructor;
import com.jpa.demo.entity.InstructorDetail;
import com.jpa.demo.entity.Review;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(Integer id);

    void deleteInstructorById(Integer id);

    InstructorDetail findInstructorDetailById(Integer id);

    void deleteInstructorDetailById(Integer id);

    List<Course> findCoursesByInstructorId(Integer id);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor theInstructor);

    Course findCourseById(Integer id);

    void update(Course theCourse);

    void deleteCourseById(Integer id);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(Integer id);

}
