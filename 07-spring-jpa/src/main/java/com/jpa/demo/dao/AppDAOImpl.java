package com.jpa.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpa.demo.entity.Course;
import com.jpa.demo.entity.Instructor;
import com.jpa.demo.entity.InstructorDetail;
import com.jpa.demo.entity.Review;
import com.jpa.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    @Transactional
    public Instructor findInstructorById(Integer id){
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(Integer id){
        //retrieve the instructor first
        Instructor tempInstructor = entityManager.find(Instructor.class, id);

        //find associated course and break the one to many relation
        List<Course> courses = tempInstructor.getCourses();
        for(Course eachCourse: courses){
            eachCourse.setInstructor(null);
        }

        //then delete the instructor using remove()
        entityManager.remove(tempInstructor);
    }

    @Override
    @Transactional
    public InstructorDetail findInstructorDetailById(Integer id){
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(Integer id){
        //find the record to delete
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, id);

        //get instructor then set instructor detail to null, this will remove the link so that delete can only happen on instructor detail
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(Integer id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        //execute the query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                                "select i from Instructor i "
                                                    + "JOIN FETCH i.courses "
                                                    + "JOIN FETCH i.instructorDetail "
                                                    + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    public Course findCourseById(Integer id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    @Transactional
    public void deleteCourseById(Integer id) {
        Course tempCourse = entityManager.find(Course.class, id);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);//this will save course and associated review due to Cascade.ALL
    }

    @Override
    public Course findCourseAndReviewsByCourseId(Integer id) {
        //create query, binding the course and reveiew with JOIN FETCH
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " 
                                                            +"JOIN FETCH c.reviews "
                                                            +"WHERE c.id = :data", Course.class);
        query.setParameter("data", id);

        //execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(Integer id) {
        TypedQuery<Course> query = 
                entityManager.createQuery("select c from Course c " 
                +"JOIN FETCH c.students "
                +"WHERE c.id = :data", Course.class);
        
        query.setParameter("data", id);

        //execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(Integer id) {
        TypedQuery<Student> query = 
                entityManager.createQuery("select s from Student s " 
                +"JOIN FETCH s.courses "
                +"WHERE s.id = :data", Student.class);
        
        query.setParameter("data", id);

        //execute query
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(Integer id) {
        Student tempStudent = entityManager.find(Student.class, id);
        entityManager.remove(tempStudent);
    }
}
