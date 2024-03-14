package com.jpa.demo.dao;

import com.jpa.demo.entity.Instructor;
import com.jpa.demo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(Integer id);
    void deleteInstructorById(Integer id);
    InstructorDetail findInstructorDetailById(Integer id);
    void deleteInstructorDetailById(Integer id);
}
