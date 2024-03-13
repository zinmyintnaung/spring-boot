package com.jpa.demo.dao;

import org.springframework.stereotype.Repository;

import com.jpa.demo.entity.Instructor;

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

}
