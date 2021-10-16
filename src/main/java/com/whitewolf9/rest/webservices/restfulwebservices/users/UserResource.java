package com.whitewolf9.rest.webservices.restfulwebservices.users;

import com.whitewolf9.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Class created to store the API calls associated to the user resource.
 */
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
        User user = service.findOne(id);
        if(user==null){
            throw new UserNotFoundException("Id-"+id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser (@Valid @RequestBody User user){
        User savedUser = service.save(user);
        //  .path is used to append to the current URI which is users, so the new path is users/{id}
        //  .buildAndExpand is used to populate the variable {id} in the path
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user==null){
            throw new UserNotFoundException("Id-"+id);
        }
    }

}
