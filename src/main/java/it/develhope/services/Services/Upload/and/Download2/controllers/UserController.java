package it.develhope.services.Services.Upload.and.Download2.controllers;

import it.develhope.services.Services.Upload.and.Download2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void create(){

    }

    @PostMapping("/{id}/profile")
    public void uploadProfileImage(){

    }

    @GetMapping
    public void getAll(){

    }

    @GetMapping("/{id}")
    public void getOne(){

    }

    @GetMapping("/{id}/profile")
    public void getProfileImage(){

    }


    @PutMapping("/{id}")
    public void update(){

    }

    @DeleteMapping("/{id}")
    public void delete(){

    }
}
