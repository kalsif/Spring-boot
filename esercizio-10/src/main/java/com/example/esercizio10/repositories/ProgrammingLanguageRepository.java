package com.example.esercizio10.repositories;

import com.example.esercizio10.entities.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage,Long> {
}
