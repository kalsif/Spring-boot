package com.example.esercizio07.repository;

import com.example.esercizio07.entities.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Student,Long> {

    <S extends Student> List<S> findAll(Example<S> example);
}
