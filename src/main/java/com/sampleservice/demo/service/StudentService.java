package com.sampleservice.demo.service;

import com.sampleservice.demo.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Collection<Student> list();
    Student findByFirstName(String firstName);
    Student findById(Long id);
    Student findByEmail(String email);
    void delete(Student student);
    Student saveOrUpdate(Student student);
    Collection<Student> saveAll(List<Student> students);
}
