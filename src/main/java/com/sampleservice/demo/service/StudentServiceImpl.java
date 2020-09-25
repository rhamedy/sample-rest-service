package com.sampleservice.demo.service;

import com.sampleservice.demo.validator.StudentValidator;
import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private StudentValidator studentValidator;

    @Transactional(readOnly = true)
    @Override
    public Collection<Student> list() {
        return StreamSupport.stream(studentDAO.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    @Override
    public Student findByFirstName(String firstName) {
        Optional<Student> student = studentDAO.findByFirstNameLike(firstName);

        studentValidator.validate404(student, "First Name", firstName);

        return student.get();
    }

    @Transactional(readOnly = true)
    @Override
    public Student findById(Long id) {
        Optional<Student> student = studentDAO.findById(id);

        studentValidator.validate404(student, "id", String.valueOf(id));

        return student.get();
    }

    @Transactional(readOnly = true)
    @Override
    public Student findByEmail(String email) {
        Optional<Student> student = studentDAO.findByFirstNameLike(email);

        studentValidator.validate404(student, "email", email);

       return student.get();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Student student) {
        studentDAO.delete(student);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Student saveOrUpdate(Student student) {
        return studentDAO.save(student);
    }
}
