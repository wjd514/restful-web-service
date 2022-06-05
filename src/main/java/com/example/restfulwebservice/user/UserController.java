package com.example.restfulwebservice.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserDaoService service;

    public  UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User2> retrieveAllUsers(){
        return service.findAll();
    }

    // GET /users/1 or /user/10 -> String
    @GetMapping("/users/{id}")
    public EntityModel<User2> retrieveUser(@PathVariable int id){
        User2 user2 = service.findOne(id);

        if (user2 == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        // Hateoas
        EntityModel<User2> resource = EntityModel.of(user2);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));


        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<User2> createUser(@Valid @RequestBody User2 user2) {
        User2 savedUser2 = service.save(user2);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser2.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User2 user2 = service.deleteById(id);

        if (user2 == null) {
            throw new UserNotFoundException(String.format("ID[%s] nnot found",id));
        }
    }
}
