package com.whitewolf9.rest.webservices.restfulwebservices.users;

import com.whitewolf9.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class created to store the API calls associated to the user resource.
 */
@RestController
public class UserJPAResource {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/jpa/user/{id}")
    public EntityModel<Optional<User>> retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("Id-"+id);
        }
        EntityModel<Optional<User>> model = EntityModel.of(user);

        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkToUsers.withRel("Link to Users"));
        return model;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser (@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        //  .path is used to append to the current URI which is users, so the new path is users/{id}
        //  .buildAndExpand is used to populate the variable {id} in the path
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

}
