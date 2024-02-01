package com.example.esercizio12.controllers;

import com.example.esercizio12.entities.User;
import com.example.esercizio12.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userSevice;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userSevice.createUser(user);
    }

    @GetMapping("/getAll")
    public ArrayList<User> getAllUser(){
        return userSevice.getAll();
    }

    @PatchMapping("/update/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user){
        return userSevice.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable long id){
        return userSevice.deleteUser(id);
    }
}
