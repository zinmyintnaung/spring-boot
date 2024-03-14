package com.jpa.demo.dao;

import org.springframework.stereotype.Repository;

import com.jpa.demo.entity.Instructor;
import com.jpa.demo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
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
}
