package com.sampleservice.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.when;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = {StudentServiceImpl.class, StudentDAO.class, StudentValidator.class})
@RunWith(SpringRunner.class)
public class StudentServiceImplTest {
    @MockBean
    private StudentDAO studentDAO;
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @MockBean
    private StudentValidator studentValidator;

    @Test
    public void testList() {
        // Arrange
        when(this.studentDAO.findAll()).thenReturn(new ArrayList<Student>());

        // Act and Assert
        assertEquals(0, this.studentServiceImpl.list().size());
    }

    @Test
    public void testFindByFirstName() {
        // Arrange
        Student student = new Student();
        student.setLastName("Doe");
        student.setEmail("email");
        student.setKlass(1);
        student.setId(123L);
        student.setFirstName("Jane");
        when(this.studentDAO.findByFirstNameLike(or(isA(String.class), isNull())))
                .thenReturn(Optional.<Student>of(student));

        // Act and Assert
        assertSame(student, this.studentServiceImpl.findByFirstName("Jane"));
    }

    @Test
    public void testFindById() {
        // Arrange
        Student student = new Student();
        student.setLastName("Doe");
        student.setEmail("email");
        student.setKlass(1);
        student.setId(123L);
        student.setFirstName("Jane");
        when(this.studentDAO.findById(or(isA(Long.class), isNull()))).thenReturn(Optional.<Student>of(student));

        // Act and Assert
        assertSame(student, this.studentServiceImpl.findById(123L));
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        Student student = new Student();
        student.setLastName("Doe");
        student.setEmail("email");
        student.setKlass(1);
        student.setId(123L);
        student.setFirstName("Jane");
        when(this.studentDAO.findByFirstNameLike(or(isA(String.class), isNull())))
                .thenReturn(Optional.<Student>of(student));

        // Act and Assert
        assertSame(student, this.studentServiceImpl.findByEmail("email"));
    }

    @Test
    public void testDelete() {
        // Arrange
        Assert.fail("This is a boilerplate test. Please fix.");
        StudentServiceImpl studentServiceImpl = null;

        // Act
        studentServiceImpl.delete(null);
    }

    @Test
    public void testSaveOrUpdate() {
        // Arrange
        Assert.fail("This is a boilerplate test. Please fix.");
        StudentServiceImpl studentServiceImpl = null;

        // Act and Assert
        Assert.assertNotNull("This is a boilerplate assert on the result.", studentServiceImpl.saveOrUpdate(null));
    }
}

