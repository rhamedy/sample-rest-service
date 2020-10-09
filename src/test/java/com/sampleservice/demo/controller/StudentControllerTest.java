package com.sampleservice.demo.controller;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.when;

import com.sampleservice.demo.dto.inbound.StudentInDTO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.service.StudentServiceImpl;

import java.util.ArrayList;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {StudentController.class})
@RunWith(SpringRunner.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentServiceImpl studentServiceImpl;

    @Test
    public void testConstructor() {
        // Arrange
        Assert.fail("This is a boilerplate test. Please fix.");

        // Act and Assert
        Assert.assertNotNull("This is a boilerplate assert on the result.", new StudentController());
    }

    @Test
    public void testGetById() throws Exception {
        // Arrange
        Student student = new Student();
        student.setLastName("Doe");
        student.setEmail("email");
        student.setKlass(1);
        student.setId(123L);
        student.setFirstName("Jane");
        when(this.studentServiceImpl.findById(or(isA(Long.class), isNull()))).thenReturn(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/{sid}", 1L);

        // Act
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

        // Assert
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
        ResultActions resultActions1 = resultActions
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
        Matcher<String> matcher = Matchers
                .containsString("{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"klass\":1}");
        resultActions1.andExpect(MockMvcResultMatchers.content().string(matcher));
    }

    @Test
    public void testList() throws Exception {
        // Arrange
        Student student = new Student();
        student.setLastName("Doe");
        student.setEmail("favicon.ico");
        student.setKlass(0);
        student.setId(123L);
        student.setFirstName("Jane");
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(student);
        when(this.studentServiceImpl.list()).thenReturn(studentList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/list");

        // Act
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

        // Assert
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
        ResultActions resultActions1 = resultActions
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
        Matcher<String> matcher = Matchers
                .containsString("[{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"klass\":0}]");
        resultActions1.andExpect(MockMvcResultMatchers.content().string(matcher));
    }

    @Test
    public void testList2() throws Exception {
        // Arrange
        when(this.studentServiceImpl.list()).thenReturn(new ArrayList<Student>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/list");

        // Act
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

        // Assert
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
        ResultActions resultActions1 = resultActions
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
        Matcher<String> matcher = Matchers.containsString("[]");
        resultActions1.andExpect(MockMvcResultMatchers.content().string(matcher));
    }

    @Test
    public void testList3() throws Exception {
        // Arrange
        Student student = new Student();
        student.setLastName("Doe");
        student.setEmail("favicon.ico");
        student.setKlass(0);
        student.setId(123L);
        student.setFirstName("Jane");
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(student);
        Student student1 = new Student();
        student1.setLastName("Doe");
        student1.setEmail("favicon.ico");
        student1.setKlass(0);
        student1.setId(123L);
        student1.setFirstName("Jane");
        studentList.add(student1);
        when(this.studentServiceImpl.list()).thenReturn(studentList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/list");

        // Act
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

        // Assert
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
        ResultActions resultActions1 = resultActions
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
        Matcher<String> matcher = Matchers.containsString(
                "[{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"klass\":0},{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe"
                        + "\",\"klass\":0}]");
        resultActions1.andExpect(MockMvcResultMatchers.content().string(matcher));
    }

    @Test
    public void testDelete() throws Exception {
        // Arrange
        Student student = new Student();
        student.setLastName("Doe");
        student.setEmail("email");
        student.setKlass(1);
        student.setId(123L);
        student.setFirstName("Jane");
        when(this.studentServiceImpl.findById(or(isA(Long.class), isNull()))).thenReturn(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/students/{sid}", 1L);

        // Act
        ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/students");

        // Act
        ResultActions actualPerformResult = this.mockMvc
                .perform(getResult.param("dto", String.valueOf(new StudentInDTO())));

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}

