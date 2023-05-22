package it.develhope.services.Services.Upload.and.Download2.services;

import it.develhope.services.Services.Upload.and.Download2.dto.DownloadProfilePictureDTO;
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
    private Utente getUtente(Long userId) {
        Optional<Utente> optionalUtente = userRepository.findById(userId);
        if (!optionalUtente.isPresent()) throw new Exception("User is not present");
        return optionalUtente.get();
    }

    @SneakyThrows
    public Utente uploadProfilePicture(Long userId, MultipartFile profilePicture) {
        Utente utente = getUtente(userId);
        if (utente.getProfilePicture() != null) {
            fileStorageService.remove(utente.getProfilePicture());
        }
        String fileName = fileStorageService.upload(profilePicture);
        utente.setProfilePicture(fileName);
        return userRepository.save(utente);
    }

    @SneakyThrows
    public DownloadProfilePictureDTO downloadProfilePicture(Long userId) {
        Utente utente = getUtente(userId);
        DownloadProfilePictureDTO dto = new DownloadProfilePictureDTO();
        dto.setUtente(utente);

        if (utente.getProfilePicture() == null) return dto;
        byte[] profilePictureBytes = fileStorageService.download(utente.getProfilePicture());
        dto.setProfileImage(profilePictureBytes);
        return dto;
    }

    @SneakyThrows
    public void remove(Long userId) {
        Utente utente = getUtente(userId);
        if (utente.getProfilePicture() != null) {
            fileStorageService.remove(utente.getProfilePicture());
        }
        userRepository.deleteById(userId);
    }
}

