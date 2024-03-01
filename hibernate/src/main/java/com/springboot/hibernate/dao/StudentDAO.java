package com.springboot.hibernate.dao;

import com.springboot.hibernate.entity.Student;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer id);
}
