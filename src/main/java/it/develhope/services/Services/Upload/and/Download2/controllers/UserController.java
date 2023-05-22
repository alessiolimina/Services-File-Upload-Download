package it.develhope.services.Services.Upload.and.Download2.controllers;

import it.develhope.services.Services.Upload.and.Download2.entities.Utente;
import it.develhope.services.Services.Upload.and.Download2.repositories.UserRepository;
import it.develhope.services.Services.Upload.and.Download2.services.FileStorageService;
import it.develhope.services.Services.Upload.and.Download2.services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public Utente create(Utente utente){
     utente.setId(null);
     return userRepository.save(utente);
    }

    @SneakyThrows
    @PostMapping("/{id}/profile")
    public Utente uploadProfileImage(@PathVariable Long id, @RequestParam MultipartFile profilePicture) {
    return userService.uploadProfilePicture(id, profilePicture);
    }

    @GetMapping
    public List<Utente> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Utente> getOne(@PathVariable Long id){
      return userRepository.findById(id);

    }
    @SneakyThrows
    @GetMapping("/{id}/profile")
    public void getProfileImage(@PathVariable Long id){
    userService.downloadProfilePicture(id);
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
