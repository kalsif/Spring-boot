package com.example.esercizio11.controllers;

import com.example.esercizio11.entities.Student;
import com.example.esercizio11.repositories.StudentRepository;
import com.example.esercizio11.servicies.StudentService;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student/create")
    public Student create(@RequestBody Student student) {
       return studentService.createStudent(student);
    }

    @GetMapping("/getAll")
    public ArrayList<Student> getAllStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/getById/{id}")
    public Student getById(@PathVariable long id) {
        return studentService.getStudentById(id);
    }


    @PatchMapping("/student/updateStatus/{id}")
    public Student updateStatus(@PathVariable long id, @RequestParam boolean isWorking) {
        return studentService.updateStatus(id,isWorking);
    }

    @DeleteMapping("/student/deleteById/{id}")
    public Student deleteById(@PathVariable long id){
       return studentService.deleteStudentById(id);
    }
}
