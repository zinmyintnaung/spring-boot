package com.jpa.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpa.demo.entity.Course;
import com.jpa.demo.entity.Instructor;
import com.jpa.demo.entity.InstructorDetail;

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
}
