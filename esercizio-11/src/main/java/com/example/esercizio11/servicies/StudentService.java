package com.example.esercizio11.servicies;

import com.example.esercizio11.entities.Student;
import com.example.esercizio11.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student){
       return studentRepository.saveAndFlush(student);
    }

    public ArrayList<Student> getAllStudents(){
        return new ArrayList<>(studentRepository.findAll());
    }

    public Student getStudentById(long id){
        for (Student s : studentRepository.findAll()) {
            if (s.getId() == id) {
                return studentRepository.saveAndFlush(s);
            }
        }
        return null;
    }


    public Student deleteStudentById(long id){
        for(Student s : studentRepository.findAll()){
            if(s.getId()==id){
                studentRepository.deleteById(id);
                return s;
            }
        }
        return null;
    }


    public Student updateStatus(long id, boolean isWorking){
        for (Student s : studentRepository.findAll()){
            if(s != null && s.getId()==id){
                s.setWorking(isWorking);
                return studentRepository.saveAndFlush(s);
            }
        }
        return null;
    }
}
