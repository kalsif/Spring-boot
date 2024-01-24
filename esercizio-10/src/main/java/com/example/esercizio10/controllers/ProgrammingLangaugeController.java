package com.example.esercizio10.controllers;

import com.example.esercizio10.entities.ProgrammingLanguage;
import com.example.esercizio10.repositories.ProgrammingLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/repo/prog/languages")
public class ProgrammingLangaugeController {

    @Autowired
    private ProgrammingLanguageRepository progLanguagesRepo;

    @PostMapping("/language/create")
    public void addLanguage(@RequestBody ProgrammingLanguage programmingLanguage) {
        progLanguagesRepo.saveAndFlush(programmingLanguage);
    }

    @GetMapping("/getAll")
    public Page<ProgrammingLanguage> getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ProgrammingLanguage> leanguages = progLanguagesRepo.findAll(pageable);
        return leanguages;
    }

    @PatchMapping("/language/updateInventor/{id}")
    public ProgrammingLanguage updateInventor(@PathVariable long id, @RequestParam String newInventor){
        for(ProgrammingLanguage p : progLanguagesRepo.findAll()){
            if(p.getId() == id){
                p.setInventor(newInventor);
                progLanguagesRepo.saveAndFlush(p);
                return p;
            }else{
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return null;
    }
}
