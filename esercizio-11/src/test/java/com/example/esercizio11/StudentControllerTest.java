package com.example.esercizio11;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.esercizio11.controllers.StudentController;
import com.example.esercizio11.entities.Student;
import com.example.esercizio11.servicies.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.List;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void contextLoads() {
        assertThat(studentController).isNotNull();
    }

    @Test
    void createStudent() throws Exception {
        Student student = new Student();
        student.setName("Antonio");
        student.setSurname("Troiano");
        student.setWorking(false);

        String studentJSON = objectMapper.writeValueAsString(student);

        MvcResult result = this.mockMvc.perform(post("/v1/students/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Student studentResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertThat(studentResponse.getId()).isNotNull();
    }

    @Test
    void readStudents() throws Exception {
        studentService.createStudent(new Student(1, "anto", "anto", false));
        studentService.createStudent(new Student(2, "anto", "anto", false));


        this.mockMvc.perform(get("/v1/students/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        assertThat(studentService.getAllStudents().size()).isNotZero();
    }

    @Test
    void getByIdTest() throws Exception {
        Student s1 = studentService.createStudent(new Student(1, "anto", "anto", false));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/students/student/getById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(studentService.getAllStudents()).contains(s1);
        assertThat(studentService.getStudentById(1)).isEqualTo(s1);
        assertThat(studentService.getStudentById(1)).isNotNull();
    }

    @Test
    void updateStatus() throws Exception {
        Student s1 = studentService.createStudent(new Student(1, "anto", "anto", false));

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/v1/students/student/updateStatus/1?isWorking=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void deleteStudentById() throws Exception {
        Student s1 = studentService.createStudent(new Student(1, "anto", "anto", false));

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/v1/students/student/deleteById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(studentService.getAllStudents()).isEmpty();
    }

}
