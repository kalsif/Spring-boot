package com.example.esercizio06.repository;

import com.example.esercizio06.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long> {


}
