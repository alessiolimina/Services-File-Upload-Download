package it.develhope.services.Services.Upload.and.Download2.controllers;

import it.develhope.services.Services.Upload.and.Download2.entities.Utente;
import it.develhope.services.Services.Upload.and.Download2.repositories.UserRepository;
import it.develhope.services.Services.Upload.and.Download2.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public Utente create(Utente utente){
     utente.setId(null);
     return userRepository.save(utente);
    }

    @PostMapping("/{id}/profile")
    public void uploadProfileImage(){

    }

    @GetMapping
    public List<Utente> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public void getOne(@PathVariable Long id){
        userRepository.findById(id);

    }

    @GetMapping("/{id}/profile")
    public void getProfileImage(){

    }


    @PutMapping("/{id}")
    public void update(@RequestBody Utente utente, @PathVariable Long id){
        utente.setId(id);
        userRepository.save(utente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}
