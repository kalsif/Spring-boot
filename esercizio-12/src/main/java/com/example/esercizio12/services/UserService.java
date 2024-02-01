package com.example.esercizio12.services;

import com.example.esercizio12.entities.User;
import com.example.esercizio12.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    public ArrayList<User> getAll() {
        return new ArrayList<>(userRepo.findAll());
    }

    public User updateUser(long id, User user) {
        for(User u : userRepo.findAll()){
            if(u.getId() == id){
                if(user.getName() != null){
                   u.setName(user.getName()) ;
                }
                if(user.getSurname() != null){
                    u.setSurname(user.getSurname());
                }
                if(user.getAge() != 0){
                    u.setAge(user.getAge());
                }
                if(user.getEmail() != null){
                    u.setEmail(user.getEmail());
                }
                return userRepo.saveAndFlush(u);
            }
        }
        return null;
    }

    public User deleteUser(long id) {
        for (User u : userRepo.findAll()) {
            if (u.getId() == id) {
                userRepo.deleteById(id);
                return u;
            }
        }
        return null;
    }
}
