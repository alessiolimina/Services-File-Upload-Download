package it.develhope.services.Services.Upload.and.Download2.services;

import it.develhope.services.Services.Upload.and.Download2.entities.Utente;
import it.develhope.services.Services.Upload.and.Download2.repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @SneakyThrows
    public Utente uploadProfilePicture(Long userId, MultipartFile profilePicture) {
        Optional<Utente> optionalUtente =  userRepository.findById(userId);
        if(!optionalUtente.isPresent()) throw new Exception("User is not present");
        String fileName = fileStorageService.upload(profilePicture);
        Utente utente = optionalUtente.get();
        utente.setProfilePicture(fileName);
        return userRepository.save(utente);
    }
}
