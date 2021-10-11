package com.whitewolf9.rest.webservices.restfulwebservices.controllers;

import com.whitewolf9.rest.webservices.restfulwebservices.daoservice.UserDaoService;
import com.whitewolf9.rest.webservices.restfulwebservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findOne(id);
    }

    @PostMapping("/users")
    public void createUser (@RequestBody User user){
        User savedUser = service.save(user);
    }

}
