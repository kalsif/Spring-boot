package com.example.esercizio07.controllers;

import com.example.esercizio07.entities.Student;
import com.example.esercizio07.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentController {
    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping("/getAll")
    public ArrayList<Student> getStudents() {
        return new ArrayList<>(studentsRepository.findAll());
    }

    @GetMapping("/getById/{id}")
    public Student getById(@PathVariable long id){
        for(Student x : studentsRepository.findAll()){
            if(x.getId() == id){
                return x;
            }
        }
        return null;
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentsRepository.save(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudentFromId(@PathVariable long id) {
        for(Student x : studentsRepository.findAll()){
            if(x.getId() == id){
                studentsRepository.delete(x);
            }
        }
    }
}
