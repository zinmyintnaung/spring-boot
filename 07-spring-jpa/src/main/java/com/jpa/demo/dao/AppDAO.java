package com.jpa.demo.dao;

import com.jpa.demo.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(Integer id);
}
